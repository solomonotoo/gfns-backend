package com.gnfs.GNFS.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.entity.ActivityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDTO {
//	private Integer id;
//	
//	@JsonProperty("activity_name")
//	private String name;
//	private String responsibility;
//	private String description;
	
private Integer id;
	
	@JsonProperty("activity_name")
	private String name;
	private String description;
	
	@JsonProperty("activity_type")
	private ActivityTypeDTO activityType;

}
