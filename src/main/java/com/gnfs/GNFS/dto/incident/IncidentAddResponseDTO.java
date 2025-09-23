package com.gnfs.GNFS.dto.incident;
import com.gnfs.GNFS.dto.referenceDTO.IncidentCategoryReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.IncidentTypeReferenceDTO;
import com.gnfs.GNFS.dto.referenceDTO.RegionReferenceDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentAddResponseDTO {
	private Long id;

	private String fullName;
	private String description;

	private String phoneNumber;

	private String locationArea;

	private String photo;

	private RegionReferenceDTO regionReference;

	private IncidentCategoryReferenceDTO incidentCategory;

	private IncidentTypeReferenceDTO incidentType;
}
