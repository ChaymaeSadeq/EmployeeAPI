package com.example.EmployeeAPI.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.EmployeeAPI.model.Employee;

public class EmployeeDTO {
	
	public EmployeeDTO(long id,String firstname, String lastname,String emailId) {
		super();
		this.id=id;
		this.Firstname = firstname;
		this.Lastname = lastname;
		this.emailId = emailId;
	}

	@NotNull
	private long id;

	@NotNull
	@Length(min = 3, max = 20, message = "The number of character in firstname property should be between 3 and 20")
	private String Firstname;

	@NotNull
	@Length(min = 3, max = 20, message = "The number of character in lastname property should be between 3 and 20")
	private String Lastname;

	@NotNull
	private String emailId;

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
	
	public Employee convertToEmployee() {
		return new Employee(id,Firstname, Lastname, emailId);
	}
	
	public EmployeeDTO build(Employee employee) {
		return new EmployeeDTO(employee.getId(), employee.getFirstname(),employee.getLastname(), employee.getEmailId());
	}

}
