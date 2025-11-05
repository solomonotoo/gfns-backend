package com.gnfs.GNFS.dto.staffRegistration;

import java.time.LocalDate;


import com.gnfs.GNFS.entity.GenderEnum;
import com.gnfs.GNFS.entity.MarritalStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StaffRegistrationUpdateRequestDTO(
		@NotNull(message = "Id is required for update")
		Long id,
		    
		@NotBlank(message = "First Name is required")
		@Size(min = 2, message = "First Name should have at 2 characters")
	 	String firstName,
		
		@NotBlank(message = "Last Name is required")
		@Size(min = 2, message = "Last Name should have at 2 characters")
		String lastName,
		
		String otherName,
		GenderEnum gender,
		 String email,
		 String phoneNumber,
		 @NotNull(message = "Date of birth is required")
		 LocalDate dateOfBirth,
		 RegionReferenceDTO region,
		 DistrictReferenceDTO district,
		 MarritalStatus marritalStatus,
		 String photo
		) {
	
}
