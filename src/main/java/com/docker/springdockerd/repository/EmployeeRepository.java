package com.docker.springdockerd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.springdockerd.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
