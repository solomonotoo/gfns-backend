package com.gnfs.GNFS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
import com.gnfs.GNFS.entity.StaffRegistration;

@Mapper(componentModel = "spring")
public interface StaffRegistrationMapper {
	//StaffRegistrationMapper MAPPER = Mappers.getMapper(StaffRegistrationMapper.class);

	//NB @Mapping(target = "id", ignore = true) is used here because of inherited id
	//convert from dto to entity
	@Mapping(target = "id", ignore = true)
	StaffRegistration mapToStaffRegistrationEntityObject(StaffRegistrationCreateRequestDTO dto);
	
	
	//NB @Mapping(target = "id", ignore = true) is used here because of inherited id
	 // Update DTO → Entity (full copy)
    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
	void updateStaffRegistrationEntityObjectFromDTO(StaffRegistrationUpdateRequestDTO dto, @MappingTarget StaffRegistration entity);
	
	
    
 // Entity → Response DTO
    StaffRegistrationResponseDTO toResponseDTO(StaffRegistration entity);
    
    
	
}
