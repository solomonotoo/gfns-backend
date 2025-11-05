package com.gnfs.GNFS.service.incident.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.incident.IncidentCategoryRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentCategoryResponseDTO;
import com.gnfs.GNFS.entity.IncidentCategory;
import com.gnfs.GNFS.entity.IncidentClassification;
import com.gnfs.GNFS.exceptions.IncidentCategoryNotFoundException;
import com.gnfs.GNFS.repository.IncidentCategoryRepository;
import com.gnfs.GNFS.repository.IncidentClassificationRepository;
import com.gnfs.GNFS.service.incident.IncidentCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidentCategoryServiceImpl implements IncidentCategoryService{
	
	private final IncidentCategoryRepository incidentCategoryRepo;
	private final IncidentClassificationRepository incidentClassificationRepo;
	private final ModelMapper mapper;

	@Override
	public List<IncidentCategoryResponseDTO> listIncidentCategory() {
		List<IncidentCategory> listIncidentCategories = incidentCategoryRepo.findAll();
		return listIncidentCategories.stream().map(incidents -> convertFromEntityToDTO(incidents))
				.toList();
	}

	@Override
	public IncidentCategoryResponseDTO getIncidentCategoryId(Integer id) {
		IncidentCategory incidentCategory = getIncidentCategoryById(id);
		return convertFromEntityToDTO(incidentCategory);
	}

	@Override
	public IncidentCategoryResponseDTO createIncidentCategory(IncidentCategoryRequestDTO dto) {
		
		if (incidentCategoryRepo.existsByName(dto.getName())) {
			throw new DuplicateResouceException("Name already exist");
		}
		IncidentCategory incidentCategory = convertFromDTOToEntity(dto);
		IncidentCategory saveIncidentCategory = incidentCategoryRepo.save(incidentCategory);
		return convertFromEntityToDTO(saveIncidentCategory);
	}

	@Override
	public IncidentCategoryResponseDTO updateIncidentCategory(Integer id, IncidentCategoryRequestDTO dto) {
		IncidentCategory existingIncidentCategory = getIncidentCategoryById(id);
		IncidentCategory incidentCategory2update = convertFromDTOToEntity(dto);
		incidentCategory2update.setId(id);
		
		mapper.map(incidentCategory2update, existingIncidentCategory);
		
		IncidentCategory updatedIncidentCategory = incidentCategoryRepo.save(existingIncidentCategory);
		
		return convertFromEntityToDTO(updatedIncidentCategory);
	}

	@Override
	public void deleteIncidentCategory(Integer id) {
		Integer countById = incidentCategoryRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new IncidentCategoryNotFoundException("Could not find incident category with ID: " + id);
		}
		
		incidentCategoryRepo.deleteById(id);
		
	}
	
	private IncidentCategory getIncidentCategoryById(Integer id) {
		return incidentCategoryRepo.findById(id)
				.orElseThrow(()-> new IncidentCategoryNotFoundException("Could not find Incident Category ID: " + id));
	}

	private IncidentCategoryResponseDTO convertFromEntityToDTO(IncidentCategory incidentCategory) {
		return mapper.map(incidentCategory, IncidentCategoryResponseDTO.class);
	}
	
	private IncidentCategory convertFromDTOToEntity(IncidentCategoryRequestDTO incidentCategoryRequestDTO) {
		
		IncidentCategory incidentCategory = mapper.map(incidentCategoryRequestDTO, IncidentCategory.class);
		
		if(incidentCategoryRequestDTO.getIncidentClass() != null && incidentCategoryRequestDTO.getIncidentClass().getId() != null) {
        	IncidentClassification incidentClassification = incidentClassificationRepo.findById(incidentCategoryRequestDTO.getIncidentClass().getId())
        			.orElseThrow(() -> new RuntimeException("Incident Classification not found with ID: " + incidentCategoryRequestDTO.getIncidentClass().getId()));
        	incidentCategory.setClassification(incidentClassification);
        }else {
        	incidentCategory.setClassification(null);
		}
		
		return incidentCategory;
	}
	
}
