package com.sachin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sachin.entity.Designation;

public interface DesignationService {

	void addDesignation(Designation designation);

	Designation getDesignation(Long id);

	List<Designation> getDesignations();

	void updateDesignation(Long id, Designation designation);

	void deleteDesignation(Long id);

	Page<Designation> findPaginated(int pageNo, int pageSize);
}