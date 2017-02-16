package com.test.em.repository;

import com.test.em.entity.CifProcess;
import com.test.em.entity.CifProcessRole;
import com.test.em.entity.CifSystem;
import com.test.em.entity.ProcessOrgID;
import com.test.em.entity.ProcessRoleOrgId;
import com.test.em.entity.SystemOrgId;

public interface MasterDataRepository {
	
	CifProcess persistProcess(CifProcess process, ProcessOrgID id);
	CifProcessRole assignProcessRole(CifProcessRole role, ProcessRoleOrgId id);
	CifSystem persistSystem(CifSystem system, SystemOrgId id);

}
