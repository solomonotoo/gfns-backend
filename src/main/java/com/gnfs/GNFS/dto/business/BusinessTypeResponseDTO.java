package com.gnfs.GNFS.dto.business;


import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.entity.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessTypeResponseDTO {
	private Integer id;
	private String name;
	private String description;
	private StatusEnum status;
	private BusinessClassReferenceRequestDTO businessClass;
}
