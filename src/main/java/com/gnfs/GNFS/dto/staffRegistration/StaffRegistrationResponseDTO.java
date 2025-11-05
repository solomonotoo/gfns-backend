package com.gnfs.GNFS.dto.staffRegistration;

import java.time.LocalDate;


import com.gnfs.GNFS.entity.GenderEnum;
import com.gnfs.GNFS.entity.MarritalStatus;

public record StaffRegistrationResponseDTO(
		 Long id,
		 String firstName,
		 String lastName,
		 String otherName,
		 GenderEnum gender,
		 String email,
		 String phoneNumber,
		 LocalDate dateOfBirth,
		 RegionReferenceDTO region,
		 DistrictReferenceDTO district,
		 MarritalStatus marritalStatus,
		 String photo,
		 String fullName
		) {

}
