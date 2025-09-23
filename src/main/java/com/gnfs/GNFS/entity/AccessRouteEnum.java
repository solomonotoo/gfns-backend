package com.gnfs.GNFS.entity;

public enum AccessRouteEnum {

	ACCESS_ROUTE("Access Route");
	
	private final String displayName;
	
	private AccessRouteEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
