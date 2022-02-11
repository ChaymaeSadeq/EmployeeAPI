package com.example.EmployeeAPI.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.EmployeeAPI.model.Employee;

@RestController
@RequestMapping("/api3")
public class FetchEmployeeDataAPI {
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public static RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	private static final String GET_ALL_EMPLOYEES_API="http://localhost:8080/api1/employees";
	private static final String CREATE_EMPLOYEE_API="http://localhost:8080/api1/employees";
	private static final String UPDATE_EMPLOYEE_API="http://localhost:8080/api1/employees/{id}";
	private static final String DELETE_EMPLOYEE_API="http://localhost:8080/api1/employees/{id}";

	
//	@GetMapping("/employees")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Object> GetEmployees(){
		Object[] employees=restTemplate.getForObject(GET_ALL_EMPLOYEES_API, Object[].class, null, null);
		System.out.println("Get Employees from FetchData API");
		return Arrays.asList(employees);
		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> CreateEmployee(@RequestBody Employee employeeDetails) {
		
		HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_JSON);
		   Employee employee = new Employee();
		   employee.setFirstname(employeeDetails.getFirstname());
		   employee.setLastname(employeeDetails.getLastname());
		   employee.setEmailId(employeeDetails.getEmailId());
		   HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee, headers);
		   URI uri = restTemplate.postForLocation(CREATE_EMPLOYEE_API, requestEntity);
		   
		   return ResponseEntity.ok().body(employee);
	} 

//	@PutMapping("/employees/{id}")
//    private ResponseEntity<Employee> updateEmployee(@PathVariable (value="id") Long employeeId, @RequestBody Employee employeeDetails) {
//		
//		  HttpHeaders headers = new HttpHeaders();
//		  headers.setContentType(MediaType.APPLICATION_JSON);
//		  Employee employee = new Employee();
//		  employee.setFirstname(employeeDetails.getFirstname());
//		  employee.setLastname(employeeDetails.getLastname());
//		  employee.setEmailId(employeeDetails.getEmailId());
//		  HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee, headers);
//		  restTemplate.put(UPDATE_EMPLOYEE_API, requestEntity);
//        
//		return ResponseEntity.ok().body(employee);
//    }

//	@DeleteMapping("/employees/{id}")
//    private ResponseEntity deleteEmployee() {
//		System.out.println("Hello from DELETE");
//		 HttpHeaders headers = new HttpHeaders();
//		  headers.setContentType(MediaType.APPLICATION_JSON);
//		   Employee employee = new Employee();
//		  HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(headers);
//		  restTemplate.exchange(DELETE_EMPLOYEE_API, HttpMethod.DELETE, requestEntity, Void.class, 101); 
//		  return ResponseEntity.ok().body(employee);
//    }
	
	
//	public static void main(String[] args) {
//		SpringApplication.run(FetchEmployeeDataAPI.class, args);
//		System.out.println("Welcome to fetch Data API");
//	}
	
}
