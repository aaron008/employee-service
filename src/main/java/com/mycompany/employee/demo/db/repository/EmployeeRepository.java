package com.mycompany.employee.demo.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mycompany.employee.demo.model.EmployeeDTO;

public interface EmployeeRepository extends MongoRepository<EmployeeDTO, String>{
	
	public List<EmployeeDTO> findAll();
	public Optional<EmployeeDTO> findById(String id);
	public <S extends EmployeeDTO> S save(EmployeeDTO employeeDTO);

}
