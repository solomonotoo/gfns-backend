package com.gnfs.GNFS.dto.building;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionTypeRequestDTO {
	
	@JsonProperty("construction_name")
	private String name;
	
	@JsonProperty("construction_description")
	private String description;

}
