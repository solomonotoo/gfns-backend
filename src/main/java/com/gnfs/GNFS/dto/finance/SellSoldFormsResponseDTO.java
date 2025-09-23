package com.gnfs.GNFS.dto.finance;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.region.RegionResponseDTO;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.Region;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellSoldFormsResponseDTO {
	
private Long id;
	
	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("phone_number")
	private String phoneNumber;
	private String location;
	
	@JsonProperty("form_number")
	private String formNumber;
	
	@JsonProperty("application_form")
	private ApplicationFormResponseDTO applicaitonForm;

	private RegionResponseDTO region;
	
	@JsonProperty("created_at")
	private LocalDateTime createdAt;

}
