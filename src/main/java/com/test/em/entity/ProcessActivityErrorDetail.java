package com.test.em.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/*
 * +---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| paed_suid     | int(11)     | NO   | PRI | NULL    | auto_increment |
| paed_pae_suid | int(11)     | NO   |     | NULL    |                |
| paed_ppl_suid | int(11)     | NO   |     | NULL    |                |
| paed_err_msg  | text        | NO   |     | NULL    |                |
| paed_proc_seq | varchar(10) | NO   |     | NULL    |                |
| paed_pst_id   | varchar(10) | NO   |     | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+

 */

@Entity
@Table(name="process_activity_err_dtl")
public class ProcessActivityErrorDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1476768815543844537L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=" paed_suid ")
	private Long id;
	
	@Column(name="paed_pae_suid")
	private Long processActivityErrorId;
	
	@Transient
	private int level;
	
	@Column(name="paed_ppl_suid ")
	private int processLevelId;
	
	
	@Column(name="paed_proc_seq")
	private String processSeqquence;
	
	@Column(name="paed_err_msg")
	private String errorMessage;
	
	@Column(name="paed_pst_id ")
	private String statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcessActivityErrorId() {
		return processActivityErrorId;
	}

	public void setProcessActivityErrorId(Long processActivityErrorId) {
		this.processActivityErrorId = processActivityErrorId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getProcessLevelId() {
		return processLevelId;
	}

	public void setProcessLevelId(int processLevelId) {
		this.processLevelId = processLevelId;
	}

	public String getProcessSeqquence() {
		return processSeqquence;
	}

	public void setProcessSeqquence(String processSeqquence) {
		this.processSeqquence = processSeqquence;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	
	@Override
	public String toString() {
		return "ProcessActivityErrorDetail [id=" + id
				+ ", processActivityErrorId=" + processActivityErrorId
				+ ", level=" + level + ", processLevelId=" + processLevelId
				+ ", processSeqquence=" + processSeqquence + ", errorMessage="
				+ errorMessage + ", statusId=" + statusId + "]";
	}
	
}
