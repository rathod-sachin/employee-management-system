package com.sachin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	@Column(name = "name")
	@NotBlank(message = "name can't be empty")
	private String employeeName;

	@Column(name = "email")
	@NotBlank(message = "email can't be empty")
	private String email;

	@Column(name = "mobile")
	@NotBlank(message = "mobile number can't be empty")
	private String mobileNumber;

	@ManyToOne
	@JoinColumn(name = "designation_id")
	private Designation designation;

	public Employee() {
		super();
	}

	public Employee(String employeeName, String email, String mobileNumber, Designation designation) {
		super();
		this.employeeName = employeeName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.designation = designation;
	}

	public Employee(Long employeeId, String employeeName, String email, String mobileNumber, Designation designation) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.designation = designation;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", designation=" + designation + "]";
	}
}