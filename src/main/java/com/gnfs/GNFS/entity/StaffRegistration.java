package com.gnfs.GNFS.entity;

import java.beans.Transient;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "staff_registration")
@Setter
@Getter
public class StaffRegistration extends IdBaseEntity<Long>{

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "other_name")
	private String otherName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	@Enumerated(EnumType.STRING)
	private MarritalStatus marritalStatus;
	
	private String photo;
	
	@Transient
	public String getFullName() {
		return this.getFirstName() + " " + this.getOtherName()+ " " + this.getLastName();
	}
}
