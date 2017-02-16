package com.test.em.endpoint;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFaultException;

import com.test.em.generated.GetCorrectedActivitiesRequest;
import com.test.em.generated.GetCorrectedActivitiesResponse;
import com.test.em.generated.LogProcessActivityErrorRequest;
import com.test.em.generated.LogProcessActivityErrorResponse;
import com.test.em.service.EMService;

@Endpoint
public class ErrorManagementServiceEndpoint {
	Logger logger = LoggerFactory.getLogger(ErrorManagementServiceEndpoint.class);
	
	@Autowired
	private EMService emService;

	@PayloadRoot(localPart="LogProcessActivityErrorRequest", namespace="http://com.capgemini/cif/iem")
	public @ResponsePayload LogProcessActivityErrorResponse upsertError(@RequestPayload LogProcessActivityErrorRequest logProcessActivityErrorRequest){
		
		logger.info("ErrorManagementServiceEndpoint_______Received upsertError request");
		
		LogProcessActivityErrorResponse response;
		try {
			response = emService.persistErrorRecord(logProcessActivityErrorRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SoapFaultException(e);
		}
		
		logger.info("ErrorManagementServiceEndpoint_______Completed processing upsertError request");
		return response;
	}
	
	@PayloadRoot(namespace="http://com.capgemini/cif/iem", localPart="GetCorrectedActivitiesRequest")
	public @ResponsePayload GetCorrectedActivitiesResponse getCorrectedRecords(@RequestPayload GetCorrectedActivitiesRequest getCorrectedActivitiesRequest){
		logger.info("ErrorManagementServiceEndpoint_______Received getCorrectedRecords request");
		
		GetCorrectedActivitiesResponse response = emService.getCorrectedRecords(getCorrectedActivitiesRequest);
		
		
		return response;
	}
}
