package com.gnfs.GNFS.dto.incident;


import com.gnfs.GNFS.dto.referenceDTO.IncidentCategoryReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.IncidentTypeReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.RegionReferenceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentAddRequestDTO {
	private Long id;

	@NotBlank(message = "Full name is required")
	private String fullName;
	
	@NotBlank(message = "Description is required")
	private String description;

	@NotBlank(message = "Phone number name is required")
	private String phoneNumber;

	@NotBlank(message = "location is required")
	private String locationArea;

	//@NotNull(message = "Incident picture  is required")
	private String photo;

	@NotNull(message = "Region is required")
	private RegionReferenceDTO region;

	@NotNull(message = "Category is required")
	private IncidentCategoryReferenceDTO incidentCategory;

	@NotNull(message = "Incident type is required")
	private IncidentTypeReferenceDTO incidentType;
}
