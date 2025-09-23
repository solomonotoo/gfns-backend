package com.gnfs.GNFS.dto.business;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessClassReferenceRequestDTO {

	@NotNull
	private Integer id;
	private String name;
}
