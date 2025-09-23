package com.gnfs.GNFS.service.business.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.business.BusinessClassRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessClassResponseDTO;
import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.exceptions.BusinessClassNotFoundException;
import com.gnfs.GNFS.repository.BusinessClassRepository;
import com.gnfs.GNFS.service.business.BusinessClassService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessClassServiceImpl implements BusinessClassService{
	
	private final ModelMapper mapper;
	private final BusinessClassRepository businessClassRepo;

	@Override
	public List<BusinessClassResponseDTO> listBusinessClass() {
		List<BusinessClass> listBusinesses = businessClassRepo.findAll();
		return listBusinesses.stream().map(business -> convertFromEntityToDTO(business))
				.toList();
	}

	@Override
	public BusinessClassResponseDTO createBusinessClass(BusinessClassRequestDTO businessClassRequestDTO) {
		BusinessClass business = convertFromDTOToEntity(businessClassRequestDTO);
		BusinessClass saveBusiness = businessClassRepo.save(business);
		return convertFromEntityToDTO(saveBusiness);
	}

	@Override
	public BusinessClassResponseDTO getBusinessClass(Integer id) {
		BusinessClass businessClass  = getBusinessClassId(id);
		
		return convertFromEntityToDTO(businessClass);
	}

	@Override
	public BusinessClassResponseDTO updataBusinessClass(Integer id, BusinessClassRequestDTO businessClassRequestDTO) {
		BusinessClass existingBusinessClass  = getBusinessClassId(id);
		BusinessClass businessClassToUpdate = convertFromDTOToEntity(businessClassRequestDTO);
		businessClassToUpdate.setId(id);
		mapper.map(businessClassToUpdate, existingBusinessClass);
		
		BusinessClass updateBusinessClass = businessClassRepo.save(existingBusinessClass);
		return convertFromEntityToDTO(updateBusinessClass);
	}

	@Override
	public void deleteBusinessClass(Integer id) {
		Integer countById = businessClassRepo.countById(id);
		
		if(countById == 0 || countById == null) {
			throw new BusinessClassNotFoundException("Could not find Business Class ID: " + id);
		}
		
		businessClassRepo.deleteById(countById);
		
	}
	
	private BusinessClass getBusinessClassId(Integer id) {
		return businessClassRepo.findById(id)
				.orElseThrow(() -> new BusinessClassNotFoundException("Could not find Business Class with ID: " + id));
	}
	
	private BusinessClassResponseDTO convertFromEntityToDTO(BusinessClass businessClass) {
		return mapper.map(businessClass, BusinessClassResponseDTO.class);
	}
	
	
	private BusinessClass convertFromDTOToEntity(BusinessClassRequestDTO businessClassRequestDTO) {
		//BusinessClass businessClass =  mapper.map(businessClassRequestDTO, BusinessClass.class);
		
		//manually set status because it is not type enum
//		if(businessClassRequestDTO.getStatus() != null ) {
//			
//		}
		//return businessClass;
		
		 return mapper.map(businessClassRequestDTO, BusinessClass.class);
			
	}

}
