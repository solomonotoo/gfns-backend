package com.gnfs.GNFS.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.drawing.CurrencyRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDTO {

	
	private Integer id;
	private String name;
	private String description;
	private double amount;
	@JsonProperty("currency")
	private CurrencyRequestDTO currencyRequestDTO;
	
	@JsonProperty("bill_type")
	private BillTypeRequestDTO billTypeRequestDTO;
}
