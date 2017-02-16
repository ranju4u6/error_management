package com.test.em.entity;

/*
 * +--------------+-------------+------+-----+---------+-------+
| Field        | Type        | Null | Key | Default | Extra |
+--------------+-------------+------+-----+---------+-------+
| usr_cif_id   | varchar(20) | NO   | PRI | NULL    |       |
| usr_fname    | varchar(25) | YES  |     | NULL    |       |
| usr_lname    | varchar(25) | YES  |     | NULL    |       |
| usr_org_id   | varchar(25) | NO   |     | NULL    |       |
| usr_pwd      | varchar(50) | NO   |     | NULL    |       |
| usr_email    | varchar(30) | YES  |     | NULL    |       |
| isactive     | char(1)     | NO   |     | NULL    |       |
| created_user | varchar(25) | YES  |     | NULL    |       |
| created_on   | datetime    | YES  |     | NULL    |       |
| updated_user | varchar(25) | YES  |     | NULL    |       |
| updated_on   | datetime    | YES  |     | NULL    |       |
+--------------+-------------+------+-----+---------+-------+

 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="user_cif")
public class CifUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547953294161754704L;

	@Id
	@Column(name="usr_cif_id")
	private String userId;
	
	@Column(name="usr_fname")
	private String fname;
	
	@Column(name="usr_lname")
	private String lname;
	
	@Column(name="usr_org_id")
	private String orgId;
	
	@Column(name="usr_pwd")
	private String pwd;
	
	@Column(name="isactive")
	@Type(type="yes_no")
	private boolean isActive;
	
	@Column(name="usr_email")
	private String email;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CifUser [userId=" + userId + ", fname=" + fname + ", lname="
				+ lname + ", orgId=" + orgId + ", pwd=" + pwd + ", isActive="
				+ isActive + ", email=" + email + "]";
	}
	
	
}
