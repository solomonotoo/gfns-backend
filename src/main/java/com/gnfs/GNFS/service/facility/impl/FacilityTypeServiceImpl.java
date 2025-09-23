package com.gnfs.GNFS.service.facility.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.facility.FacilityTypeRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityTypeResponseDTO;
import com.gnfs.GNFS.entity.FacilityType;
import com.gnfs.GNFS.exceptions.FacilityTypeNotFoundException;
import com.gnfs.GNFS.repository.FacilityTypeRepository;
import com.gnfs.GNFS.service.facility.FacilityTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FacilityTypeServiceImpl implements FacilityTypeService{

	private final FacilityTypeRepository facilityTypeRepo;
	private final ModelMapper mapper;
	
	
	@Override
	public List<FacilityTypeResponseDTO> listFacilityTypes() {
		List<FacilityType> facilityTypes = facilityTypeRepo.findAll();
		return facilityTypes.stream().map(facility -> convertFromEntityToDTO(facility)).collect(Collectors.toList());
	}
	@Override
	public FacilityTypeResponseDTO createFacilityType(FacilityTypeRequestDTO facilityRequestDTO) {
		FacilityType facilityType  = convertFromDTOToEntity(facilityRequestDTO);
		FacilityType savedFacilityType = facilityTypeRepo.save(facilityType);
		return convertFromEntityToDTO(savedFacilityType);
	}
	@Override
	public FacilityTypeResponseDTO getFacilityType(Integer id) {
		FacilityType facilityType = getFacilityTypeId(id);
		return convertFromEntityToDTO(facilityType);
	}
	@Override
	public FacilityTypeResponseDTO updataFacilityType(Integer id, FacilityTypeRequestDTO facilityRequestDTO) {
		FacilityType existingFacilityType = getFacilityTypeId(id);
		existingFacilityType.setId(id);
		existingFacilityType.setName(facilityRequestDTO.getName());
		existingFacilityType.setDescription(facilityRequestDTO.getDescription());
		existingFacilityType.setFacility(facilityRequestDTO.getFacility());
		
		FacilityType updatedFacilityType = facilityTypeRepo.save(existingFacilityType);
		return convertFromEntityToDTO(updatedFacilityType);
	}
	
	@Override
	public void deleteFacilityType(Integer id) {
		Integer countById = facilityTypeRepo.countById(id);
		
		if (countById == 0 || countById == null) {
			throw new FacilityTypeNotFoundException("Could not find Facility type with ID: "+ id);
		}
		
		facilityTypeRepo.deleteById(countById);
		
	}
	
	
	
	//private method that get facility type id
	private FacilityType getFacilityTypeId(Integer id) {
		return facilityTypeRepo.findById(id)
				.orElseThrow(() -> new FacilityTypeNotFoundException("Could not find Facility Type with ID: " + id));
	}
	
	//method that convert from entity to DTO
	private FacilityTypeResponseDTO convertFromEntityToDTO(FacilityType facilityType) {
		return mapper.map(facilityType, FacilityTypeResponseDTO.class);
	}
	
	private FacilityType convertFromDTOToEntity(FacilityTypeRequestDTO facilityTypeRequestDTO) {
		return mapper.map(facilityTypeRequestDTO, FacilityType.class);
	}
	
}
