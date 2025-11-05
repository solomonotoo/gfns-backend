package com.gnfs.GNFS.dto.incident;

import com.gnfs.GNFS.dto.referenceDTO.IncidentAddReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.StaffRegistrationReferenceDTO;
import com.gnfs.GNFS.entity.IncidentAssignedToOfficer;
import com.gnfs.GNFS.entity.IncidentPriorityEnum;


public record IncidentAssignedToOfficerResponseDTO(
		Long id,
		StaffRegistrationReferenceDTO officer,
		IncidentAddReferenceDTO incident,
		String description,
		IncidentPriorityEnum priority
		) {
	
//	// Static factory method to create DTO with formatted priority
//    public static IncidentAssignedToOfficerResponseDTO fromEntity(IncidentAssignedToOfficer entity) {
//        return new IncidentAssignedToOfficerResponseDTO(
//            entity.getId(),
//            new StaffRegistrationReferenceDTO(entity.getOfficer().getId()),
//            entity.getDescription(),
//            entity.getPriority(),
//            formatPriority(entity.getPriority())  // Format the enum for display
//        );
//    }0598635383
//
//    private static String formatPriority(IncidentPriorityEnum priority) {
//        if (priority == null) return "";
//        
//        switch (priority) {
//            case HIGH: return "High";
//            case MEDIUM: return "Medium";
//            case LOW: return "Low";
//            default: return priority.name();
//        }
//    }

}
