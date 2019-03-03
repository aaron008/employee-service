package com.mycompany.employee.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.employee.demo.db.repository.EmployeeRepository;
import com.mycompany.employee.demo.model.EmployeeDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@HystrixCommand(groupKey="EmployeeService", commandKey = "GetAllEmployeesCommand")
	public List<EmployeeDTO> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	@HystrixCommand(groupKey="EmployeeService", commandKey = "GetAllEmployeesByIdCommand")
	public Optional<EmployeeDTO> getEmployeeById(String id){
		return employeeRepository.findById(id);
	}
	
}
