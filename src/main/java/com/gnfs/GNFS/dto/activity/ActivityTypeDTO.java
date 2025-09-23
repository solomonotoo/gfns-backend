package com.gnfs.GNFS.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTypeDTO {
	private Integer id;
    private String name;
    private String description;
}
