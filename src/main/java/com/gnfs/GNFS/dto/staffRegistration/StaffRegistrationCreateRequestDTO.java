package com.gnfs.GNFS.dto.staffRegistration;

import com.gnfs.GNFS.entity.GenderEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StaffRegistrationCreateRequestDTO(
		    @NotBlank(message = "First Name is required")
		    @Size(min = 2, message = "First Name should have at least 2 characters")
		    String firstName,

		    @NotBlank(message = "Last Name is required")
		    @Size(min = 2, message = "Last Name should have at least 2 characters")
		    String lastName,

		    String otherName,
		    GenderEnum gender
	    ) {

}
