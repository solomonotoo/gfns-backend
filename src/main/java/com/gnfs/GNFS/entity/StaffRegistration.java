package com.gnfs.GNFS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_registration")
public class StaffRegistration extends IdBaseEntityNumeric<Long>{

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "other_name")
	private String otherName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	//private String serviceNo;
}
