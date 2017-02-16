package com.test.em.common.service;

import java.util.List;

import com.test.em.entity.CifUser;
import com.test.em.entity.ProcessActivityHeader;
import com.test.em.entity.ProcessPayloadLevel;

public interface CifService {

	ProcessActivityHeader updateProcessActivityHeader(ProcessActivityHeader processActivityHeader);
	ProcessPayloadLevel getProcessActivitylevels(Long headerId, Long levelNumber);
	
	CifUser getUserDetails(String userName);
	List<ProcessActivityHeader> getProcessActivityHeader(String orgId);
}
