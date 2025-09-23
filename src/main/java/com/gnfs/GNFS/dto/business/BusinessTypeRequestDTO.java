package com.gnfs.GNFS.dto.business;


import com.gnfs.GNFS.entity.StatusEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTypeRequestDTO {
	
	private Integer id;
	private String name;
	private String description;
	
	@NotNull
	private StatusEnum status;
	private BusinessClassReferenceRequestDTO businessClass;

}
