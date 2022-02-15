package com.example.EmployeeAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.EmployeeAPI.dto.EmployeeDTO;
import com.example.EmployeeAPI.model.Employee;
import com.example.EmployeeAPI.repository.EmployeeRepository;

@Service 
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRep;
	
	@Override
	public List<EmployeeDTO> getAllEmployees(){
		System.out.println("Hello from GET Employee");
		
		List<Employee> employee = employeeRep.findAll();
		List<EmployeeDTO> employeedto = new ArrayList<>();
		for (Employee e:employee) {
			employeedto.add(new EmployeeDTO(e.getId(),e.getFirstname(), e.getLastname(), e.getEmailId()));
		}

		return employeedto;
		
	}
	
	@Override
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long id) {
		System.out.println("Hello from GETBYID Employee");
		Optional<Employee> employeeWanted = employeeRep.findById(id);
		EmployeeDTO emp= new EmployeeDTO(employeeWanted.get().getId() ,employeeWanted.get().getFirstname(), employeeWanted.get().getLastname(), employeeWanted.get().getEmailId());
			return emp;
		
	}
	
	@Override
	public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeedto) {
		System.out.println("Hello from POST Employee");
		Employee employee = employeeRep.save(employeedto.convertToEmployee());
		return employee.convertToEmployeeDto();	
		}
	
	@Override
	public void UpdateEmployee(@PathVariable (value="id") Long id, @RequestBody EmployeeDTO employeedto) {
		
		Optional<Employee> employee = employeeRep.findById(employeedto.getId());
			Employee newemployee = employee.get().updateFromDto(employeedto);
			Employee savedemployee = employeeRep.save(newemployee);
			savedemployee.convertToEmployeeDto();
	}
	
	@Override
	public String DeleteEmployee(@PathVariable (value="id") Long id){
		System.out.println("Hello from DELETE Employee");
		employeeRep.existsById(id);
		employeeRep.deleteById(id);
		
		return "Employee deleted with id : " + id;
	}

    
}
