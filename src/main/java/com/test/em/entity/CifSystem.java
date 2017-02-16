package com.test.em.entity;


/*
 * 
 * 
 * +--------------+-------------+------+-----+---------+-------+
| Field        | Type        | Null | Key | Default | Extra |
+--------------+-------------+------+-----+---------+-------+
| sys_id       | varchar(10) | NO   | PRI | NULL    |       |
| sys_org_id   | varchar(10) | NO   |     | NULL    |       |
| sys_name     | varchar(10) | NO   |     | NULL    |       |
| sys_desc     | varchar(25) | YES  |     | NULL    |       |
| isactive     | char(1)     | NO   |     | NULL    |       |
| created_user | varchar(20) | YES  |     | NULL    |       |
| created_on   | datetime    | YES  |     | NULL    |       |
| updated_user | varchar(20) | YES  |     | NULL    |       |
| updated_on   | datetime    | YES  |     | NULL    |       |
+--------------+-------------+------+-----+---------+-------+

 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@IdClass(SystemOrgId.class)
@Table(name="system")
public class CifSystem implements Serializable{
	
	@Id
	@Column(name="sys_id")
	private String systemId;
	
	@Id
	@Column(name="sys_org_id")
	private String systemOrgId;
	
	@Column(name="sys_name")
	private String systemName;
	
	@Column(name="sys_desc")
	private String systemDesc;
	
	@Column(name="isactive")
	@Type(type="yes_no")
	private boolean isActive;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="updated_user")
	private String updatedUser;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSystemOrgId() {
		return systemOrgId;
	}
	public void setSystemOrgId(String systemOrgId) {
		this.systemOrgId = systemOrgId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemDesc() {
		return systemDesc;
	}
	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
