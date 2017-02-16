package com.test.em.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.em.common.service.CifService;
import com.test.em.entity.ProcessActivityError;
import com.test.em.entity.ProcessActivityErrorDetail;
import com.test.em.entity.ProcessActivityHeader;
import com.test.em.entity.ProcessPayloadLevel;
import com.test.em.generated.CorrectedRecordType;
import com.test.em.generated.CorrectedRecordsType;
import com.test.em.generated.GetCorrectedActivitiesRequest;
import com.test.em.generated.GetCorrectedActivitiesResponse;
import com.test.em.generated.LogProcessActivityErrorRequest;
import com.test.em.generated.PayloadRecordType;
import com.test.em.generated.LogProcessActivityErrorRequest.ProcessActivityErrors;
import com.test.em.generated.LogProcessActivityErrorResponse;
import com.test.em.generated.ProcessActivityErrorDetailType;
import com.test.em.generated.ProcessActivityErrorType;
import com.test.em.generated.ProcessActivityErrorType.ProcessActivityErrorDetails;
import com.test.em.generated.ProcessActivityHeaderType;
import com.test.em.repository.EMRepository;

@Service
public class EMServiceImpl implements EMService {
	
	Logger logger = LoggerFactory.getLogger(EMServiceImpl.class);
	
	@Autowired
	private CifService cifService;
	
	@Autowired
	private EMRepository emRepository;

	@Transactional
	public LogProcessActivityErrorResponse persistErrorRecord(
			LogProcessActivityErrorRequest loggedErrorRequest) throws Exception {
		
		LogProcessActivityErrorResponse response = new LogProcessActivityErrorResponse();
		
		ProcessActivityHeaderType loggedProcessActivityHeader = loggedErrorRequest.getProcessActivityHeader();
		
		ProcessActivityHeader processActivityHeader = new ProcessActivityHeader();
		BeanUtils.copyProperties(loggedProcessActivityHeader, processActivityHeader);
		
		logger.info("EMServiceImpl_______Completed converting request to entity. [{}]", processActivityHeader);
		
		logger.info("EMServiceImpl_______Setting process date time. [{}]", loggedProcessActivityHeader.getOriginalProcessDatetime());
		processActivityHeader.setOriginalProcessDatetime(loggedProcessActivityHeader.getOriginalProcessDatetime().toGregorianCalendar().getTime());
		
		
		logger.info("EMServiceImpl_______About to persist header");
		cifService.updateProcessActivityHeader(processActivityHeader);
		logger.info("EMServiceImpl_______Completed persisting header");
		
		logger.info("EMServiceImpl_______About to persist Errors");
		
		final ProcessActivityErrors processActivityErrors = loggedErrorRequest.getProcessActivityErrors();
		
		updateError(processActivityErrors, processActivityHeader);
		
		logger.info("EMServiceImpl_______Completed persisting Errors");
		
		
		response.setProcessActivityHeaderId(processActivityHeader.getProcessActivityHeaderId());
		return response;
	}
	
	
	@Transactional
	public GetCorrectedActivitiesResponse getCorrectedRecords(GetCorrectedActivitiesRequest request){
		logger.info("EMServiceImpl_______retrieving corrected records");
		
		GetCorrectedActivitiesResponse response = new GetCorrectedActivitiesResponse();
		
		List<ProcessActivityHeader> headerList = cifService.getProcessActivityHeader(request.getOrganizationId());
		logger.info("EMServiceImpl_______completed retrieving headers");
		
		CorrectedRecordsType correctedRecords = new CorrectedRecordsType();
		
		for(ProcessActivityHeader header: headerList){
			CorrectedRecordType corectedRecordType = new CorrectedRecordType();
			ProcessActivityHeaderType headerType = new ProcessActivityHeaderType();
			BeanUtils.copyProperties(header, headerType);
			
			List<ProcessActivityError> errorList = emRepository.getErrors(header.getId());
			
			boolean correctedRecFound = false;
			
			for(ProcessActivityError processActivityError : errorList){
				if("SUBMITTED".equals(processActivityError.getProcessStatusId())){
					
					correctedRecFound = true;
					
					PayloadRecordType payloadRecord = new PayloadRecordType();
					
					payloadRecord.setProcessSequence(processActivityError.getProcessSequence());
					payloadRecord.setPayload(processActivityError.getPayload());
					//not setting process index
					
					corectedRecordType.getPayloads().add(payloadRecord);
					
					processActivityError.setProcessStatusId("IN PROCESS");
					processActivityError.setLocked(true);
					//not setting session id
					
					//Update error to inprocess
					emRepository.persistProcessActivityError(processActivityError);
					
				}
			}
			
			
			if(correctedRecFound){
				corectedRecordType.setProcessActivityHeader(headerType);
				correctedRecords.getCorrectedRecord().add(corectedRecordType);
			}
		}
		
		
		logger.info("EMServiceImpl_______completed retrieving corrected records with count:{}", correctedRecords.getCorrectedRecord().size());
		response.setCorrectedRecords(correctedRecords);
		
		return response;
	}
	
	
	private void updateError(ProcessActivityErrors processActivityErrors, ProcessActivityHeader processActivityHeader) throws Exception{
		logger.info("EMServiceImpl_______Total Errors[{}]", processActivityErrors.getProcessActivityError().size());
		
		for(ProcessActivityErrorType processActivityError: processActivityErrors.getProcessActivityError()){
			
			//XML Payload
			final String errorPayload = StringUtils.deleteWhitespace(processActivityError.getPayload());
			
			ProcessActivityError errorToBePersisted = null;
			
			ProcessActivityError persistedError = emRepository
					.getErrorByheaderIdSeqAndIndex(
							processActivityHeader.getId(),
							processActivityError.getProcessSequence(),
							processActivityError.getProcessIndex());
			
			if(((persistedError != null) && (!persistedError.isLocked()) && (!persistedError.getProcessStatusId().equalsIgnoreCase("SUBMITTED")))){
				persistedError.setPayload(errorPayload);
				persistedError.setProcessStatusId(processActivityError.getStatusId());
				persistedError.setErrorMessage(processActivityError.getErrorMessage());
				
				errorToBePersisted = persistedError;
				//if a persisted error record exists, update it.
			}else{
				
				errorToBePersisted = new ProcessActivityError();
				
				errorToBePersisted.setHeaderId(processActivityHeader.getId());
				errorToBePersisted.setPayload(errorPayload);
				errorToBePersisted.setProcessSequence(processActivityError.getProcessSequence());
				errorToBePersisted.setProcessIndex(processActivityError.getProcessIndex());
				errorToBePersisted.setProcessDate(processActivityError.getProcessDate().toGregorianCalendar().getTime());
				errorToBePersisted.setSrcSystemRef(processActivityError.getSourceSystemRefId());
				errorToBePersisted.setProcessStatusId(processActivityError.getStatusId());
				errorToBePersisted.setErrorMessage(processActivityError.getErrorMessage());
				
			}
			
			if(null != errorToBePersisted){
				emRepository.persistProcessActivityError(errorToBePersisted);
				logger.info("EMServiceImpl_______Completed persisting ProcessActivityError.");
				logger.info("EMServiceImpl_______Started persisting ProcessActivityErrorDetail.");
				logger.info("EMServiceImpl_______Header:{}",processActivityHeader);
				updateErrorDetail(processActivityError.getProcessActivityErrorDetails(), errorToBePersisted.getId(), processActivityHeader.getId());
				
			}
			
		}
		
		
	}
	
	
	
