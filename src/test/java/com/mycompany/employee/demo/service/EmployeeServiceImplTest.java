package com.mycompany.employee.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.employee.demo.db.repository.EmployeeRepository;
import com.mycompany.employee.demo.model.Employee;
import com.mycompany.employee.demo.model.SaveEmployeeRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeServiceImpl service;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void shouldCreateEmployee_whenCreateEmployee_isInvoked() {
		
		SaveEmployeeRequest request = SaveEmployeeRequest.builder()
														.address1("MyAddress1")
														.address2("MyAddress2")
														.firstName("MyFirstName")
														.lastName("MyLastName")
														.build();
		Employee createEmployee = service.createEmployee(request);
		
		employeeRepository.findById(createEmployee.getId());
		assertThat(createEmployee.getFirstName()).isEqualTo("MyFirstName");
	}

}