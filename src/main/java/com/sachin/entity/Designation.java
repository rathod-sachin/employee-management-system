package com.sachin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "designation")
public class Designation {

	@Id
	@Column(name = "designation_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long designationId;

	@Column(name = "name")
	private String designationName;

	public Designation() {
		super();
	}

	public Designation(String designationName) {
		super();
		this.designationName = designationName;
	}

	public Designation(Long designationId, String designationName) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "Designation [designationId=" + designationId + ", designationName=" + designationName + "]";
	}
}
