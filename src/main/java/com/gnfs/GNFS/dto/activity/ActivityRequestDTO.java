package com.gnfs.GNFS.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.entity.ActivityType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityRequestDTO {
	
	@JsonProperty("activity_name")
	private String name;
	private String description;
	
	@JsonProperty("activity_type")
	private ActivityType activityType;

}
