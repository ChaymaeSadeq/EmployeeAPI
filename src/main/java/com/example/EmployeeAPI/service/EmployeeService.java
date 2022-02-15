package com.example.EmployeeAPI.service;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.EmployeeAPI.dto.EmployeeDTO;
import com.example.EmployeeAPI.exception.ResourceNotFound;
import com.example.EmployeeAPI.model.Employee;

public interface EmployeeService {
	
	public List<EmployeeDTO> getAllEmployees();
	
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long id);
	
	public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO Employeedto);
	
	public void UpdateEmployee (@PathVariable (value="id") Long id, @RequestBody EmployeeDTO employeeDetails) ;
	
	public String DeleteEmployee(@PathVariable (value="id") Long id) ;

}
