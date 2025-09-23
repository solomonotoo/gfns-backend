package com.gnfs.GNFS.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.drawing.CurrencyResponseDTO;
import com.gnfs.GNFS.entity.BillTypeEnum;
import com.gnfs.GNFS.entity.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFormResponseDTO {

	private Integer id; 
	private String name;
	private double amount;
	
	@JsonProperty("bill_type")
	private BillTypeEnumDTO billType;

	private CurrencyResponseDTO currency;
}
