package com.mycompany.employee.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.employee.demo.exception.NoEmployeeFoundException;
import com.mycompany.employee.demo.model.BasicResponse;
import com.mycompany.employee.demo.model.Employee;
import com.mycompany.employee.demo.model.SaveEmployeeRequest;
import com.mycompany.employee.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(value = "/employees")
	public ResponseEntity<BasicResponse> getAllEmployees() {

		List<Employee> allEmployees = employeeService.getAllEmployees();
		BasicResponse response = BasicResponse.builder()
												.data(allEmployees)
												.status("SUCCESS")
												.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<BasicResponse> getEmployeeById(@PathVariable("id") String id) {

		Employee employee = employeeService.getEmployeeById(id);
		BasicResponse response = BasicResponse.builder()
												.data(employee)
												.status("SUCCESS")
												.build();
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/employees")
	public ResponseEntity<BasicResponse> createEmployee(@Valid SaveEmployeeRequest request) {
		Employee createEmployee = employeeService.createEmployee(request);
		BasicResponse response = BasicResponse.builder()
												.data(createEmployee)
												.status("SUCCESS")
												.build();

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<BasicResponse> updateEmployeeById(@PathVariable("id") String id,
			@Valid SaveEmployeeRequest request) throws NoEmployeeFoundException {

		employeeService.updateEmployee(request);
		BasicResponse response = BasicResponse.builder()
												.status("SUCCESS")
												.build();

		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/employees/{id}")
	public ResponseEntity<BasicResponse> updateEmployeeById(@PathVariable("id") String id){
		employeeService.deleteEmployee(id);
		BasicResponse response = BasicResponse.builder()
												.status("SUCCESS")
												.build();

		return ResponseEntity.ok(response);
	}
}
