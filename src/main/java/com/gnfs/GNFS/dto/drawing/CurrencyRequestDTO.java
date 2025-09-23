package com.gnfs.GNFS.dto.drawing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequestDTO {
	private Integer id;  // Required to resolve currency issue from DB in ApplicaitonFormRequestDTO
	private String name;
	private String description;

}
