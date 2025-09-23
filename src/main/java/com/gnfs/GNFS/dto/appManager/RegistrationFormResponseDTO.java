package com.gnfs.GNFS.dto.appManager;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.building.BuildingTypeResponseDTO;
import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemResponseDTO;
import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansResponseDTO;
import com.gnfs.GNFS.dto.equipment.EquipFireSystemResponseDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.dto.referenceDTO.RegionReferenceDTO;
import com.gnfs.GNFS.entity.ApplicationForm;
import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.entity.BusinessType;
import com.gnfs.GNFS.entity.CertificationTypeEnum;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.entity.SellSoldForms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormResponseDTO {

	private Long id;
	private String formNumber;
	private ApplicationFormReferenceDTO certificateType;
	private String certificateNumber;
	private Integer floors;
	private LocalDate dateOfIssue;
	private String companyName;
	private String plotNumber;
	private String city;
	private String address;
	private String phoneNumber;
	private String previousPremisesUse;
	private String currentPremisesUse;
	private String proposePremisesUse;
	private LocalDate constructionDate;
	private Integer occupant;
	private String lastName;
	private String firstName;
	private String mobileNumber;
	private String email;
	private String locationArea;
	private SellSoldFormsReferenceDTO soldForms;
//	private BusinessClass businessClass;
//	private BusinessType businessType;
	private FacilityReferenceDTO facility;
	private FacilityTypeReferenceDTO facilityType;
	private ConstructionTypeReferenceDTO constructionType;
	private BuildingTypeReferenceDTO buildingType;
	private RegionReferenceDTO region;
	private DistrictReferenceDTO district;
	private Set<EquipEscapeMeansReferenceDTO> escapeMeans;
	private Set<EquipFireSystemReferenceDTO> fireSystems;
	private Set<EquipAlarmSystemReferenceDTO> alarmSystems;
}
	
	

