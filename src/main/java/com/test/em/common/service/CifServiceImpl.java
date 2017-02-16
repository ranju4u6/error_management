package com.test.em.common.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.em.entity.CifProcess;
import com.test.em.entity.CifProcessRole;
import com.test.em.entity.CifSystem;
import com.test.em.entity.CifUser;
import com.test.em.entity.ProcessActivityHeader;
import com.test.em.entity.ProcessOrgID;
import com.test.em.entity.ProcessPayloadLevel;
import com.test.em.entity.ProcessRoleOrgId;
import com.test.em.entity.SystemOrgId;
import com.test.em.repository.EMRepository;
import com.test.em.repository.MasterDataRepository;

@Service
public class CifServiceImpl implements CifService {
	
	Logger logger = LoggerFactory.getLogger(CifServiceImpl.class);
	
	@Autowired
	private EMRepository emRepository;
	
	
	@Autowired
	private MasterDataRepository masterDataRepository;

	/**
	 * There should be a transaction before this method is called.
	 * Else it will throw an exception.
	 * Here we are starting a transaction at service level, and same will
	 * be used here.
	 */
	@Transactional(propagation=Propagation.MANDATORY)
	public ProcessActivityHeader updateProcessActivityHeader(
			ProcessActivityHeader processActivityHeader) {
		logger.info("CifServiceImpl_______inside updateProcessActivityHeader");
		
		final String generatedHeaderId = processActivityHeader.getOrganizationId()
				+processActivityHeader.getProcessId()
				+processActivityHeader.getSourceSystemId()
				+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(processActivityHeader.getOriginalProcessDatetime());
		processActivityHeader.setProcessActivityHeaderId(generatedHeaderId);
		
		logger.info("CifServiceImpl_______generatedHeaderId: {}", generatedHeaderId);
		
		
		//if a same record is send again for persisting,find it from db and return it. do not persist.
		final ProcessActivityHeader persistedHeader = emRepository.getProcessActivityHeader(generatedHeaderId);
		logger.info("CifServiceImpl_______Existing header: {}", persistedHeader);
		
		if(null == persistedHeader){
			logger.info("CifServiceImpl_______ProcessId: {}", processActivityHeader.getProcessId());
			logger.info("CifServiceImpl_______Organization Id: {}", processActivityHeader.getOrganizationId());
			persistProcessRecord(processActivityHeader.getProcessId(), processActivityHeader.getOrganizationId());
			assignProcessRole(processActivityHeader.getProcessId(), processActivityHeader.getOrganizationId());
			
			persistCifSystemRecord(
					new String[] {
							processActivityHeader.getDestinationSystemId(),
							processActivityHeader.getSourceSystemId(),
							processActivityHeader.getOrganizationId() },
					processActivityHeader.getOrganizationId());
			
			emRepository.persistProcessActivityHeader(processActivityHeader);
			
		}else{
			logger.info("CifServiceImpl_______Returnoing existing header");
			return persistedHeader;
		}
		
		
		return processActivityHeader;
	}
	
	/**
	 * Fetch all records with organization id (eg...  TGIF)
	 * @param orgId
	 * @return
	 */
	public List<ProcessActivityHeader> getProcessActivityHeader(String orgId){
		List<ProcessActivityHeader> headerList = null;
		headerList = emRepository.getProcessActivityHeaders(orgId);
		return headerList;		
	}
	
	private void persistProcessRecord(String processId, String orgId){
		logger.info("CifServiceImpl_______persistProcessRecord started");
		final CifProcess process = new CifProcess();
		process.setProcessId(processId);
		process.setProcessOrgId(orgId);
		process.setProcessName(processId);//  ???????
		process.setActive(true);
		process.setBatch(true);
		
		final ProcessOrgID primaryId = new ProcessOrgID();
		primaryId.setProcessId(processId);
		primaryId.setProcessOrgId(orgId);
		
		masterDataRepository.persistProcess(process, primaryId);
		logger.info("CifServiceImpl_______persistProcessRecord completed");
		
	}
	
	private void assignProcessRole(String processId, String orgId){
		logger.info("CifServiceImpl_______assignProcessRole started");
		final CifProcessRole role = new CifProcessRole();
		role.setProcessRoleProcessId(processId);
		role.setProcessRoleOrgId(orgId);
		role.setProcessRoleId("Admin"); //????
		role.setCreatedUser("SYSTEM"); //????
		role.setCreated_on(new Date());
		
		final ProcessRoleOrgId primaryKey = new ProcessRoleOrgId();
		primaryKey.setProcessRoleProcessId(processId);
		primaryKey.setProcessRoleOrgId(orgId);
		primaryKey.setProcessRoleId("Admin");
		
		masterDataRepository.assignProcessRole(role, primaryKey);
		logger.info("CifServiceImpl_______assignProcessRole completed");
		
	}
	
	private void persistCifSystemRecord(String[] systemIdList, String orgId){
		logger.info("CifServiceImpl_______persistCifSystemRecord started");
		final CifSystem system = new CifSystem();
		final SystemOrgId primaryKey = new SystemOrgId();
		
		for(String systemId: systemIdList){
			system.setSystemId(systemId);
			system.setSystemOrgId(orgId);
			system.setSystemName(systemId);
			system.setActive(true);
			
			primaryKey.setSystemId(systemId);
			primaryKey.setSystemOrgId(orgId);
			
			masterDataRepository.persistSystem(system, primaryKey);
			
		}
		logger.info("CifServiceImpl_______persistCifSystemRecord ended");
	}

	@Transactional(readOnly = true)
	public ProcessPayloadLevel getProcessActivitylevels(Long headerId,
			Long levelNumber) {
		return emRepository.getProcessActivityLevels(headerId, levelNumber);
	}

	@Override
	public CifUser getUserDetails(String userName) {
		return emRepository.getUserDetails(userName);
	}

}
