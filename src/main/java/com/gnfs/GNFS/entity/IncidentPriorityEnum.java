package com.gnfs.GNFS.entity;

public enum IncidentPriorityEnum {
	HIGH("High"),MEDIUM("Medium"),LOW("Low");
	
	private final String displayName;

	private IncidentPriorityEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	} 
	
	
}
