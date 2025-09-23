package com.gnfs.GNFS.dto.enums;


import com.gnfs.GNFS.entity.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusEnumDTO {
	private String value;
	private String displayName;
	
	// Optional: Add conversion methods
    public static StatusEnumDTO fromEnum(StatusEnum statusEnum) {
        return new StatusEnumDTO(statusEnum.name(), statusEnum.getDisplayName());
    }
	
	public StatusEnum toEnum() {
		return StatusEnum.valueOf(this.value);
	}
}
