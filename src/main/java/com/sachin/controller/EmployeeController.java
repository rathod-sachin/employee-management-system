package com.sachin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sachin.entity.Designation;
import com.sachin.entity.Employee;
import com.sachin.service.DesignationService;
import com.sachin.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DesignationService designationService;

	@GetMapping
	public String getEmployees(Model model) {
		return findPaginated(1, model);
	}

	@GetMapping("/new")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		List<Designation> designationList = designationService.getDesignations();
		model.addAttribute("employee", employee);
		model.addAttribute("designationList", designationList);
		return "newEmployee";
	}

	@PostMapping("/new")
	public String addEmployee(@Valid Employee employee) {
		try {
			validateEmployee(employee);
			employeeService.addEmployee(employee);
			return "redirect:/employees";
		} catch (Exception e) {
			return "newEmployee";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}

	@GetMapping("/update/{id}")
	public String showUpdateEmployeeForm(@PathVariable("id") Long id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		List<Designation> designationList = designationService.getDesignations();
		model.addAttribute("employee", employee);
		model.addAttribute("designationList", designationList);
		return "updateEmployee";
	}

	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
		employeeService.updateEmployee(id, employee);
		return "redirect:/employees";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNumber, Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize);
		List<Employee> employeeList = page.getContent();

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("employeeList", employeeList);
		return "viewEmployee";
	}

	private void validateEmployee(Employee employee) {
		Assert.hasLength(employee.getEmail(), "Email must not be empty");
		Assert.hasLength(employee.getMobileNumber(), "Mobile Number must not be empty");
		Assert.hasLength(employee.getEmployeeName(), "Employee Name must not be empty");
		Assert.notNull(employee.getDesignation(), "Employee designation must not be null");
	}
}