package com.test.em.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="process_activity_hdr")
public class ProcessActivityHeader implements Serializable {

	@Id
	@Column(name="pah_suid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="pah_id")
	private String processActivityHeaderId;
	
	@Column(name="pah_org_id")
    private String organizationId;

	@Column(name="pah_origin_date")
    private Date originalProcessDatetime;

	@Column(name="pah_pro_id")
    private String processId;

	@Column(name="pah_src_sys_id")
    private String sourceSystemId;

	@Column(name="pah_dst_sys_id")
    private String destinationSystemId;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessActivityHeaderId() {
		return processActivityHeaderId;
	}

	public void setProcessActivityHeaderId(String processActivityHeaderId) {
		this.processActivityHeaderId = processActivityHeaderId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Date getOriginalProcessDatetime() {
		return originalProcessDatetime;
	}

	public void setOriginalProcessDatetime(Date originalProcessDatetime) {
		this.originalProcessDatetime = originalProcessDatetime;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getDestinationSystemId() {
		return destinationSystemId;
	}

	public void setDestinationSystemId(String destinationSystemId) {
		this.destinationSystemId = destinationSystemId;
	}

	@Override
	public String toString() {
		return "Entity: ProcessActivityHeader [id=" + id + ", processActivityHeaderId="
				+ processActivityHeaderId + ", organizationId="
				+ organizationId + ", originalProcessDatetime="
				+ originalProcessDatetime + ", processId=" + processId
				+ ", sourceSystemId=" + sourceSystemId
				+ ", destinationSystemId=" + destinationSystemId + "]";
	}
	
	
}
