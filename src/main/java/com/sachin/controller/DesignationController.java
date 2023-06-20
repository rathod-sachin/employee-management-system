package com.sachin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sachin.entity.Designation;
import com.sachin.service.DesignationService;

@Controller
@RequestMapping("/designations")
public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@GetMapping
	public String getDesignations(Model model) {
		return findPaginated(1, model);
	}

	@GetMapping("/new")
	public String showNewDesignationForm(Model model) {
		model.addAttribute("designation", new Designation());
		return "newDesignation";
	}

	@PostMapping("/new")
	public String addDesignation(@ModelAttribute("designation") Designation designation) {
		designationService.addDesignation(designation);
		return "redirect:/designations";
	}

	@PostMapping("/delete/{id}")
	public String deleteDesignation(@PathVariable("id") Long id) {

		designationService.deleteDesignation(id);
		return "redirect:/designations";
	}

	@GetMapping("/update/{id}")
	public String showUpdateDesignationForm(@PathVariable("id") Long id, Model model) {
		Designation designation = designationService.getDesignation(id);
		model.addAttribute("designation", designation);
		return "updateDesignation";
	}

	@PostMapping("/update/{id}")
	public String updateDesignation(@PathVariable("id") Long id,
			@ModelAttribute("designation") Designation designation) {
		designationService.updateDesignation(id, designation);
		return "redirect:/designations";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNumber, Model model) {

		int pageSize = 5;

		Page<Designation> page = designationService.findPaginated(pageNumber, pageSize);
		List<Designation> designationList = page.getContent();

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("designationList", designationList);
		return "viewDesignation";
	}
}