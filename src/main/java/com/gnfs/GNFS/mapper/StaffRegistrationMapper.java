package com.gnfs.GNFS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
import com.gnfs.GNFS.entity.StaffRegistration;

@Mapper(componentModel = "spring")
public interface StaffRegistrationMapper {

	//convert from dto to entity
	StaffRegistration mapToStaffRegistrationEntityObject(StaffRegistrationCreateRequestDTO dto);
	
	
	
	 // Update DTO → Entity (full copy)
   // @Mapping(target = "id", ignore = true) // Prevent overwriting ID
	void updateStaffRegistrationEntityObjectFromDTO(StaffRegistrationUpdateRequestDTO dto, @MappingTarget StaffRegistration entity);
	
	
    
 // Entity → Response DTO
    StaffRegistrationResponseDTO toResponseDTO(StaffRegistration entity);
    
    
	
}
