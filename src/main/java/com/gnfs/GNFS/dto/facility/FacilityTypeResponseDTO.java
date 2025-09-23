package com.gnfs.GNFS.dto.facility;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeResponseDTO {

	private Integer id;
	private String name;
	private String description;
	
	//NB using a direct entity here will create a problem
	private FacilityResponseDTO facility;
}
