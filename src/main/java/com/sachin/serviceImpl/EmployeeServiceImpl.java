package com.sachin.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sachin.entity.Employee;
import com.sachin.repo.EmployeeRepository;
import com.sachin.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	} 

	@Override
	public Employee getEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);

		if (optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} else {
			throw new IllegalArgumentException("Employee not found with ID: " + id);
		}
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = employeeRepository.findById(id).orElse(null);

		if (existingEmployee != null) {
			existingEmployee.setEmployeeName(employee.getEmployeeName());
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setMobileNumber(employee.getMobileNumber());
			existingEmployee.setDesignation(employee.getDesignation());

			employeeRepository.save(existingEmployee);
		} else {
			throw new IllegalArgumentException("Employee not found with ID: " + id);
		}
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		return this.employeeRepository.findAll(pageable);
	}
}