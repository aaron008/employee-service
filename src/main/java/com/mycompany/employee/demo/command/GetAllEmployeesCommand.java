package com.mycompany.employee.demo.command;

import java.util.List;

import com.mycompany.employee.demo.db.repository.EmployeeRepository;
import com.mycompany.employee.demo.model.EmployeeDTO;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class GetAllEmployeesCommand extends HystrixCommand<List<EmployeeDTO>>{
	
	private EmployeeRepository employeeRepository;
	
	public GetAllEmployeesCommand(EmployeeRepository employeeRepository) {
		super("GetAllEmployeesCommand");
		this.employeeRepository = employeeRepository;
	}

	@Override
	protected List<EmployeeDTO> run() throws Exception {
		
		return employeeRepository.findAll();
	}

	
}
