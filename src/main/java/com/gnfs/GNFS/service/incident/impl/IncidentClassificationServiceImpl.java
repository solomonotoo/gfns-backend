package com.gnfs.GNFS.service.incident.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.incident.IncidentClassificationRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentClassificationResponseDTO;
import com.gnfs.GNFS.entity.IncidentClassification;
import com.gnfs.GNFS.exceptions.IncidentClassificationNotFoundException;
import com.gnfs.GNFS.repository.IncidentClassificationRepository;
import com.gnfs.GNFS.service.incident.IncidentClassificationService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IncidentClassificationServiceImpl implements IncidentClassificationService{
	
	private final IncidentClassificationRepository incidentClassificationRepo;
	private final ModelMapper mapper;

	@Override
	public List<IncidentClassificationResponseDTO> listIncidentClassification() {
		List<IncidentClassification> IncidentClassification = incidentClassificationRepo.findAll();
		return IncidentClassification.stream().map(incident -> convertFromEntityToDTO(incident))
				.toList();
	}

	@Override
	public IncidentClassificationResponseDTO getIncidentClassificationId(Integer id) {
		IncidentClassification IncidentClassification = getIncidentClassificationById(id);
		return convertFromEntityToDTO(IncidentClassification);
	}

	@Override
	public IncidentClassificationResponseDTO createIncidentClassification(IncidentClassificationRequestDTO dto) {
		if (incidentClassificationRepo.existsByName(dto.getName())) {
			throw new DuplicateResouceException("Name already exist");
		}
		
		IncidentClassification IncidentClassification = convertFromDTOToEntity(dto);
		IncidentClassification savedIncidentClassification = incidentClassificationRepo.save(IncidentClassification);
		
		return convertFromEntityToDTO(savedIncidentClassification);
	}

	@Override
	public IncidentClassificationResponseDTO updateIncidentClassification(Integer id, IncidentClassificationRequestDTO dto) {
		IncidentClassification existingIncidentClassification = getIncidentClassificationById(id);
		IncidentClassification IncidentClassification2Update = convertFromDTOToEntity(dto);
		IncidentClassification2Update.setId(id);
		mapper.map(IncidentClassification2Update, existingIncidentClassification);
		
		IncidentClassification updateIncidentClassification = incidentClassificationRepo.save(existingIncidentClassification);
		return convertFromEntityToDTO(updateIncidentClassification);
	}

	@Override
	public void deleteIncidentClassification(Integer id) {
		Integer countById = incidentClassificationRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new IncidentClassificationNotFoundException("Could not find Incident Type with ID: " + id);
		}
		incidentClassificationRepo.deleteById(countById);
	}
	
	private IncidentClassification getIncidentClassificationById(Integer id) {
		return incidentClassificationRepo.findById(id)
				.orElseThrow(()-> new IncidentClassificationNotFoundException("Could not find Incident Type with ID: " + id));
	}

	
	private IncidentClassificationResponseDTO convertFromEntityToDTO(IncidentClassification IncidentClassification) {
		return mapper.map(IncidentClassification, IncidentClassificationResponseDTO.class);
	}
	
	private IncidentClassification convertFromDTOToEntity(IncidentClassificationRequestDTO requestDTO) {
		return mapper.map(requestDTO, IncidentClassification.class);
	}
}
