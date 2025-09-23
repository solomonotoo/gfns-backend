package com.gnfs.GNFS.dto.generateBillAndPayment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SellSoldFormReferencedDTO {

	@NotNull
	private Long id;
}
