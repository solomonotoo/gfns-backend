package com.gnfs.GNFS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerResponseDTO;
import com.gnfs.GNFS.entity.IncidentAssignedToOfficer;

@Mapper(componentModel = "spring", uses = StaffRegistrationMapper.class)
public interface IncidentAssignedToOfficerMapper {
	IncidentAssignedToOfficer toEntity(IncidentAssignedToOfficerRequestDTO dto);

    IncidentAssignedToOfficerResponseDTO toResponseDTO(IncidentAssignedToOfficer entity);
    
    void updateIncidentAssignedToOfficerFromDTO(IncidentAssignedToOfficerRequestDTO dto, @MappingTarget IncidentAssignedToOfficer entity);
}
