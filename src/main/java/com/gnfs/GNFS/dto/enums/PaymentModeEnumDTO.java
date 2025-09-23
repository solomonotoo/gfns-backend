package com.gnfs.GNFS.dto.enums;

import com.gnfs.GNFS.entity.PaymentModeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModeEnumDTO {
	private String value;
	private String displayName;
	
	// Optional: Add conversion methods
    public static PaymentModeEnumDTO fromEnum(PaymentModeEnum paymentModeDTO) {
        return new PaymentModeEnumDTO(paymentModeDTO.name(), paymentModeDTO.getDisplayName());
    }

    public PaymentModeEnum toEnum() {
        return PaymentModeEnum.valueOf(this.value);
    }
}
