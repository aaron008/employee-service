package com.mycompany.employee.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.employee.demo.db.repository.EmployeeRepository;
import com.mycompany.employee.demo.exception.NoEmployeeFoundException;
import com.mycompany.employee.demo.model.Employee;
import com.mycompany.employee.demo.model.EmployeeDTO;
import com.mycompany.employee.demo.model.SaveEmployeeRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeServiceImpl service;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@After
	public void cleanUp() {
		employeeRepository.deleteAll();
	}
	
	@Test
	public void shouldReturnCreatedEmployee_whenCreateEmployee_isInvoked() {
		
		SaveEmployeeRequest request = getCreateRequest();
		Employee createEmployee = service.createEmployee(request);
		
		Optional<EmployeeDTO> oEmployeeDTO = employeeRepository.findById(createEmployee.getId());
		EmployeeDTO employeeDTO = oEmployeeDTO.get();
		
		assertThat(employeeDTO.getFirstName()).isEqualTo(request.getFirstName());
		assertThat(employeeDTO.getLastName()).isEqualTo(request.getLastName());
		assertThat(employeeDTO.getAddress1()).isEqualTo(request.getAddress1());
		assertThat(employeeDTO.getAddress2()).isEqualTo(request.getAddress2());
		assertThat(employeeDTO.getCity()).isEqualTo(request.getCity());
		assertThat(employeeDTO.getState()).isEqualTo(request.getState());
		assertThat(employeeDTO.getPhone()).isEqualTo(request.getPhone());
		assertThat(employeeDTO.getZip()).isEqualTo(request.getZip());
		assertThat(employeeDTO.getEmail()).isEqualTo(request.getEmail());
		assertThat(employeeDTO.getEmployeeId()).isEqualTo(request.getEmployeeId());
		assertThat(employeeDTO.getTitle()).isEqualTo(request.getTitle());
		assertThat(employeeDTO.getSSN()).isEqualTo(request.getSSN());
	}

	@Test
	public void shouldReturnEmployee_whenGetEmployeeById_isInvoked() {
		
		//Save employee to MongoDb
		SaveEmployeeRequest saveEmployeeRequest = getCreateRequest();
		EmployeeDTO employeeDTO = service.mapSaveEmployeeDTO(saveEmployeeRequest);
		
		EmployeeDTO savedEmployeeDTO = employeeRepository.save(employeeDTO);
		
		//Get employee through service from MongoDb
		Employee employee = service.getEmployeeById(savedEmployeeDTO.getId());
		
		assertThat(employee.getFirstName()).isEqualTo(savedEmployeeDTO.getFirstName());
		assertThat(employee.getLastName()).isEqualTo(savedEmployeeDTO.getLastName());
		assertThat(employee.getAddress1()).isEqualTo(savedEmployeeDTO.getAddress1());
		assertThat(employee.getAddress2()).isEqualTo(savedEmployeeDTO.getAddress2());
		assertThat(employee.getCity()).isEqualTo(savedEmployeeDTO.getCity());
		assertThat(employee.getState()).isEqualTo(savedEmployeeDTO.getState());
		assertThat(employee.getPhone()).isEqualTo(savedEmployeeDTO.getPhone());
		assertThat(employee.getZip()).isEqualTo(savedEmployeeDTO.getZip());
		assertThat(employee.getEmail()).isEqualTo(savedEmployeeDTO.getEmail());
		assertThat(employee.getEmployeeId()).isEqualTo(savedEmployeeDTO.getEmployeeId());
		assertThat(employee.getTitle()).isEqualTo(savedEmployeeDTO.getTitle());
		
	}
	
	@Test
	public void shouldReturnAllEmployees_whenGetAllEmployees_isInvoked() {
		
		//Save employees to MongoDb
		SaveEmployeeRequest saveEmployeeRequest1 = getCreateRequest();
		saveEmployeeRequest1.setFirstName("Employee1_FirstName");
		saveEmployeeRequest1.setLastName("Employee1_LastName");
		saveEmployeeRequest1.setEmployeeId("Employee1_employeeId");
		
		EmployeeDTO saveEmployeeDTO = service.mapSaveEmployeeDTO(saveEmployeeRequest1);
		employeeRepository.save(saveEmployeeDTO);
		
		SaveEmployeeRequest saveEmployeeRequest2 = getCreateRequest();
		saveEmployeeRequest2.setFirstName("Employee2_FirstName");
		saveEmployeeRequest2.setLastName("Employee2_LastName");
		saveEmployeeRequest2.setEmployeeId("Employee2_employeeId");
		
		saveEmployeeDTO = service.mapSaveEmployeeDTO(saveEmployeeRequest2);
		employeeRepository.save(saveEmployeeDTO);
		
		//Get all employees through service from MongoDb
		List<Employee> employeeList = service.getAllEmployees();
		
		assertThat(employeeList.size()).isEqualTo(2);
		assertThat(employeeList.get(0).getFirstName()).isEqualTo("Employee1_FirstName");
		assertThat(employeeList.get(0).getLastName()).isEqualTo("Employee1_LastName");
		assertThat(employeeList.get(0).getAddress1()).isEqualTo("MyAddress1");
		assertThat(employeeList.get(0).getAddress2()).isEqualTo("MyAddress2");
		assertThat(employeeList.get(0).getCity()).isEqualTo("MyCity");
		assertThat(employeeList.get(0).getState()).isEqualTo("MyState");
		assertThat(employeeList.get(0).getPhone()).isEqualTo("999-999-9999");
		assertThat(employeeList.get(0).getZip()).isEqualTo("MyZip");
		assertThat(employeeList.get(0).getEmail()).isEqualTo("Myemail@email.com");
		assertThat(employeeList.get(0).getEmployeeId()).isEqualTo("Employee1_employeeId");
		assertThat(employeeList.get(0).getTitle()).isEqualTo("MyTitle");
		
		assertThat(employeeList.get(1).getFirstName()).isEqualTo("Employee2_FirstName");
		assertThat(employeeList.get(1).getLastName()).isEqualTo("Employee2_LastName");
		assertThat(employeeList.get(1).getAddress1()).isEqualTo("MyAddress1");
		assertThat(employeeList.get(1).getAddress2()).isEqualTo("MyAddress2");
		assertThat(employeeList.get(1).getCity()).isEqualTo("MyCity");
		assertThat(employeeList.get(1).getState()).isEqualTo("MyState");
		assertThat(employeeList.get(1).getPhone()).isEqualTo("999-999-9999");
		assertThat(employeeList.get(1).getZip()).isEqualTo("MyZip");
		assertThat(employeeList.get(1).getEmail()).isEqualTo("Myemail@email.com");
		assertThat(employeeList.get(1).getEmployeeId()).isEqualTo("Employee2_employeeId");
		assertThat(employeeList.get(1).getTitle()).isEqualTo("MyTitle");
	}
	
	@Test
	public void shouldReturnUpdateEmployee_whenUpdateEmployee_isInvoked() throws NoEmployeeFoundException {
		//Save employee to MongoDb
		SaveEmployeeRequest saveEmployeeRequest = getCreateRequest();
		
		EmployeeDTO saveEmployeeDTO = service.mapSaveEmployeeDTO(saveEmployeeRequest);
		employeeRepository.save(saveEmployeeDTO);
		
		//Update employee through service from MongoDb
		SaveEmployeeRequest updatedEmployeeRequest = getCreateRequest();
		updatedEmployeeRequest.setAddress1("NewAddress1");
		updatedEmployeeRequest.setAddress2("NewAddress2");
		updatedEmployeeRequest.setCity("NewCity");
		updatedEmployeeRequest.setState("NewState");
		updatedEmployeeRequest.setZip("NewZip");
		updatedEmployeeRequest.setPhone("111-111-1111");
		updatedEmployeeRequest.setId(saveEmployeeDTO.getId());
		
		Employee employee = service.updateEmployee(updatedEmployeeRequest);
		
		assertThat(employee.getFirstName()).isEqualTo("MyFirstName");
		assertThat(employee.getLastName()).isEqualTo("MyLastName");
		assertThat(employee.getAddress1()).isEqualTo("NewAddress1");
		assertThat(employee.getAddress2()).isEqualTo("NewAddress2");
		assertThat(employee.getCity()).isEqualTo("NewCity");
		assertThat(employee.getState()).isEqualTo("NewState");
		assertThat(employee.getPhone()).isEqualTo("111-111-1111");
		assertThat(employee.getZip()).isEqualTo("NewZip");
		assertThat(employee.getEmail()).isEqualTo("Myemail@email.com");
		assertThat(employee.getEmployeeId()).isEqualTo("employeeID");
		assertThat(employee.getTitle()).isEqualTo("MyTitle");
	}
		
	@Test
	public void shouldReturnTrue_whenDeleteEmployee_isInvoked_withInvalid_id() throws NoEmployeeFoundException {
		//Save employee to MongoDb
		SaveEmployeeRequest saveEmployeeRequest = getCreateRequest();
		
		EmployeeDTO saveEmployeeDTO = service.mapSaveEmployeeDTO(saveEmployeeRequest);
		employeeRepository.save(saveEmployeeDTO);
		
		//Delete employee through service from MongoDb
		boolean success = service.deleteEmployee(saveEmployeeDTO.getId());
		
		Optional<EmployeeDTO> oEmployeeDTO = employeeRepository.findById(saveEmployeeDTO.getId());
		
		assertThat(success).isTrue();
		assertThat(oEmployeeDTO.isPresent()).isFalse();
	}
	
	private SaveEmployeeRequest getCreateRequest() {
		return SaveEmployeeRequest.builder()
								.address1("MyAddress1")
								.address2("MyAddress2")
								.firstName("MyFirstName")
								.lastName("MyLastName")
								.city("MyCity")
								.state("MyState")
								.zip("MyZip")
								.phone("999-999-9999")
								.email("Myemail@email.com")
								.employeeId("employeeID")
								.title("MyTitle")
								.SSN("MySSN")
								.build();
	}

}