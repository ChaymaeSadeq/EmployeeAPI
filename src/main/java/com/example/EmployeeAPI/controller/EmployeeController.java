package com.example.EmployeeAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.EmployeeAPI.exception.ResourceNotFound;
import com.example.EmployeeAPI.model.Employee;
import com.example.EmployeeAPI.repository.EmployeeRepository;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;



@RestController
@RequestMapping("/api1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRep;
	
	//Get all employees 
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		System.out.println("Hello from GET");
		return employeeRep.findAll();
		
	}
	
	//Get employees by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFound {
		System.out.println("Hello from GETBYID");
		Employee employee = employeeRep.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFound("Employee with id = "	+ employeeId + " was not found"));
		return ResponseEntity.ok().body(employee);
	}
	
	//Create employee
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		System.out.println("Hello from POST");
		return employeeRep.save(employee);
	}
	
	
//	@PutMapping(name="/employees/{id}")
	@RequestMapping(value = "/employees/{id}", 
	  produces = "application/json", 
	  method=RequestMethod.PUT)
	
	public ResponseEntity<Employee> UpdateEmployee(@PathVariable (value="id") Long employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFound {
		System.out.println("Hello from PUT");
		Employee employee= employeeRep.findById(employeeId).orElseThrow(()->new ResourceNotFound("Employee with id = "	+ employeeId + " was not found"));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmailId(employeeDetails.getEmailId());
		employeeRep.save(employee);
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity DeleteEmployee(@PathVariable (value="id") Long employeeId) throws ResourceNotFound{
		System.out.println("Hello from DELETE");
		Employee employee= employeeRep.findById(employeeId).orElseThrow(()->new ResourceNotFound("Employee with id = "	+ employeeId + " was not found"));
		employeeRep.deleteById(employeeId);
		return ResponseEntity.ok().body(employee);
	}
}

