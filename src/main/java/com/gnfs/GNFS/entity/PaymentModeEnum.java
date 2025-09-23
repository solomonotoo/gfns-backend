package com.gnfs.GNFS.entity;

public enum PaymentModeEnum {
	Cash("cash"), Cheque("cheque");
	
	private final String displayName;


	PaymentModeEnum(String displayName) {
		
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	
}
