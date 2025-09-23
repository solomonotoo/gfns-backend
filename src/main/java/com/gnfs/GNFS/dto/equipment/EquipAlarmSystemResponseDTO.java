package com.gnfs.GNFS.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipAlarmSystemResponseDTO {

	private Integer id;
	private String name;
	private String description;
}
