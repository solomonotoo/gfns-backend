package com.gnfs.GNFS.dto.building;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionTypeResponseDTO {

	@JsonProperty("construction_id")
	private Integer id;
	
	@JsonProperty("construction_name")
	private String name;
	
	@JsonProperty("construction_description")
	private String description;
}
