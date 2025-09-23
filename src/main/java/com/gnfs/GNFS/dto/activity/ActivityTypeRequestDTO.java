package com.gnfs.GNFS.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTypeRequestDTO {

	private String name;
	private String description;
}
