package com.gnfs.GNFS.dto.district;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistrictResponseDTO {

	private Integer id;
	
	@JsonProperty("district_name")
	private String districtName;
	
	@JsonProperty("region_name")
    private String regionName;

	@JsonProperty("region_id")
	private Integer regionId;
}
