package com.gnfs.GNFS.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTypeResponseDTO {

	private Integer id;
	private String name;
	private String description;
}
