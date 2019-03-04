package com.mycompany.employee.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.employee.demo.db.repository.EmployeeRepository;
import com.mycompany.employee.demo.exception.NoEmployeeFoundException;
import com.mycompany.employee.demo.model.Employee;
import com.mycompany.employee.demo.model.EmployeeDTO;
import com.mycompany.employee.demo.model.SaveEmployeeRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@HystrixCommand(groupKey = "EmployeeService", commandKey = "GetAllEmployeesCommand")
	public List<Employee> getAllEmployees() {

		List<EmployeeDTO> employeeDTOList = employeeRepository.findAll();
		List<Employee> employeeList = new ArrayList<>();

		for (EmployeeDTO employeeDTO : employeeDTOList) {
			Employee employee = mapEmployee(employeeDTO);
			employeeList.add(employee);
		}

		return employeeList;
	}

	@HystrixCommand(groupKey = "EmployeeService", commandKey = "GetAllEmployeeByIdCommand")
	public Employee getEmployeeById(String id) {
		Optional<EmployeeDTO> optionalEmployeeDTO = employeeRepository.findById(id);
		Employee employee = null;

		if (optionalEmployeeDTO.isPresent()) {
			employee = mapEmployee(optionalEmployeeDTO.get());
		}
		return employee;
	}

	@HystrixCommand(groupKey = "EmployeeService", commandKey = "CreateEmployeeCommand")
	public Employee createEmployee(SaveEmployeeRequest request) {

		EmployeeDTO employeeDTO = mapSaveEmployeeDTO(request);
		EmployeeDTO savedEmployeeDTO = employeeRepository.save(employeeDTO);

		return mapEmployee(savedEmployeeDTO);
	}

	@HystrixCommand(groupKey = "EmployeeService", commandKey = "updateEmployeeCommand")
	public Employee updateEmployee(SaveEmployeeRequest request) throws NoEmployeeFoundException {

		Optional<EmployeeDTO> optionalEmployeeDTO = employeeRepository.findById(request.getId());
		Employee employee = null;

		if (optionalEmployeeDTO.isPresent()) {

			EmployeeDTO employeeDTO = mapUpdateEmployeeDTO(optionalEmployeeDTO.get(), request);
			EmployeeDTO savedEmployeeDTO = employeeRepository.save(employeeDTO);
			employee = mapEmployee(savedEmployeeDTO);

		} else {
			throw new NoEmployeeFoundException("No Employee found");
		}

		return employee;
	}

	@HystrixCommand(groupKey = "EmployeeService", commandKey = "deleteEmployeeCommand")
	public boolean deleteEmployee(String id) {
		employeeRepository.deleteById(id);
		return true;
	}

	protected EmployeeDTO mapUpdateEmployeeDTO(EmployeeDTO employeeDTO, SaveEmployeeRequest request) {

		if (request.getAddress1() != null) {
			employeeDTO.setAddress1(request.getAddress1());
		}

		if (request.getAddress2() != null) {
			employeeDTO.setAddress2(request.getAddress2());
		}

		if (request.getCity() != null) {
			employeeDTO.setCity(request.getCity());
		}

		if (request.getState() != null) {
			employeeDTO.setState(request.getState());
		}

		if (request.getZip() != null) {
			employeeDTO.setZip(request.getZip());
		}

		if (request.getEmail() != null) {
			employeeDTO.setEmail(request.getEmail());
		}

		if (request.getFirstName() != null) {
			employeeDTO.setFirstName(request.getFirstName());
		}

		if (request.getLastName() != null) {
			employeeDTO.setLastName(request.getLastName());
		}

		if (request.getPhone() != null) {
			employeeDTO.setPhone(request.getPhone());
		}

		if (request.getEmployeeId() != null) {
			employeeDTO.setEmployeeId(request.getEmployeeId());
		}

		if (request.getSSN() != null) {
			employeeDTO.setSSN(request.getSSN());
		}

		if (request.getTitle() != null) {
			employeeDTO.setTitle(request.getTitle());
		}

		return employeeDTO;
	}

	protected EmployeeDTO mapSaveEmployeeDTO(SaveEmployeeRequest employee) {

		EmployeeDTO employeeDTO = EmployeeDTO.builder().address1(employee.getAddress1())
				.address2(employee.getAddress2()).city(employee.getCity()).state(employee.getState())
				.zip(employee.getZip()).email(employee.getEmail()).employeeId(employee.getEmployeeId())
				.firstName(employee.getFirstName()).lastName(employee.getLastName()).phone(employee.getPhone())
				.SSN(employee.getSSN()).title(employee.getTitle()).build();
		return employeeDTO;
	}

	protected Employee mapEmployee(EmployeeDTO employeeDTO) {
		return Employee.builder().id(employeeDTO.getId()).address1(employeeDTO.getAddress1())
				.address2(employeeDTO.getAddress2()).city(employeeDTO.getCity()).state(employeeDTO.getState())
				.zip(employeeDTO.getZip()).email(employeeDTO.getEmail()).firstName(employeeDTO.getFirstName())
				.lastName(employeeDTO.getLastName()).phone(employeeDTO.getPhone())
				.employeeId(employeeDTO.getEmployeeId()).title(employeeDTO.getTitle()).build();
	}
}
