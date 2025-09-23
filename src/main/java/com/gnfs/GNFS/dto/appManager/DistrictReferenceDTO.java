package com.gnfs.GNFS.dto.appManager;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictReferenceDTO {

	private Integer id;
	private String districtName;
}
