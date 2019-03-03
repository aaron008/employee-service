package com.mycompany.employee.demo.service;

import java.util.List;

import com.mycompany.employee.demo.exception.NoEmployeeFoundException;
import com.mycompany.employee.demo.model.Employee;
import com.mycompany.employee.demo.model.SaveEmployeeRequest;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(String id);
	public Employee updateEmployee(SaveEmployeeRequest request) throws NoEmployeeFoundException;
	public Employee createEmployee(SaveEmployeeRequest request);
	public void deleteEmployee(String id);
}
