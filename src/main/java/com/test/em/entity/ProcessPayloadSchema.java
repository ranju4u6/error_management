package com.test.em.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
+----------------+-------------+------+-----+---------+----------------+
| Field          | Type        | Null | Key | Default | Extra          |
+----------------+-------------+------+-----+---------+----------------+
| pps_suid       | int(11)     | NO   | PRI | NULL    | auto_increment |
| pps_org_id     | varchar(20) | NO   |     | NULL    |                |
| pps_pro_id     | varchar(20) | NO   |     | NULL    |                |
| pps_src_sys_id | varchar(20) | NO   |     | NULL    |                |
| pps_schema     | text        | NO   |     | NULL    |                |
| created_user   | varchar(20) | YES  |     | NULL    |                |
| created_on     | datetime    | YES  |     | NULL    |                |
| updated_user   | varchar(20) | YES  |     | NULL    |                |
| updated_on     | datetime    | YES  |     | NULL    |                |
+----------------+-------------+------+-----+---------+----------------+


 */

@Entity
@Table(name="process_payload_schema")
public class ProcessPayloadSchema implements Serializable {


	private static final long serialVersionUID = -5558985324505744260L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pps_suid")
	private Long Id;
	
	@Column(name="pps_org_id")
	private String orgId;
	
	@Column(name="pps_pro_id")
	private String processId;
	
	@Column(name="pps_src_sys_id")
	private String srcSystemId;
	
	@Column(name="pps_schema")
	private String schema;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSrcSystemId() {
		return srcSystemId;
	}

	public void setSrcSystemId(String srcSystemId) {
		this.srcSystemId = srcSystemId;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	
	

}
