package com.test.em.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.em.entity.CifProcess;
import com.test.em.entity.CifProcessRole;
import com.test.em.entity.CifSystem;
import com.test.em.entity.ProcessOrgID;
import com.test.em.entity.ProcessRoleOrgId;
import com.test.em.entity.SystemOrgId;

@Repository
public class MasterDataRepositoryImpl implements MasterDataRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Propagation.REQUIRES_NEW
	 * Start a completely new transaction
	 */

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CifProcess persistProcess(CifProcess process, ProcessOrgID id) {
		final Session session = getSession();
		CifProcess persistedProcess = (CifProcess)session.get(CifProcess.class, id);
		if(null == persistedProcess){
			session.save(process);
			persistedProcess = process;
		}
		
		return persistedProcess;
		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CifProcessRole assignProcessRole(CifProcessRole role,
			ProcessRoleOrgId id) {
		final Session session = getSession();
		CifProcessRole persistedProcessRole = (CifProcessRole)session.get(CifProcessRole.class, id);
		if(null == persistedProcessRole){
			session.save(role);
			persistedProcessRole = role;
		}
		
		return persistedProcessRole;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CifSystem persistSystem(CifSystem system, SystemOrgId id) {
		final Session session = getSession();
		CifSystem persistedSystem = (CifSystem)session.get(CifSystem.class, id);
		if(null == persistedSystem){
			session.save(system);
			persistedSystem = system;
		}
		
		return persistedSystem;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

}
