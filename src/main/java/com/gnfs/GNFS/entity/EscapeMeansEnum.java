package com.gnfs.GNFS.entity;

public enum EscapeMeansEnum {

	BACK_DOOR("Back Door"), BASEMENT_EXIT("Basement Exit"),EMERGENCY_EXIT("Emergency Exit"),
	EXIT_DIRECTIONAL_SIGN("Exit Directional Sign"),SHIT("Shit"),
	GENERAL_FIRE_NOTICE("General Fire Notice"),STAIR_CASE("Stair Case"),MAIN_EXIT("Main Exit");
	
	private final String displayName;

	private EscapeMeansEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
