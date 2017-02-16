package com.test.em.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * +--------------+-------------+------+-----+---------+----------------+
| Field        | Type        | Null | Key | Default | Extra          |
+--------------+-------------+------+-----+---------+----------------+
| ppl_suid     | int(11)     | NO   | PRI | NULL    | auto_increment |
| ppl_pps_suid | int(11)     | NO   |     | NULL    |                |
| ppl_level    | int(11)     | NO   |     | NULL    |                |
| ppl_uischema | text        | NO   |     | NULL    |                |
| created_user | varchar(20) | YES  |     | NULL    |                |
| created_on   | datetime    | YES  |     | NULL    |                |
| updated_user | varchar(20) | YES  |     | NULL    |                |
| updated_on   | datetime    | YES  |     | NULL    |                |
+--------------+-------------+------+-----+---------+----------------+

 * 
 */
@Entity
@Table(name="process_payload_level")
public class ProcessPayloadLevel implements Serializable{
	

	private static final long serialVersionUID = -7892736630465475512L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ppl_suid")
	private Long id;
	
	@Column(name="ppl_pps_suid")
	private Long schemaId;
	
	@Column(name="ppl_level")
	private Integer levelNumber;
	
	@Column(name="ppl_uischema")
	private String uiSchema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(Long schemaId) {
		this.schemaId = schemaId;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getUiSchema() {
		return uiSchema;
	}

	public void setUiSchema(String uiSchema) {
		this.uiSchema = uiSchema;
	}


	@Override
	public String toString() {
		return "ProcessPayloadLevel [id=" + id + ", schemaId=" + schemaId
				+ ", levelNumber=" + levelNumber + ", uiSchema=" + uiSchema
				+ "]";
	}
	
	

}
