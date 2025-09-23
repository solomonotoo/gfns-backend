package com.gnfs.GNFS.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;




/**
 *  for enum
 * @ElementCollection(fetch = FetchType.EAGER )
	@CollectionTable(
			name="applicant_escape",
			joinColumns = @JoinColumn(name="applicant_id")
			)
	@Enumerated(EnumType.STRING) 
	
	
	for entity 
	
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	Set<Role> roles = new HashSet();
 */

@Entity
@Table(name = "registration")
@Data
public class RegistrationForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String formNumber;
	
	@Column(unique = true)
	private String certificateNumber;
	private Integer floors;
	private LocalDate dateOfIssue;
	private String companyName;
	private String plotNumber;
	private String city;
	private String address;
	private String phoneNumber;
	
	
	
	@Column(name = "previous_premises_use")
	private String previousPremisesUse;
	
	@Column(name = "current_premises_use")
	private String currentPremiseUse;
	
	@Column(name = "construction_date")
	private LocalDate constructionDate;
	
	private Integer occupant;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	private String email;
	
	
	@Column(name = "propose_premises_use")
	private String proposePremiseUse;
	
	@Column(name = "location_area")
	private String locationArea;
	
//	@ManyToOne
//	@JoinColumn(name = "business_class")
//	private BusinessClass businessClass;
	
//	@ManyToOne
//	@JoinColumn(name = "business_Type")
//	private BusinessType businessType;
	
	@ManyToOne
	@JoinColumn(name = "certification_Type_id")
	private ApplicationForm certificationType;
	
	@ManyToOne
	@JoinColumn(name = "facility_id")
	private Facility facility;
	
	@ManyToOne
	@JoinColumn(name = "facility_type_id")
	private FacilityType facilityType;
	
	@ManyToOne
	@JoinColumn(name = "construction_type")
	private BuildConstructionType constructionType;
	
	@ManyToOne
	@JoinColumn(name = "building_type")
	private BuildingType buildingType;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	@ManyToOne
	@JoinColumn(name = "sold_forms_id")
	private SellSoldForms soldForms;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "applicant_escape",
	joinColumns= @JoinColumn(name="applicant_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="escape_id"))
	private Set<EquipEscapeMeans> equipEscapeMeans;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "applicant_fire_fighting",
	joinColumns= @JoinColumn(name="applicant_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="fire_fight_id"))
	private Set<EquipFireFightingSystem> equipFireFightingSystems;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "applicant_alarm_devices",
	joinColumns= @JoinColumn(name="applicant_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="fire_alarm_id"))
	private Set<EquipAlarmSystem> equipAlarmSystems;
	

//	@ElementCollection(fetch = FetchType.EAGER )
//	@CollectionTable(
//			name="applicant_escape",
//			joinColumns = @JoinColumn(name="applicant_id")
//			)
//	@Enumerated(EnumType.STRING)
//	private Set<EscapeMeansEnum> escapeMeansEnum;
//	
//	@ElementCollection(fetch = FetchType.EAGER )
//	@CollectionTable(
//			name="applicant_escape",
//			joinColumns = @JoinColumn(name="applicant_id")
//			)
//	@Enumerated(EnumType.STRING)
//	private Set<FireFightEnum> fireFightEnum;
//	
//	
//	@ElementCollection(fetch = FetchType.EAGER )
//	@CollectionTable(
//			name="applicant_escape",
//			joinColumns = @JoinColumn(name="applicant_id")
//			)
//	@Enumerated(EnumType.STRING)
//	private Set<AccessRouteEnum> accessRouteEnum;
	
}



	
	
	
	