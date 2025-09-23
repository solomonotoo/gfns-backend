package com.gnfs.GNFS.entity;

public enum StatusEnum {
	ACTIVE("Active"),INACTIVE("Inactive");
	
	private final String displayName;
	
	private StatusEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
