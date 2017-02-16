package com.test.em.repository;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.em.entity.CifUser;
import com.test.em.entity.ProcessActivityError;
import com.test.em.entity.ProcessActivityErrorDetail;
import com.test.em.entity.ProcessActivityHeader;
import com.test.em.entity.ProcessPayloadLevel;

@Repository
public class EMRepositoryImpl implements EMRepository {
	
	Logger logger = LoggerFactory.getLogger(EMRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	
	
	public ProcessActivityHeader persistProcessActivityHeader(
			ProcessActivityHeader processActivityHeader) {
		
		logger.info("EMRepositoryImpl_______About to persist ProcessActivityHeader");
		
		getSession().saveOrUpdate(processActivityHeader);
			
		
		return processActivityHeader;
	}
	
	public ProcessActivityHeader getProcessActivityHeader(String processActivityHeaderId){
		final Query headerQuery = getSession().createQuery("from ProcessActivityHeader where processActivityHeaderId = :processActivityHeaderId");
		headerQuery.setString("processActivityHeaderId", processActivityHeaderId);
		ProcessActivityHeader processActivityHeader = (ProcessActivityHeader)headerQuery.uniqueResult();
		return processActivityHeader;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ProcessActivityError getErrorByheaderIdSeqAndIndex(
			Long processActivityHeaderId, int processSequence, int processIndex) {
		Query query = getSession().createQuery("from ProcessActivityError where id= :headerId and processSequence= :processSequence and processIndex = :processIndex");
		query.setLong("headerId", processActivityHeaderId);
		query.setInteger("processSequence", processSequence);
		query.setInteger("processIndex", processIndex);
		
		
		return (ProcessActivityError)query.uniqueResult();
		
	}

	@Override
	public void persistProcessActivityError(
			ProcessActivityError processActivityError) {
		getSession().saveOrUpdate(processActivityError);
		
	}

	@Override
	public ProcessActivityErrorDetail getErrorDetailBySeqErrorLevel(String seq,
			Long errorId, Integer level) {
		ProcessActivityErrorDetail persistedErrorDtl = null;
		
		final Query query = getSession().createQuery(" from ProcessActivityErrorDetail where processSequence = :processSequence and"
				+ " processActivityErrorId = :errorId and"
				+ " processLevel = :levelId");
		
		query.setString("processSequence", seq);
		query.setLong("errorId", errorId);
		query.setInteger("levelId", level);
		
		return null;
	}

	@Override
	public ProcessPayloadLevel getProcessActivityLevels(Long headerId,
			Long levelNumber) {
		ProcessPayloadLevel payloadLevel = null;
		
		Query payloadLevelQuery = getSession().createQuery(
				"select level from ProcessPayloadLevel level,"
				+ "ProcessActivityHeader header,"
				+ "ProcessPayloadSchema schema "
				+ "where header.id = :headerId and "
				+ "header.organizationId = schema.orgId and "
				+ "header.processId = schema.processId and "
				+ "header.sourceSystemId = schema.srcSystemId and "
				+ "schema.id = level.schemaId and "
				+ "level.levelNumber = :levelNumber");
		
		payloadLevelQuery.setLong("headerId", headerId);
		payloadLevelQuery.setLong("levelNumber", levelNumber);
		
		
		logger.info("EMRepositoryImpl_______Level Query :{}", payloadLevelQuery.toString());
		
		payloadLevel = (ProcessPayloadLevel)payloadLevelQuery.uniqueResult();
		
		return payloadLevel;
	}

	@Override
	public void persistProcessActivityErrorDetail(
			ProcessActivityErrorDetail processActivityErrorDetail) {
		getSession().saveOrUpdate(processActivityErrorDetail);
		
	}

	@Override
	public CifUser getUserDetails(String userName) {
		Query userQuery =getSession().createQuery("from Cifuser where userId = :userId and active = 'Y'");
		userQuery.setString("userId", userName);
		return (CifUser)userQuery.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProcessActivityHeader> getProcessActivityHeaders(String orgId) {
		// TODO Auto-generated method stub
		Query headerQuery = getSession().createQuery("from ProcessActivityHeader where organizationId = :orgId");
		headerQuery.setString("orgId", orgId);
		
		List<ProcessActivityHeader> headerList = (List<ProcessActivityHeader>)headerQuery.list();
		
		return headerList;
	}

	@Override
	public List<ProcessActivityError> getErrors(Long headerId) {
		
		List<ProcessActivityError> errorList = null;
		
		Query errorQuery = getSession().createQuery("from ProcessActivityError where headerId = :headerId order by processSequence asc");
		errorQuery.setLong("headerId", headerId);
		
		errorList = (List<ProcessActivityError> )errorQuery.list();
		
		return errorList;
	}

}
