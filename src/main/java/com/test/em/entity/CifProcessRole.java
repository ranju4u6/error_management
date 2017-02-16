package com.test.em.entity;


/*+----------------+-------------+------+-----+---------+-------+
| Field          | Type        | Null | Key | Default | Extra |
+----------------+-------------+------+-----+---------+-------+
| prsrol_prs_id  | varchar(10) | NO   | PRI | NULL    |       |
| prsrol_role_id | varchar(10) | NO   |     | NULL    |       |
| prsrol_org_id  | varchar(10) | NO   |     | NULL    |       |
| created_user   | varchar(20) | YES  |     | NULL    |       |
| created_on     | datetime    | YES  |     | NULL    |       |
+----------------+-------------+------+-----+---------+-------+
*/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ProcessRoleOrgId.class)
@Table(name="process_role")
public class CifProcessRole {
	
	@Id
	@Column(name="prsrol_prs_id")
	private String processRoleProcessId;
	
	@Id
	@Column(name="prsrol_role_id")
	private String processRoleId;
	
	@Id
	@Column(name="prsrol_org_id")
	private String processRoleOrgId;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name="created_on")
	private Date created_on;

	public String getProcessRoleProcessId() {
		return processRoleProcessId;
	}

	public void setProcessRoleProcessId(String processRoleProcessId) {
		this.processRoleProcessId = processRoleProcessId;
	}

	public String getProcessRoleId() {
		return processRoleId;
	}

	public void setProcessRoleId(String processRoleId) {
		this.processRoleId = processRoleId;
	}

	public String getProcessRoleOrgId() {
		return processRoleOrgId;
	}

	public void setProcessRoleOrgId(String processRoleOrgId) {
		this.processRoleOrgId = processRoleOrgId;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	
	
	
}
