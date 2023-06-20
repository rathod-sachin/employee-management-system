package com.sachin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sachin.entity.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);

	Employee getEmployee(Long id);

	List<Employee> getEmployees();

	void updateEmployee(Long id, Employee employee);

	void deleteEmployee(Long id);

	Page<Employee> findPaginated(int pageNo, int pageSize);
}