package com.mycompany.employee.demo.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mycompany.employee.demo.model.EmployeeDTO;

public interface EmployeeRepository extends MongoRepository<EmployeeDTO, String>{
	
}
