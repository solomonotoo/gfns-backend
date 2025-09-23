package com.gnfs.GNFS.dto.facility;

import com.gnfs.GNFS.entity.Facility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeRequestDTO {

	private String name;
	private String description;
	private Facility facility;

}
