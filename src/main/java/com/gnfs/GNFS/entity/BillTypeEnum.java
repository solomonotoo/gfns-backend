package com.gnfs.GNFS.entity;


public enum BillTypeEnum {

	NEW("New"), RENEW("Renew");
	
	private final String displayName;
	
	BillTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
		
}
