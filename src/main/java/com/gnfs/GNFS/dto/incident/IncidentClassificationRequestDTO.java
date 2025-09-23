package com.gnfs.GNFS.dto.incident;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncidentClassificationRequestDTO {

private Integer id;
	
	@NotBlank
	@Size(min = 2)
	private String name;
	private String description;
}
