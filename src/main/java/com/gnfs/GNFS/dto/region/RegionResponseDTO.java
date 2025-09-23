package com.gnfs.GNFS.dto.region;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponseDTO {

	private Integer id;

	@JsonProperty("region_name")
	private String regionName;
}
