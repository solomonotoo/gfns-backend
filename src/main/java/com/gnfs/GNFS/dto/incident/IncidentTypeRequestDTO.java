package com.gnfs.GNFS.dto.incident;


import com.gnfs.GNFS.dto.referenceDTO.IncidentCategoryReferenceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncidentTypeRequestDTO {

	private Integer id;
	
	@NotBlank(message = "name cannot be blank")
	@Size(min = 2, message = "name must have at least 2 characters")
	private String name;
	private String description;
	private IncidentCategoryReferenceDTO categoryReference;
}
