package com.gnfs.GNFS.service.applicationManager.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.nodes.ScalarNode;

import com.gnfs.GNFS.dto.appManager.RegistrationFormRequestDTO;
import com.gnfs.GNFS.dto.appManager.RegistrationFormResponseDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.entity.ApplicationForm;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.BuildConstructionType;
import com.gnfs.GNFS.entity.BuildingType;
import com.gnfs.GNFS.entity.BusinessClass;
import com.gnfs.GNFS.entity.BusinessType;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.entity.EquipAlarmSystem;
import com.gnfs.GNFS.entity.EquipEscapeMeans;
import com.gnfs.GNFS.entity.EquipFireFightingSystem;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.entity.RegistrationForm;
import com.gnfs.GNFS.exceptions.EquipAlarmSystemNotFoundException;
import com.gnfs.GNFS.exceptions.EquipFireFightingSystemNotFoundException;
import com.gnfs.GNFS.exceptions.RegistrationFormNotFoundException;
import com.gnfs.GNFS.repository.ApplicationFormRespository;
import com.gnfs.GNFS.repository.BuildConstructionTypeRepository;
import com.gnfs.GNFS.repository.BuildingTypeRepository;
import com.gnfs.GNFS.repository.BusinessClassRepository;
import com.gnfs.GNFS.repository.BusinessTypeRepository;
import com.gnfs.GNFS.repository.DistrictRepository;
import com.gnfs.GNFS.repository.EquipAlarmSystemRepository;
import com.gnfs.GNFS.repository.EquipEscapeMeansRepository;
import com.gnfs.GNFS.repository.EquipFireFightingSystemRepository;
import com.gnfs.GNFS.repository.RegionRepository;
import com.gnfs.GNFS.repository.RegistrationFormRepository;
import com.gnfs.GNFS.service.applicationManager.RegistrationFormService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegistrationFormServiceImpl implements RegistrationFormService{
	
	private final RegistrationFormRepository registrationRepo;
	private final ApplicationFormRespository applicationFormRespo;
	private final RegionRepository regionRepo;
	private final DistrictRepository districtRepo;
	private final BuildConstructionTypeRepository constructionTypeRepo;
	private final BuildingTypeRepository buildingTypeRepo;
	private final EquipEscapeMeansRepository escapeMeansRepo;
	private final EquipAlarmSystemRepository alarmSystemRepo;
	private final EquipFireFightingSystemRepository fightingSystemRepo;
	private final BusinessClassRepository businessClassRepo;
	private final BusinessTypeRepository businessTypeRepo;
	private final ModelMapper mapper;

	@Override
	public List<RegistrationFormResponseDTO> listRegistrationForm() {
		List<RegistrationForm> listRegistrationForms = registrationRepo.findAll(); 
		return listRegistrationForms.stream().map(forms -> convertFromEntityToDTO(forms))
				.toList();
	}

	@Override
	public RegistrationFormResponseDTO createRegistrationForm(RegistrationFormRequestDTO RegistrationFormRequestDTO) {
		RegistrationForm registrationForm = convertFromDTOToEntity(RegistrationFormRequestDTO);
		RegistrationForm createRegistrationForm = registrationRepo.save(registrationForm);
		return convertFromEntityToDTO(createRegistrationForm);
	}

	@Override
	public RegistrationFormResponseDTO getRegistrationForm(Long id) {
		RegistrationForm registrationForm = getRegistrationFormId(id);
		return convertFromEntityToDTO(registrationForm);
	}

	@Override
	public RegistrationFormResponseDTO updataRegistrationForm(Long id,
			RegistrationFormRequestDTO RegistrationFormRequestDTO) {
		RegistrationForm existingForm = getRegistrationFormId(id);
		RegistrationForm registrationFormToUpdate = convertFromDTOToEntity(RegistrationFormRequestDTO);
		registrationFormToUpdate.setId(id);
		
		//map the updated pay bill to the existing pay bill
		mapper.map(registrationFormToUpdate, existingForm);
		RegistrationForm updateRegistrationForm = registrationRepo.save(existingForm);
		return convertFromEntityToDTO(updateRegistrationForm);
	}

	@Override
	public void deleteRegistrationForm(Long id) {
		Long countById = registrationRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new RegistrationFormNotFoundException("Could not find registraiont form ID: " + id);
		}
		registrationRepo.deleteById(id);
	}
	

	private RegistrationForm getRegistrationFormId(Long id) {
		return registrationRepo.findById(id)
				.orElseThrow(() -> new RegistrationFormNotFoundException("Could not find registraiont form ID: " + id));
	}
	
	private RegistrationFormResponseDTO convertFromEntityToDTO(RegistrationForm registrationForm) {
		return mapper.map(registrationForm, RegistrationFormResponseDTO.class);
	}
	
	private RegistrationForm convertFromDTOToEntity(RegistrationFormRequestDTO registrationFormRequestDTO) {
		RegistrationForm registrationForm = mapper.map(registrationFormRequestDTO, RegistrationForm.class);
		
		 // Manually resolve and attach existing Bill Type
//        if (registrationFormRequestDTO.getApplicationForm() != null && registrationFormRequestDTO.getApplicationForm().getId() != null) {
//        	//Long billTypeId = payBillRequestDTO.getBillType().getId().longValue(); // Convert Integer to Long
//            ApplicationForm applicationForm = applicationFormRespo.findById(registrationFormRequestDTO.getApplicationForm().getId())
//                    .orElseThrow(() -> new RuntimeException("Application Form not found with ID: " + registrationFormRequestDTO.getApplicationForm().getId()));
//            registrationForm.setApplicationForm(applicationForm);
//        } else {
//            registrationForm.setApplicationForm(null);
//        }
        
//		if(registrationFormRequestDTO.getBusinessClass() != null && registrationFormRequestDTO.getBusinessClass().getId() != null) {
//        	BusinessClass businessClass = businessClassRepo.findById(registrationFormRequestDTO.getBusinessClass().getId())
//        			.orElseThrow(() -> new RuntimeException("Business Class found with ID: " + registrationFormRequestDTO.getBusinessClass().getId()));
//        	registrationForm.setBusinessClass(businessClass);
//        }else {
//			registrationForm.setBusinessClass(null);
//		}
//		
//		if(registrationFormRequestDTO.getBusinessType() != null && registrationFormRequestDTO.getBusinessType().getId() != null) {
//        	BusinessType businessType = businessTypeRepo.findById(registrationFormRequestDTO.getBusinessType().getId())
//        			.orElseThrow(() -> new RuntimeException("Business Type not found with ID: " + registrationFormRequestDTO.getBusinessType().getId()));
//        	registrationForm.setBusinessType(businessType);
//        }else {
//			registrationForm.setBusinessType(null);
//		}
		
		if(registrationFormRequestDTO.getCertificateType() != null && registrationFormRequestDTO.getCertificateType().getId() != null) {
        	ApplicationForm applicationFormCertificateType = applicationFormRespo.findById(registrationFormRequestDTO.getCertificateType().getId())
        			.orElseThrow(() -> new RuntimeException("Certificate Type not found with ID: " + registrationFormRequestDTO.getCertificateType().getId()));
        	registrationForm.setCertificationType(applicationFormCertificateType);
        }else {
			registrationForm.setCertificationType(null);
		}
		
        if(registrationFormRequestDTO.getRegion() != null && registrationFormRequestDTO.getRegion().getId() != null) {
        	Region region = regionRepo.findById(registrationFormRequestDTO.getRegion().getId())
        			.orElseThrow(() -> new RuntimeException("Region not found with ID: " + registrationFormRequestDTO.getRegion().getId()));
        	registrationForm.setRegion(region);
        }else {
			registrationForm.setRegion(null);
		}
        
        if(registrationFormRequestDTO.getDistrict() != null && registrationFormRequestDTO.getDistrict().getId() != null) {
        	District district = districtRepo.findById(registrationFormRequestDTO.getDistrict().getId())
        			.orElseThrow(() -> new RuntimeException("District not found with ID: " + registrationFormRequestDTO.getDistrict().getId()));
        	registrationForm.setDistrict(district);
        }else {
			registrationForm.setDistrict(null);
		}
        
        if(registrationFormRequestDTO.getBuildingType() != null && registrationFormRequestDTO.getBuildingType().getId() != null) {
        	BuildingType buildingType = buildingTypeRepo.findById(registrationFormRequestDTO.getBuildingType().getId())
        			.orElseThrow(() -> new RuntimeException("Building Type not found with ID: " + registrationFormRequestDTO.getBuildingType().getId()));
        	registrationForm.setBuildingType(buildingType);
        }else {
			registrationForm.setBuildingType(null);
		}
        
        if(registrationFormRequestDTO.getConstructionType() != null && registrationFormRequestDTO.getConstructionType().getId() != null) {
        	BuildConstructionType  constructionType = constructionTypeRepo.findById(registrationFormRequestDTO.getConstructionType().getId())
        			.orElseThrow(() -> new RuntimeException("Construction Type not found with ID: " + registrationFormRequestDTO.getConstructionType().getId()));
        	registrationForm.setConstructionType(constructionType);
        }else {
			registrationForm.setConstructionType(null);
		}
        
        
        if(registrationFormRequestDTO.getConstructionType() != null && registrationFormRequestDTO.getConstructionType().getId() != null) {
        	BuildConstructionType  constructionType = constructionTypeRepo.findById(registrationFormRequestDTO.getConstructionType().getId())
        			.orElseThrow(() -> new RuntimeException("Construction Type not found with ID: " + registrationFormRequestDTO.getConstructionType().getId()));
        	registrationForm.setConstructionType(constructionType);
        }else {
			registrationForm.setConstructionType(null);
		}
        
        if (registrationFormRequestDTO.getEscapeMeans() != null && !registrationFormRequestDTO.getEscapeMeans().isEmpty()) {
            Set<EquipEscapeMeans> escapeMeansSet = registrationFormRequestDTO.getEscapeMeans().stream()
                .map(dto -> escapeMeansRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Escape means not found with ID: " + dto.getId())))
                .collect(Collectors.toSet());
            registrationForm.setEquipEscapeMeans(escapeMeansSet);
        } else {
//            registrationForm.setEquipEscapeMeans(Collections.emptySet());
        	registrationForm.setEquipEscapeMeans(new HashSet<>());
        }

        
        if (registrationFormRequestDTO.getAlarmSystems() != null && !registrationFormRequestDTO.getAlarmSystems().isEmpty()) {
			Set<EquipAlarmSystem> alarmSystems = registrationFormRequestDTO.getAlarmSystems().stream()
					.map(dto -> alarmSystemRepo.findById(dto.getId())
							.orElseThrow(() -> new EquipAlarmSystemNotFoundException("Alarm system not found with ID: " + dto.getId())))
					.collect(Collectors.toSet());
					registrationForm.setEquipAlarmSystems(alarmSystems);
		}else {
//			registrationForm.setEquipAlarmSystems(Collections.emptySet());
			registrationForm.setEquipAlarmSystems(new HashSet<>());
		}
        
        if (registrationFormRequestDTO.getFireSystems() != null && !registrationFormRequestDTO.getFireSystems().isEmpty()) {
			Set<EquipFireFightingSystem> fightingSystems = registrationFormRequestDTO.getFireSystems().stream()
					.map(dto -> fightingSystemRepo.findById(dto.getId())
							.orElseThrow(() -> new EquipFireFightingSystemNotFoundException("Alarm system not found with ID: " + dto.getId())))
					.collect(Collectors.toSet());
			registrationForm.setEquipFireFightingSystems(fightingSystems);
		}else {
//			registrationForm.setEquipFireFightingSystems(Collections.emptySet());
			registrationForm.setEquipFireFightingSystems(new HashSet<>());
		}
        
        
        
		
		return registrationForm;
	}
	
	


}
