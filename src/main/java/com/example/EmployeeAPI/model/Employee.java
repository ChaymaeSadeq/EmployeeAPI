package com.example.EmployeeAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.EmployeeAPI.dto.EmployeeDTO;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public Employee(long id, String firstname, String lastname, String emailId) {
		super();
		this.id=id;
		this.Firstname = firstname;
		this.Lastname = lastname;
		this.emailId = emailId;
	}

	@NotNull
	@Length(min=3, max=20, message = "The number of character in firstname property should be between 3 and 20")
	@Column(name = "firstname")
	private String Firstname;

	@NotNull
	@Length(min=3, max=20, message = "The number of character in lastname property should be between 3 and 20")
	@Column(name = "lastname")
	private String Lastname;

	@NotNull
	@Column(name = "emailid")
	private String emailId;

	public Employee() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public EmployeeDTO convertToEmployeeDto() {
		return new EmployeeDTO(id,Firstname, Lastname, emailId);
	}
	
	public Employee updateFromDto(EmployeeDTO employeeDto) {
		this.setId(employeeDto.getId());
		this.setFirstname(employeeDto.getFirstname());
		this.setLastname(employeeDto.getLastname());
		this.setEmailId(employeeDto.getEmailId());

		return this;
	}

}
