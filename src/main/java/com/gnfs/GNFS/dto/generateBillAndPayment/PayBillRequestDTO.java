package com.gnfs.GNFS.dto.generateBillAndPayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.enums.PaymentModeEnumDTO;
import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsRequestDTO;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.PaymentModeEnum;
import com.gnfs.GNFS.entity.SellSoldForms;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayBillRequestDTO {

	private Integer id;
	private String amount;
	private String comment;

	@JsonProperty("bill_type")
	private BillTypeRequestDTO billType;
	
	@JsonProperty("payment_type")
	private PaymentModeEnumDTO paymentType;
	
	@NotNull
	@JsonProperty("sell_sold_form")
	private SellSoldFormReferencedDTO sellSoldForms;
}
