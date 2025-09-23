package com.gnfs.GNFS.service.business.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.business.BusinessTypeRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessTypeResponseDTO;
import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.entity.BusinessType;
import com.gnfs.GNFS.exceptions.BusinessClassNotFoundException;
import com.gnfs.GNFS.exceptions.BusinessTypeNotFoundException;
import com.gnfs.GNFS.repository.BusinessClassRepository;
import com.gnfs.GNFS.repository.BusinessTypeRepository;
import com.gnfs.GNFS.service.business.BusinessTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BusinessTypeServiceImpl implements BusinessTypeService{
	
	private final BusinessTypeRepository businessTypeRepo;
	private final BusinessClassRepository businessClassRepo;
	private final ModelMapper mapper;

	@Override
	public List<BusinessTypeResponseDTO> listbusinessType() {
		List<BusinessType> businessType = businessTypeRepo.findAll();
		return businessType.stream().map(business -> convertFromEntityToDTO(business))
				.toList();
	}

	@Override
	public BusinessTypeResponseDTO createbusinessType(BusinessTypeRequestDTO businessTypeRequestDTO) {
		BusinessType businessType = convertFromDTOToEntity(businessTypeRequestDTO);
		
		//retrieve business class by id from the database. thus the selected business type id
		BusinessClass businessClassId = businessClassRepo.findById(businessType.getBusinessClass().getId())
				.orElseThrow(() -> new BusinessClassNotFoundException("Could not find business class ID: "+ businessType.getBusinessClass().getId()));
		businessType.setBusinessClass(businessClassId);
		
		BusinessType savedBusinessType = businessTypeRepo.save(businessType);
		
		return convertFromEntityToDTO(savedBusinessType);
	}

	@Override
	public BusinessTypeResponseDTO getbusinessType(Integer id) {
		BusinessType businessType = getBusinessTypeId(id);
		return convertFromEntityToDTO(businessType);
	}

	@Override
	public BusinessTypeResponseDTO updatabusinessType(Integer id, BusinessTypeRequestDTO businessTypeRequestDTO) {
		BusinessType existingBusinessType = getBusinessTypeId(id);
		BusinessType businessTypeToUpdate = convertFromDTOToEntity(businessTypeRequestDTO);
		businessTypeToUpdate.setId(id);
		
		//map the updated business type to the existing business type
		mapper.map(businessTypeToUpdate, existingBusinessType);
		BusinessType updateBusinessType = businessTypeRepo.save(existingBusinessType);
		
		return convertFromEntityToDTO(updateBusinessType);
	}

	@Override
	public void deletebusinessType(Integer id) {
		Integer countById = businessTypeRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new BusinessTypeNotFoundException("Could not find business type ID: "+ id);
		}
		
		businessTypeRepo.deleteById(id);
		
	}
	
	private BusinessType getBusinessTypeId(Integer id) {
		return businessTypeRepo.findById(id)
				.orElseThrow(() -> new BusinessTypeNotFoundException("Could not find Business Type ID: " + id));
	}

	
	private BusinessType convertFromDTOToEntity(BusinessTypeRequestDTO requestDTO) {
		BusinessType businessType = mapper.map(requestDTO, BusinessType.class);
		
		 // Manually set Business class from the referenced DTO
		if(requestDTO.getBusinessClass() == null || requestDTO.getBusinessClass().getId() == null) {
			throw new IllegalArgumentException("Business Class Id must be provided");
		}
		
		BusinessClass businessClass = new BusinessClass();
		businessClass.setId(requestDTO.getBusinessClass().getId());
		businessType.setBusinessClass(businessClass);
		
		return businessType;
	}
	
	private BusinessTypeResponseDTO convertFromEntityToDTO(BusinessType businessType) {
		return mapper.map(businessType, BusinessTypeResponseDTO.class);
	}
}
