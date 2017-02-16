/*--------------+-------------+------+-----+---------+-------+
| pro_id       | varchar(10) | NO   | PRI | NULL    |       |
| pro_og_id    | varchar(10) | NO   |     | NULL    |       |
| pro_name     | varchar(10) | NO   |     | NULL    |       |
| pro_desc     | varchar(20) | YES  |     | NULL    |       |
| isbatch      | char(1)     | NO   |     | NULL    |       |
| isactive     | char(1)     | NO   |     | NULL    |       |
| created_user | varchar(20) | YES  |     | NULL    |       |
| created_on   | datetime    | YES  |     | NULL    |       |
| updated_user | varchar(20) | YES  |     | NULL    |       |*/
package com.test.em.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@IdClass(ProcessOrgID.class)
@Table(name="process")
public class CifProcess implements Serializable{

	@Id
	@Column(name="pro_id")
	private String processId;
	
	@Id
	@Column(name="pro_og_id")
	private String processOrgId;
	
	@Column(name="pro_name")
	private String processName;
	
	@Column(name="pro_desc")
	private String processDesc;
	
	@Column(name="isactive")
	@Type(type="yes_no")
	private boolean isActive;
	
	@Column(name="isbatch")
	@Type(type="yes_no")
	private boolean isBatch;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="updated_user")
	private String updatedUser;
	
	@Column(name="updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	
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
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessDesc() {
		return processDesc;
	}
	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isBatch() {
		return isBatch;
	}
	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
