package com.gnfs.GNFS.service.incident.impli;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.incident.IncidentTypeRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentTypeResponseDTO;
import com.gnfs.GNFS.entity.IncidentCategory;
import com.gnfs.GNFS.entity.IncidentClassification;
import com.gnfs.GNFS.entity.IncidentType;
import com.gnfs.GNFS.exceptions.IncidentCategoryNotFoundException;
import com.gnfs.GNFS.exceptions.IncidentTypeNotFoundException;
import com.gnfs.GNFS.repository.IncidentCategoryRepository;
import com.gnfs.GNFS.repository.IncidentTypeRepository;
import com.gnfs.GNFS.service.incident.IncidentTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IncidentTypeServiceImpl implements IncidentTypeService{
	
	private final IncidentTypeRepository incidentTypeRepo;
	private final IncidentCategoryRepository incidentCategoryRepo;
	private final ModelMapper mapper;
	
	@Override
	public List<IncidentTypeResponseDTO> listIncidentType() {
		
		return incidentTypeRepo.findAll()
				.stream().map(incidentType -> convertFromEntityToDTO(incidentType))
				.toList();
	}

	@Override
	public IncidentTypeResponseDTO getIncidentTypeId(Integer id) {
		IncidentType incidentType = getIncidentType(id);
		return convertFromEntityToDTO(incidentType);
	}

	@Override
	public IncidentTypeResponseDTO createIncidentType(IncidentTypeRequestDTO dto) {
		IncidentType incidentType = convertFromDTOToEntity(dto);
		IncidentType saveIncidentType = incidentTypeRepo.save(incidentType);
		return convertFromEntityToDTO(saveIncidentType);
	}

	@Override
	public IncidentTypeResponseDTO updateIncidentType(Integer id, IncidentTypeRequestDTO dto) {
		IncidentType existingIncidentType = getIncidentType(id);
		IncidentType incidentType2Update = convertFromDTOToEntity(dto);
		incidentType2Update.setId(id);
		
		mapper.map(incidentType2Update, existingIncidentType);
		IncidentType updateIncidentType = incidentTypeRepo.save(existingIncidentType);
		
		return convertFromEntityToDTO(updateIncidentType);
	}

	@Override
	public void deleteIncidentType(Integer id) {
		Integer countById = incidentTypeRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new IncidentTypeNotFoundException("Could not find incident type with ID: " + id);
		}
		
		incidentTypeRepo.deleteById(id);
		
	}

	
	private IncidentType getIncidentType(Integer id) {
		return incidentTypeRepo.findById(id)
				.orElseThrow(()-> new IncidentTypeNotFoundException("Could not find Incident Type ID: " + id));
	}
	
	private IncidentTypeResponseDTO convertFromEntityToDTO(IncidentType incidentType) {
		
		return mapper.map(incidentType, IncidentTypeResponseDTO.class);
	}
	
	private IncidentType convertFromDTOToEntity(IncidentTypeRequestDTO incidentTypeRequestDTO) {
		IncidentType incidentType = mapper.map(incidentTypeRequestDTO, IncidentType.class);
		
		if(incidentTypeRequestDTO.getCategoryReference() != null && incidentTypeRequestDTO.getCategoryReference().getId() != null) {
        	IncidentCategory incidentCategory = incidentCategoryRepo.findById(incidentTypeRequestDTO.getCategoryReference().getId())
        			.orElseThrow(() -> new RuntimeException("Incident Classification not found with ID: " + incidentTypeRequestDTO.getCategoryReference().getId()));
        	incidentType.setCategory(incidentCategory);
        }else {
        	incidentType.setCategory(null);
		}
		
		return incidentType;
	}
	
}
