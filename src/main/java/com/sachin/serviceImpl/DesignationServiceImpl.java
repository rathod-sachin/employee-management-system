package com.sachin.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sachin.entity.Designation;
import com.sachin.repo.DesignationRepository;
import com.sachin.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	@Override
	public void addDesignation(Designation designation) {
		designationRepository.save(designation);
	}

	@Override
	public Designation getDesignation(Long id) {
		Optional<Designation> optionalDesignation = designationRepository.findById(id);

		if (optionalDesignation.isPresent()) {
			return optionalDesignation.get();
		} else {
			throw new IllegalArgumentException("Designation not found with ID: " + id);
		}
	}

	@Override
	public List<Designation> getDesignations() {
		return designationRepository.findAll();
	}

	@Override
	public void updateDesignation(Long id, Designation designation) {
		Designation existingDesignation = designationRepository.findById(id).orElse(null);

		if (existingDesignation != null) {
			existingDesignation.setDesignationName(designation.getDesignationName());
			designationRepository.save(existingDesignation);
		} else {
			throw new IllegalArgumentException("Designation not found with ID: " + id);
		}
	}

	@Override
	public void deleteDesignation(Long id) {
		designationRepository.deleteById(id);
	}

	@Override
	public Page<Designation> findPaginated(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		return this.designationRepository.findAll(pageable);
	}
}