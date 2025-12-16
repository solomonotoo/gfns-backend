package com.gnfs.GNFS;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.ui.context.Theme;

import com.gnfs.GNFS.dto.activity.ActivityResponseDTO;
import com.gnfs.GNFS.dto.business.BusinessClassRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.dto.finance.BillRequestDTO;
import com.gnfs.GNFS.dto.finance.BillResponseDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsResponseDTO;
import com.gnfs.GNFS.dto.incident.IncidentAddRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAddResponseDTO;
import com.gnfs.GNFS.dto.incident.IncidentCategoryResponseDTO;
import com.gnfs.GNFS.dto.incident.IncidentTypeResponseDTO;
import com.gnfs.GNFS.entity.Activity;
import com.gnfs.GNFS.entity.ApplicationForm;
import com.gnfs.GNFS.entity.Bill;
import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.entity.BusinessType;
import com.gnfs.GNFS.entity.IncidentAdd;
import com.gnfs.GNFS.entity.IncidentCategory;
import com.gnfs.GNFS.entity.IncidentType;
import com.gnfs.GNFS.entity.SellSoldForms;
import com.gnfs.GNFS.sidebarmenu.SidebarMenu;
import com.gnfs.GNFS.sidebarmenu.dto.SidebarMenuResponseDTO;

@EnableJpaAuditing
@SpringBootApplication
public class GnfsApplication {

	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true); // 👈
																												// This
																												// prevents
																												// nulls
																												// from
																												// overwriting

		/*
		 * NB avoid this mapper configuration code if you have derived/transient values
		 * .setFieldMatchingEnabled(true)
		 * .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
		 */

		/*
		 * ------------------------- ACTIVITY -------------------------
		 */
