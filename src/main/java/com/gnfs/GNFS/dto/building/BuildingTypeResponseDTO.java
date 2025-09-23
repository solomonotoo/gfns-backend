package com.gnfs.GNFS.dto.building;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingTypeResponseDTO {
	
	@JsonProperty("building_id")
	private Integer id;
	
	@JsonProperty("building_name")
	private String name;
	
	@JsonProperty("building_description")
	private String description;

}
