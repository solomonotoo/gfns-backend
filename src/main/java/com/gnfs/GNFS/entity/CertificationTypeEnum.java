package com.gnfs.GNFS.entity;

public enum CertificationTypeEnum {

	FORM_A("Form A (Certification)"),FORM_B("Form B (Permit Certification)");
	
	private final String displayName;
	
	CertificationTypeEnum(String displayName){
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