//		modelMapper.typeMap(Activity.class, ActivityResponseDTO.class).addMappings(mapper -> {
//		    mapper.map(src -> src.getActivityType().getId(), ActivityResponseDTO::setActivityType);
//		});

		// NB note how activity type was mapped. since activity type is defined as
		// object in activity entity
		// you have to response a dto for activity type and use it in the activity dto
		// before you can use
		// mapper configuration. or manually do the mapping in the service layer
		modelMapper.typeMap(Activity.class, ActivityResponseDTO.class).addMappings(mapper -> {
			mapper.map(Activity::getActivityType, ActivityResponseDTO::setActivityType);
		});

		
		 /* -------------------------
	       SIDEBAR MENU
	    ------------------------- */
		modelMapper.typeMap(SidebarMenu.class, SidebarMenuResponseDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getParent() != null ? src.getParent().getId() : null,
					SidebarMenuResponseDTO::setParentId);
		});

		// modelMapper.typeMap(ActivityType.class, ActivityTypeDTO.class); // optional

		/* -------------------------
	       APPLICATION FORM
	    ------------------------- */
		// Map ApplicationFormRequestDTO → Entity (skip currency to allow DB lookup)
		modelMapper.typeMap(ApplicationFormRequestDTO.class, ApplicationForm.class).addMappings(mapper -> {
			// Only set Currency manually (already done in service)
			mapper.skip(ApplicationForm::setCurrency);
		});

		// Map ResponseDTO → Entity if needed (e.g. for internal mapping)
		modelMapper.typeMap(ApplicationFormResponseDTO.class, ApplicationForm.class)
				.addMappings(mapper -> mapper.skip(ApplicationForm::setCurrency));

		/* -------------------------
	       BILL
	    ------------------------- */
		// Map BillRequestDTO → Entity (skip currency to allow DB lookup)
		modelMapper.typeMap(BillRequestDTO.class, Bill.class).addMappings(mapper -> {
			// Only set Currency manually (already done in service)
			mapper.skip(Bill::setCurrency);
			mapper.skip(Bill::setBillType);
		});

		// Map ResponseDTO → Entity if needed (e.g. for internal mapping)
		// NB note this mappings for the difference
		modelMapper.typeMap(Bill.class, BillResponseDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getCurrency(), BillResponseDTO::setCurrency);
			mapper.map(src -> src.getBillType(), BillResponseDTO::setBill_type); // ✅ This fixes the issue
		});

		
		/* -------------------------
	       SELL / SOLD FORMS
	    ------------------------- */
		// Map SellSoldFormRequestDTO → Entity (skip application form and region to
		// allow DB lookup)
		modelMapper.typeMap(SellSoldFormsRequestDTO.class, SellSoldForms.class).addMappings(mapper -> {
			// Only set Region and Application Form manually (already done in service)
			mapper.skip(SellSoldForms::setApplicationForm);
			mapper.skip(SellSoldForms::setRegion);
		});

		// Map ResponseDTO → Entity if needed (e.g. for internal mapping)
		// NB note this mappings for the difference
		modelMapper.typeMap(SellSoldForms.class, SellSoldFormsResponseDTO.class).addMappings(mapper -> {
			// Only set Region and Application Form manually (already done in service)
			mapper.map(src -> src.getApplicationForm(), SellSoldFormsResponseDTO::setApplicaitonForm);
			mapper.map(src -> src.getRegion(), SellSoldFormsResponseDTO::setRegion);
		});

		// Map BusinessClassRequestDTO → Entity
		modelMapper.typeMap(BusinessClassRequestDTO.class, BusinessClass.class).addMappings(mapper -> {
			mapper.skip(BusinessClass::setBusinessTypes);
		});

		modelMapper.typeMap(BusinessClass.class, BusinessClass.class).addMappings(mapper -> {
			mapper.skip(BusinessClass::setBusinessTypes);
			mapper.skip(BusinessClass::setId); // Usually don't update ID
		});

		
		/* -------------------------
	       INCIDENTS
	    ------------------------- */
		// Map ResponseDTO → Entity if needed (e.g. for internal mapping)
		// NB note this mappings for the difference
		modelMapper.typeMap(IncidentCategory.class, IncidentCategoryResponseDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getClassification(), IncidentCategoryResponseDTO::setIncidentClass);

		});

		// Map ResponseDTO → Entity if needed (e.g. for internal mapping)
		modelMapper.typeMap(IncidentType.class, IncidentTypeResponseDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getCategory(), IncidentTypeResponseDTO::setCategoryReference);

		});

		// // Map BusinessTypeRequestDTO → Entity
//  	  modelMapper.typeMap(BusinessTypeRequestDTO.class, BusinessType.class)
//        .addMappings(mapper -> {
//            mapper.skip(BusinessType::setBusinessClass);
//        });
		//
		// modelMapper.typeMap(BusinessType.class, BusinessType.class)
//        .addMappings(mapper -> {
//            mapper.skip(BusinessType::setBusinessClass);
//            mapper.skip(BusinessType::setId); // Usually don't update ID
//        });

		// Map from entity object (IncidentAdd) to DTO response
		// object(IncidentAddResponseDTO)
		modelMapper.typeMap(IncidentAdd.class, IncidentAddResponseDTO.class).addMappings(mapper -> {
			mapper.map(IncidentAdd::getRegion, IncidentAddResponseDTO::setRegionReference);
			mapper.map(IncidentAdd::getIncidentCategory, IncidentAddResponseDTO::setIncidentCategory);
			mapper.map(IncidentAdd::getIncidentType, IncidentAddResponseDTO::setIncidentType);
		});

		// Map from DTO request object(IncidentAddRequestDTO) to entity object
		// (IncidentAdd).
		// skip relations to resolve manually in service layer
		modelMapper.typeMap(IncidentAddRequestDTO.class, IncidentAdd.class).addMappings(mapper -> {
			mapper.skip(IncidentAdd::setRegion);
			mapper.skip(IncidentAdd::setIncidentCategory);
			mapper.skip(IncidentAdd::setIncidentType);
		});

		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(GnfsApplication.class, args);
	}

}
