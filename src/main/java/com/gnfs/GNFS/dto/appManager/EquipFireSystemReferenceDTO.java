package com.gnfs.GNFS.dto.appManager;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipFireSystemReferenceDTO {

	private Integer id;
	private String name;
	private String description;
}
