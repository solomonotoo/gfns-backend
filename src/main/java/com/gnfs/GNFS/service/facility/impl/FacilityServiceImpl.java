package com.gnfs.GNFS.service.facility.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.facility.FacilityRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityResponseDTO;
import com.gnfs.GNFS.entity.Facility;
import com.gnfs.GNFS.exceptions.FacilityNotFoundException;
import com.gnfs.GNFS.repository.FacilityRepository;
import com.gnfs.GNFS.service.facility.FacilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {
	
	private final FacilityRepository facilityRepo;
	private final ModelMapper mapper;

	@Override
	public List<FacilityResponseDTO> listFacilities() {
		List<Facility> facilitities = facilityRepo.findAll();
		return facilitities.stream().map(facility -> convertFromEntityToDTO(facility))
				.collect(Collectors.toList());
	}

	@Override
	public FacilityResponseDTO createFacility(FacilityRequestDTO facilityRequestDTO) {
		Facility facility = convertFromDTOToEntity(facilityRequestDTO);
		Facility savedFacilitity = facilityRepo.save(facility);
		return convertFromEntityToDTO(savedFacilitity);
	}

	@Override
	public FacilityResponseDTO getFacility(Integer id) {
		Facility facility = getFacilityId(id);
		
		return convertFromEntityToDTO(facility);
	}

	@Override
	public FacilityResponseDTO updataFacility(Integer id, FacilityRequestDTO facilityRequestDTO) {
		Facility existingFacility = getFacilityId(id);
		existingFacility.setName(facilityRequestDTO.getName());
		existingFacility.setDescription(facilityRequestDTO.getDescription());
		
		Facility updatedFacility = facilityRepo.save(existingFacility);
		return convertFromEntityToDTO(updatedFacility);
	}

	@Override
	public void deleteFacility(Integer id) {
		Integer countById = facilityRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new FacilityNotFoundException("Could not find faciltity with ID: " + id);
		}

		facilityRepo.deleteById(id);
	}

	
	//private method that retrieve facility by id
	private Facility getFacilityId(Integer id) {
		return facilityRepo.findById(id)
				.orElseThrow(() -> new FacilityNotFoundException("Could not find facility with ID: "+ id));
	}
	
	private FacilityResponseDTO convertFromEntityToDTO(Facility facilitity) {
		return mapper.map(facilitity, FacilityResponseDTO.class);
	}
	
	private Facility convertFromDTOToEntity(FacilityRequestDTO facilityRequestDTO) {
		return mapper.map(facilityRequestDTO, Facility.class);
	}
	
}
