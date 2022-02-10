package com.example.EmployeeAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployeeAPI.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

