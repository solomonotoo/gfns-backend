package com.gnfs.GNFS.service.incident.impli;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.incident.IncidentAddRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAddResponseDTO;
import com.gnfs.GNFS.entity.IncidentAdd;
import com.gnfs.GNFS.exceptions.IncidentAddNotFoundException;
import com.gnfs.GNFS.exceptions.IncidentCategoryNotFoundException;
import com.gnfs.GNFS.exceptions.IncidentTypeNotFoundException;
import com.gnfs.GNFS.exceptions.RegionNotFoundException;
import com.gnfs.GNFS.repository.IncidentAddRepository;
import com.gnfs.GNFS.repository.IncidentCategoryRepository;
import com.gnfs.GNFS.repository.IncidentTypeRepository;
import com.gnfs.GNFS.repository.RegionRepository;
import com.gnfs.GNFS.service.incident.IncidentAddService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidentAddServiceImpl implements IncidentAddService{
	
	private final IncidentAddRepository incidentAddRepo;
	private final RegionRepository regionRepo;
	private final IncidentCategoryRepository categoryRepo;
	private final IncidentTypeRepository typeRepo;
	private final ModelMapper mapper;
	
	@Override
	public List<IncidentAddResponseDTO> listIncidentAdd() {
		// TODO Auto-generated method stub
		return incidentAddRepo.findAll()
				.stream()
				.map(incident -> convertFromEntityToDTO(incident))
				.toList();
	}

	@Override
	public IncidentAddResponseDTO getIncidentAddId(Long id) {
		IncidentAdd incidentAdd = getIncidentAddById(id);
		return convertFromEntityToDTO(incidentAdd);
	}

	@Override
	public IncidentAddResponseDTO createIncidentAdd(IncidentAddRequestDTO dto) {
		IncidentAdd incident = convertFromDTOToEntity(dto);
		
		//relations skipped in the GnfsApplication.java in model mapper bean method
		// resolved in the code below
		incident.setRegion(regionRepo.findById(dto.getRegion().getId())
				.orElseThrow(()-> new RegionNotFoundException("Region not found")));
		
		incident.setIncidentCategory(categoryRepo.findById(dto.getIncidentCategory().getId())
				.orElseThrow(() -> new IncidentCategoryNotFoundException("Incident Category not found")));
		
		incident.setIncidentType(typeRepo.findById(dto.getIncidentType().getId())
				.orElseThrow(() -> new IncidentTypeNotFoundException("Incident Type not found")));
		
		
		IncidentAdd savedIncident = incidentAddRepo.save(incident);
		
		return convertFromEntityToDTO(savedIncident);
	}

	@Override
	public IncidentAddResponseDTO updateIncidentAdd(Long id, IncidentAddRequestDTO dto) {
		
		IncidentAdd incident = getIncidentAddById(id);

        // Map basic fields (skips relations)
        mapper.map(dto, incident);

        // Update relations
        incident.setRegion(regionRepo.findById(dto.getRegion().getId())
                .orElseThrow(() -> new RuntimeException("Region not found")));
        incident.setIncidentCategory(categoryRepo.findById(dto.getIncidentCategory().getId())
                .orElseThrow(() -> new RuntimeException("Incident Category not found")));
        incident.setIncidentType(typeRepo.findById(dto.getIncidentType().getId())
                .orElseThrow(() -> new RuntimeException("Incident Type not found")));

        IncidentAdd updated = incidentAddRepo.save(incident);
        return convertFromEntityToDTO(updated);
	}

	@Override
	public void deleteIncidentAdd(Long id) {
		
		Long countById = incidentAddRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new IncidentAddNotFoundException("Incident not found");
		}
		
		incidentAddRepo.deleteById(id);
		
	}

	
	private IncidentAdd getIncidentAddById(Long id) {
		return incidentAddRepo.findById(id)
				.orElseThrow(()-> new IncidentAddNotFoundException("Could not find Incident ID: " + id));
	}
	
	
	private IncidentAddResponseDTO convertFromEntityToDTO(IncidentAdd incidentAdd) {
		return mapper.map(incidentAdd, IncidentAddResponseDTO.class);
	}
	
	private IncidentAdd convertFromDTOToEntity(IncidentAddRequestDTO dto) {
		return mapper.map(dto, IncidentAdd.class);
	}
}
