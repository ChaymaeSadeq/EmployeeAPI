package com.example.EmployeeAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeAPI.dto.EmployeeDTO;
import com.example.EmployeeAPI.exception.ResourceNotFound;
import com.example.EmployeeAPI.model.Employee;
import com.example.EmployeeAPI.repository.EmployeeRepository;
import com.example.EmployeeAPI.service.EmployeeService;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

@RestController
@RequestMapping("/api1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<EmployeeDTO> GetEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	public EmployeeDTO GetEmployeesById(@PathVariable long id) throws ResourceNotFound{
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> UpdateEmployees(@PathVariable("id") long id, @RequestBody Employee employee) {

		EmployeeDTO employeeData = employeeService.getEmployeeById(id);
		EmployeeDTO employeeDto = employeeData;

		employeeDto.setFirstname(employee.getFirstname());
		employeeDto.setLastname(employee.getLastname());
		employeeDto.setEmailId(employee.getEmailId());
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<EmployeeDTO> AddEmployees(@RequestBody Employee employee) throws ResourceNotFound{

		EmployeeDTO employeedto = employeeService.createEmployee(employee.convertToEmployeeDto());
		return new ResponseEntity<>(employeedto, HttpStatus.CREATED);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> DeleteEmployees(@PathVariable("id") long id) throws ResourceNotFound{
		employeeService.DeleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
