package com.gnfs.GNFS.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResponseDTO {

	private Integer id;
	private String name;
	private String description;
}
