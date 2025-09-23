package com.gnfs.GNFS.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillTypeResponseDTO {

	private Integer id;
	private String name;
	private String description;
}
