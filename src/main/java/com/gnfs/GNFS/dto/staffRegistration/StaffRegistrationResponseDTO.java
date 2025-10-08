package com.gnfs.GNFS.dto.staffRegistration;

import com.gnfs.GNFS.entity.GenderEnum;

public record StaffRegistrationResponseDTO(
		Long id,

		 String firstName,
		
		 String lastName,
		
		 String otherName,

		 GenderEnum gender
		) {

}