	private void updateErrorDetail(ProcessActivityErrorDetails processActivityErrorDetailList, Long errorId, Long headerId) throws Exception{
		
		ProcessActivityErrorDetail errorDetailToBePersisted;
		ProcessPayloadLevel processPayloadLevel;
		
		for(ProcessActivityErrorDetailType errorDetail: processActivityErrorDetailList.getProcessActivityErrorDetail()){
			
			final ProcessActivityErrorDetail persistedErrorDetail = emRepository.getErrorDetailBySeqErrorLevel
					(errorDetail.getProcessSequence(), errorId, errorDetail.getProcessLevel());
			
			if(null != persistedErrorDetail){
				persistedErrorDetail.setErrorMessage(errorDetail.getErrorMessage());
				persistedErrorDetail.setStatusId(errorDetail.getStatusId());
				errorDetailToBePersisted = persistedErrorDetail;
			}else{
				processPayloadLevel = cifService.getProcessActivitylevels(headerId,Long.valueOf(errorDetail.getProcessLevel()));
				if(null == processPayloadLevel){
					throw new Exception("Payload schema information not available for this request");
				}
				errorDetailToBePersisted = new ProcessActivityErrorDetail();
				errorDetailToBePersisted.setProcessActivityErrorId(errorId);
				errorDetailToBePersisted.setProcessLevelId(processPayloadLevel.getId().intValue());
				errorDetailToBePersisted.setProcessSeqquence(errorDetail.getProcessSequence());
				errorDetailToBePersisted.setErrorMessage(errorDetail.getErrorMessage());
				errorDetailToBePersisted.setStatusId(errorDetail.getStatusId());
			}
			logger.info("EMServiceImpl_______Error Detail to be persisted: {}",errorDetailToBePersisted);
			emRepository.persistProcessActivityErrorDetail(errorDetailToBePersisted);
			logger.info("EMServiceImpl_______Completed persisting ProcessActivityErrorDetail.");
			
		}
		
	}

}
