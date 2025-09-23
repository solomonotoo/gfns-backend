package com.gnfs.GNFS.entity;

public enum DevicesEnum {

	
	CALL_POINT("Call Point"),
	HEAT_DETECTOR("Heat Detector"),
	CONTROL_PANEL("Control Panel"),
	SMOKE_DETECTOR("Smoke Detector"),
	FLAME_DETECTOR("Flame Detector"),
	SOUNDER("Sounder"),
	GAS_DETECTOR("Gas Detector");
	
	private final String displayName;
	
	private DevicesEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
