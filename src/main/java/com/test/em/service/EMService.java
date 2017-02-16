package com.test.em.service;

import com.test.em.generated.GetCorrectedActivitiesRequest;
import com.test.em.generated.GetCorrectedActivitiesResponse;
import com.test.em.generated.LogProcessActivityErrorRequest;
import com.test.em.generated.LogProcessActivityErrorResponse;

public interface EMService {
	LogProcessActivityErrorResponse persistErrorRecord(LogProcessActivityErrorRequest loggedErrorRequest) throws Exception;
	GetCorrectedActivitiesResponse getCorrectedRecords(GetCorrectedActivitiesRequest request);
}
