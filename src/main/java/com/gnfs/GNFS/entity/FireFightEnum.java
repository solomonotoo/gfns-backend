package com.gnfs.GNFS.entity;

public enum FireFightEnum {

	ALTERNATIVE_SOURCE_OF_WATER("Alternative Source of Water"),
	FOAM_EXTINGUISHER("Foam Extinguisher"),
	CO2_FIRE_EXTINGUISHER("CO2 Fire Extinguisher"),
	HOSE_REEL("Hose Reel"),
	CRY_POWDER_EXTINGUISHER("Dry Powder Extinguishers"),
	HYDRANT_WET_DRY_RISER("Hydrant Wet Dry Riser");
	
	private final String displayName;
	
	 FireFightEnum(String displayName) {
		 this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
