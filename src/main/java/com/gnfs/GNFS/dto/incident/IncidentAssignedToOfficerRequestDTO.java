package com.gnfs.GNFS.dto.incident;

import com.gnfs.GNFS.dto.referenceDTO.IncidentAddReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.StaffRegistrationReferenceDTO;
import com.gnfs.GNFS.entity.IncidentPriorityEnum;



public record IncidentAssignedToOfficerRequestDTO(
		Long id,
		StaffRegistrationReferenceDTO officer,
		IncidentAddReferenceDTO incident,
		String description,
		IncidentPriorityEnum priority) {

}
