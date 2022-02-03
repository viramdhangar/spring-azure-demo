package com.docker.springdockerd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.springdockerd.dto.Employee;
import com.docker.springdockerd.repository.EmployeeRepository;

@SpringBootApplication
@RestController
public class SpringDockerDApplication {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDockerDApplication.class, args);
	}

}
