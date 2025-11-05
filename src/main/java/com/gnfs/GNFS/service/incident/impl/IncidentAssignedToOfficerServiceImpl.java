package com.gnfs.GNFS.service.incident.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerResponseDTO;
import com.gnfs.GNFS.entity.IncidentAssignedToOfficer;
import com.gnfs.GNFS.exceptions.StaffRegistrationNotFoundException;
import com.gnfs.GNFS.mapper.IncidentAssignedToOfficerMapper;
import com.gnfs.GNFS.repository.IncidentAssignedToOfficerRepository;
import com.gnfs.GNFS.service.incident.IncidentAssignedToOfficerService;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IncidentAssignedToOfficerServiceImpl implements IncidentAssignedToOfficerService{
	
	private final IncidentAssignedToOfficerRepository incidentAssignedToOfficerRepo;
	private final IncidentAssignedToOfficerMapper incidentAssignedToOfficerMapper;
	
	@Override
	public List<IncidentAssignedToOfficerResponseDTO> getIncidentList() {
		// TODO Auto-generated method stub
		return incidentAssignedToOfficerRepo.findAll().stream()
				.map(this :: convertFromEnityToDTO)
				.toList();
		
//		 return incidentAssignedToOfficerRepo.findAll().stream()
//		            .map(incident -> convertFromEntityToDTO(incident))
//		            .toList();
		
//		  return incidentAssignedToOfficerRepo.findAll().stream()
//		            .map(incidentAssignedToOfficerMapper::toResponseDTO)
//		            .toList();
	}

	@Override
	public IncidentAssignedToOfficerResponseDTO getIncidentAssignedToOfficerId(Long id) {
		IncidentAssignedToOfficer officer  = gettAssignedToOfficer(id);
		return convertFromEnityToDTO(officer);
		
	}

	@Override
	public IncidentAssignedToOfficerResponseDTO createIncidentAssignedToOfficer(
			IncidentAssignedToOfficerRequestDTO dto) {
		IncidentAssignedToOfficer assignedToOfficer = convertFromDTOToEntity(dto);
		IncidentAssignedToOfficer saveAssignedToOfficer = incidentAssignedToOfficerRepo.save(assignedToOfficer);
		
		return convertFromEnityToDTO(saveAssignedToOfficer);
	}

	@Override
	public IncidentAssignedToOfficerResponseDTO updateIncidentAssignedToOfficer(Long id,
			IncidentAssignedToOfficerRequestDTO requestDTO) {
		IncidentAssignedToOfficer existingAssignedToOfficer  = gettAssignedToOfficer(id);
		
		//Update using MapStruct
		incidentAssignedToOfficerMapper.updateIncidentAssignedToOfficerFromDTO(requestDTO, existingAssignedToOfficer);
		
		IncidentAssignedToOfficer updateAssignedToOfficer = incidentAssignedToOfficerRepo.save(existingAssignedToOfficer);
		
		return convertFromEnityToDTO(updateAssignedToOfficer);
	}
	
	private IncidentAssignedToOfficer gettAssignedToOfficer(Long id) {
		return incidentAssignedToOfficerRepo.findById(id)
				.orElseThrow(()-> new StaffRegistrationNotFoundException("Could not find staff with ID: " + id));
	}
	
	private IncidentAssignedToOfficerResponseDTO convertFromEnityToDTO(IncidentAssignedToOfficer officer) {
		return incidentAssignedToOfficerMapper.toResponseDTO(officer);
	}
	
	private IncidentAssignedToOfficer convertFromDTOToEntity(IncidentAssignedToOfficerRequestDTO dto) {
		return incidentAssignedToOfficerMapper.toEntity(dto);
	}

	

}
