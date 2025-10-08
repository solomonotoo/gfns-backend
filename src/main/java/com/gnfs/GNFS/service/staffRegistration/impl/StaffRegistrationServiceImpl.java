package com.gnfs.GNFS.service.staffRegistration.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
import com.gnfs.GNFS.entity.StaffRegistration;
import com.gnfs.GNFS.exceptions.StaffRegistrationNotFoundException;
import com.gnfs.GNFS.mapper.StaffRegistrationMapper;
import com.gnfs.GNFS.repository.StaffRegistrationRepository;
import com.gnfs.GNFS.service.staffRegistration.StaffRegistrationService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StaffRegistrationServiceImpl implements StaffRegistrationService{

	
	private final StaffRegistrationRepository staffRegistrationRepo;
	private final StaffRegistrationMapper mapper;
	
	@Override
	public List<StaffRegistrationResponseDTO> listStaffRegistration() {
		
		return staffRegistrationRepo.findAll().stream().map(listStaff -> convertFromEntitToDTO(listStaff))
				.toList();
	}

	@Override
	public StaffRegistrationResponseDTO getStaffRegistrationId(Long id) {
		StaffRegistration staffRegistration = getRegisteredStaff(id);
		return convertFromEntitToDTO(staffRegistration);
	}

	@Override
	public StaffRegistrationResponseDTO createStaffRegistration(StaffRegistrationCreateRequestDTO dto) {
		StaffRegistration staffRegistration = convertFromDTOToEntity(dto);
		StaffRegistration savedStaffRegistration = staffRegistrationRepo.save(staffRegistration);
		return convertFromEntitToDTO(savedStaffRegistration);
	}

	@Override
	public StaffRegistrationResponseDTO updateStaffRegistration(Long id, StaffRegistrationUpdateRequestDTO dto) {
		
		if (!id.equals(dto.id())) {
	        throw new IllegalArgumentException("Path ID and DTO ID mismatch");
	    }
		
		StaffRegistration staffRegistrationToUpdate = convertFromDTOToEntity(dto);
		StaffRegistration updateStaffRegistration = staffRegistrationRepo.save(staffRegistrationToUpdate);
		return convertFromEntitToDTO(updateStaffRegistration);
	}

	@Override
	public void deleteStaffRegistration(Long id) {
		Long countById = staffRegistrationRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new StaffRegistrationNotFoundException("Could not find staff ID: " + id);
		}
		
		staffRegistrationRepo.deleteById(id);
	}

	
	private StaffRegistration getRegisteredStaff(Long id) {
		return staffRegistrationRepo.findById(id)
				.orElseThrow(() -> new StaffRegistrationNotFoundException("Could not find registered staff ID: " + id));
	}
	
	
	private StaffRegistrationResponseDTO convertFromEntitToDTO(StaffRegistration staffRegistration) {
		
		return mapper.toResponseDTO(staffRegistration);
	}
	
	
	// use something like this if you are using ModelMapper 
//	private <T> StaffRegistration convertFromDTOToEntity(T dto) {
//	    StaffRegistration entity = modelMapper.map(dto, StaffRegistration.class);
//	    if (dto instanceof StaffRegistrationUpdateRequestDTO updateDto) {
//	        entity.setId(updateDto.id());
//	    }
//	    return entity;
//	}
	
	
	
	//for mapstruct
	private StaffRegistration convertFromDTOToEntity(Object dto) {
	
		if(dto instanceof StaffRegistrationCreateRequestDTO createDTO) {
			return mapper.mapToStaffRegistrationEntityObject(createDTO);
		}else if (dto instanceof StaffRegistrationUpdateRequestDTO updateDTO) {
			StaffRegistration existingStaff = getRegisteredStaff(updateDTO.id());
			mapper.updateStaffRegistrationEntityObjectFromDTO(updateDTO, existingStaff);
			
			return existingStaff;
		}else {
            throw new IllegalArgumentException("Unsupported DTO type: " + dto.getClass().getSimpleName());
        }
	}
	
	
}
