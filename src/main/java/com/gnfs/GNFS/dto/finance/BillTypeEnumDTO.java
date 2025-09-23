package com.gnfs.GNFS.dto.finance;

import com.gnfs.GNFS.entity.BillTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillTypeEnumDTO {

	private String value;
	private String displayName;
	
	// Optional: Add conversion methods
    public static BillTypeEnumDTO fromEnum(BillTypeEnum billType) {
        return new BillTypeEnumDTO(billType.name(), billType.getDisplayName());
    }

    public BillTypeEnum toEnum() {
        return BillTypeEnum.valueOf(this.value);
    }
}
