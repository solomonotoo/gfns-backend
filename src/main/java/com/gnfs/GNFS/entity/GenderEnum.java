package com.gnfs.GNFS.entity;

public enum GenderEnum {
	MALE("Male"), FEMALE("Female");
	
	private final String displayName;

	private GenderEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
