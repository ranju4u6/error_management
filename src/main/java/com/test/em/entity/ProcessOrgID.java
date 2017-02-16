package com.test.em.entity;

import java.io.Serializable;

public class ProcessOrgID implements Serializable{
	
	private String processId;
	private String processOrgId;
	
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessOrgId() {
		return processOrgId;
	}
	public void setProcessOrgId(String processOrgId) {
		this.processOrgId = processOrgId;
	}
	
	

}
