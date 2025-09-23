package com.gnfs.GNFS.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillTypeRequestDTO {

	private Integer id;
	private String name;
	private String description;
}
