package com.gnfs.GNFS.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.drawing.CurrencyResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

	
	private Integer id;
	private String name;
	private String description;
	private double amount;
	
	@JsonProperty("currency")
	private CurrencyResponseDTO currency;
	
	@JsonProperty("bill_type")
	private BillTypeResponseDTO bill_type;
}
