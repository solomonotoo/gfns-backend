package com.gnfs.GNFS.dto.generateBillAndPayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.enums.PaymentModeEnumDTO;
import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsResponseDTO;
import com.gnfs.GNFS.entity.SellSoldForms;

import lombok.Data;

@Data
public class PayBillResponseDTO {

	private Integer id;
	private String amount;
	private String comment;

	@JsonProperty("bill_type")
	private BillTypeRequestDTO billType;
	
	@JsonProperty("payment_type")
	private PaymentModeEnumDTO paymentType;
	
	@JsonProperty("sell_sold_form")
	private SellSoldFormReferencedDTO sellSoldForms;
}
