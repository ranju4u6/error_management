package com.test.em.entity;

/*
 * +-----------------+-------------+------+-----+---------+----------------+
| Field           | Type        | Null | Key | Default | Extra          |
+-----------------+-------------+------+-----+---------+----------------+
| pae_suid        | int(11)     | NO   | PRI | NULL    | auto_increment |
| pae_pah_suid    | int(11)     | NO   |     | NULL    |                |
| pae_proc_seq    | int(10)     | NO   |     | NULL    |                |
| pae_proc_index  | int(10)     | NO   |     | NULL    |                |
| pae_proc_dt     | datetime    | NO   |     | NULL    |                |
| pae_sys_src_ref | varchar(20) | YES  |     | NULL    |                |
| pae_payload     | text        | NO   |     | NULL    |                |
| pae_err_msg     | text        | YES  |     | NULL    |                |
| pae_pst_id      | varchar(20) | NO   |     | NULL    |                |
| islocked        | char(1)     | NO   |     | NULL    |                |
| pae_sess_id     | varchar(20) | YES  |     | NULL    |                |
| pae_err_type    | varchar(20) | YES  |     | NULL    |                |
+-----------------+-------------+------+-----+---------+----------------+

 */


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="process_activity_err")
public class ProcessActivityError implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pae_suid")
	private Long id;
	
	@Column(name="pae_pah_suid")
	private Long headerId;
	
	@Column(name="pae_proc_seq")
	private Integer processSequence;
	
	@Column(name="pae_proc_index")
	private Integer processIndex;
	
	@Column(name="pae_proc_dt")
	private Date processDate;
	
	@Column(name="pae_sys_src_ref")
	private String srcSystemRef;
	
	@Column(name="pae_payload")
	private String payload;
	
	@Column(name="pae_err_msg")
	private String errorMessage;
	
	@Column(name="pae_pst_id ")
	private String processStatusId;
	
	@Column(name="islocked")
	@Type(type="yes_no")
	private boolean isLocked;
	
	@Column(name="pae_sess_id")
	private String sessionId;
	
	@Column(name="pae_err_type")
	private String processErrorType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public Integer getProcessSequence() {
		return processSequence;
	}

	public void setProcessSequence(Integer processSequence) {
		this.processSequence = processSequence;
	}

	public Integer getProcessIndex() {
		return processIndex;
	}

	public void setProcessIndex(Integer processIndex) {
		this.processIndex = processIndex;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getSrcSystemRef() {
		return srcSystemRef;
	}

	public void setSrcSystemRef(String srcSystemRef) {
		this.srcSystemRef = srcSystemRef;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getProcessStatusId() {
		return processStatusId;
	}

	public void setProcessStatusId(String processStatusId) {
		this.processStatusId = processStatusId;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getProcessErrorType() {
		return processErrorType;
	}

	public void setProcessErrorType(String processErrorType) {
		this.processErrorType = processErrorType;
	}
	
	

}
