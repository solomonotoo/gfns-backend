package com.gnfs.GNFS.dto.staffRegistration;

import com.gnfs.GNFS.entity.GenderEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record StaffRegistrationResponseDTO(
		Long id,

		 String firstName,
		
		 String lastName,
		
		 String otherName,

		 GenderEnum gender
		) {

}
