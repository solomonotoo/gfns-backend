package com.gnfs.GNFS.dto.district;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DistrictRequestDTO {
	
	@JsonProperty("district_name")
	private String districtName;
	
	@JsonProperty("region_id")
	private Integer regionId;

}
