package com.gnfs.GNFS.dto.incident;

import com.gnfs.GNFS.dto.referenceDTO.IncidentCategoryReferenceDTO;

import lombok.Data;

@Data
public class IncidentTypeResponseDTO {
	private Integer id;
	private String name;
	private String description;
	private IncidentCategoryReferenceDTO categoryReference;
}
