package com.test.em.repository;

import java.util.List;

import com.test.em.entity.CifUser;
import com.test.em.entity.ProcessActivityError;
import com.test.em.entity.ProcessActivityErrorDetail;
import com.test.em.entity.ProcessActivityHeader;
import com.test.em.entity.ProcessPayloadLevel;
import com.test.em.generated.ProcessActivityErrorDetailType;

public interface EMRepository {
	ProcessActivityHeader persistProcessActivityHeader(ProcessActivityHeader processActivityHeader);
	ProcessActivityHeader getProcessActivityHeader(String processActivityHeaderId);
	
	ProcessActivityError getErrorByheaderIdSeqAndIndex(Long processActivityHeaderId, int processSequence, int processIndex);
	
	void persistProcessActivityError(ProcessActivityError processActivityError);
	ProcessActivityErrorDetail getErrorDetailBySeqErrorLevel(String seq, Long errorId, Integer level);
	
	ProcessPayloadLevel getProcessActivityLevels(Long headerId, Long levelNumber);
	
	void persistProcessActivityErrorDetail(ProcessActivityErrorDetail processActivityErrorDetail);
	
	CifUser getUserDetails(String userName);
	
	List<ProcessActivityHeader> getProcessActivityHeaders(String orgId);
	
	List<ProcessActivityError> getErrors(Long headerId);
	
}
