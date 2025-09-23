package com.gnfs.GNFS.dto.incident;

import com.gnfs.GNFS.dto.referenceDTO.IncidentClassificationReferenceDTO;

import lombok.Data;

@Data
public class IncidentCategoryResponseDTO {
	private Integer id;
	private String name;
	private String description;
	private IncidentClassificationReferenceDTO incidentClass;
}
