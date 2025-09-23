package com.gnfs.GNFS.dto.appManager;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnfs.GNFS.dto.building.BuildingTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
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

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class RegistrationFormRequestDTO {
//
//	private Long id;
//		
//	//@JsonProperty("form_number")
//	private String formNumber;
//	
//	//@JsonProperty("certificate_number")
//	private String certificateNumber;
//	private Integer numberOfFloors;
//	
//	//@JsonProperty("date_of_issue")
//	private LocalDate dateOfIssue;
//	
//	//@JsonProperty("company_name")
//	private String companyName;
//	
//	//@JsonProperty("plot_number")
//	private String plotNumber;
//	private String city;
//	private String address;
//	
//	//@JsonProperty("phone_number")
//	private String phoneNumber;
//	
//	//@JsonProperty("business_class")
//	private String businessClass;
//	
//	//@JsonProperty("business_type")
//	private String businessType;
//	
//	//@JsonProperty("previous_premises_use")
//	private String previousPremisesUse;
//	
//	//@JsonProperty("current_premises_use")
//	private String currentPremiseUse;
//	
//	//@JsonProperty("construction_date")
//	private LocalDate constructionDate;
//	
//	private Integer occupant;
//	
//	//@JsonProperty("last_name")
//	private String lastName;
//	
//	//@JsonProperty("first_name")
//	private String firstName;
//	
//	//@JsonProperty("mobile_number")
//	private String mobileNumber;
//	private String email;
//	
//	private String location;
//	
//	//@JsonProperty("propose_premises_use")
//	private String proposePremiseUse;
//	
//	//@JsonProperty("construction_type")
//	private ConstructionTypeReferenceDTO construction;
//	
//	//@JsonProperty("building_type")
//	private BuildingTypeReferenceDTO buildingType;
//	
//	//@JsonProperty("region_id")
//	private RegionReferenceDTO region;
//	
//	//@JsonProperty("district_id")
//	private DistrictReferenceDTO district;
//
//	//@JsonProperty("application_form")
//	//private ApplicationFormRequestDTO applicationForm;
//	
//	//@JsonProperty("escape")
//	private Set<EquipEscapeMeansReferenceDTO> escapeMeans;
//	
//	//@JsonProperty("fire_system")
//	private Set<EquipFireSystemReferenceDTO> fireSystems;
//	
//	//@JsonProperty("alarm_system")
//	private Set<EquipAlarmSystemReferenceDTO> alarmSystems;
//}


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormRequestDTO {

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
	private String currentPremiseUse;
	private String proposePremiseUse;
	private LocalDate constructionDate;
	private Integer occupant;
	private String lastName;
	private String firstName;
	private String mobileNumber;
	private String email;
	private String locationArea;
	private SellSoldFormsReferenceDTO soldForms;
//	private BusinessClassReferenceDTO businessClass;
//	private BusinessTypeReferenceDTO businessType;
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
