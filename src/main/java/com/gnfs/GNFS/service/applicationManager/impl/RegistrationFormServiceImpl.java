package com.gnfs.GNFS.service.applicationManager.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.nodes.ScalarNode;

import com.gnfs.GNFS.dto.appManager.ApplicationFormReferenceDTO;
import com.gnfs.GNFS.dto.appManager.BuildingTypeReferenceDTO;
import com.gnfs.GNFS.dto.appManager.ConstructionTypeReferenceDTO;
import com.gnfs.GNFS.dto.appManager.DistrictReferenceDTO;
import com.gnfs.GNFS.dto.appManager.EquipAlarmSystemReferenceDTO;
import com.gnfs.GNFS.dto.appManager.EquipEscapeMeansReferenceDTO;
import com.gnfs.GNFS.dto.appManager.EquipFireSystemReferenceDTO;
import com.gnfs.GNFS.dto.appManager.RegistrationFormRequestDTO;
import com.gnfs.GNFS.dto.appManager.RegistrationFormResponseDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.dto.referenceDTO.RegionReferenceDTO;
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
public class RegistrationFormServiceImpl implements RegistrationFormService {

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
		return listRegistrationForms.stream().map(forms -> convertFromEntityToDTO(forms)).toList();
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

		// map the updated form data to the existing form data
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

	private RegistrationForm convertFromDTOToEntity(RegistrationFormRequestDTO dto) {
		RegistrationForm registrationForm = mapper.map(dto, RegistrationForm.class);

		registrationForm.setCertificationType(resolveApplicationForm(dto.getCertificateType()));

		registrationForm.setRegion(resolveRegion(dto.getRegion()));

		registrationForm.setDistrict(resolveDistrict(dto.getDistrict()));

		registrationForm.setBuildingType(resolveBuildingType(dto.getBuildingType()));
		registrationForm.setConstructionType(resolveConstructionType(dto.getConstructionType()));

		registrationForm.setEquipEscapeMeans(resolveEscapeMeans(dto.getEscapeMeans()));
		registrationForm.setEquipAlarmSystems(resolveAlarmSystems(dto.getAlarmSystems()));
		registrationForm.setEquipFireFightingSystems(resolveFireSystems(dto.getFireSystems()));

		return registrationForm;
	}

	private ApplicationForm resolveApplicationForm(ApplicationFormReferenceDTO applicationFormReferenceDTO) {
		if (applicationFormReferenceDTO == null || applicationFormReferenceDTO.getId() == null)
			return null;
		return applicationFormRespo.findById(applicationFormReferenceDTO.getId()).orElseThrow(
				() -> new RuntimeException("ApplicationForm not found: " + applicationFormReferenceDTO.getId()));
	}

	private Region resolveRegion(RegionReferenceDTO ref) {
		if (ref == null || ref.getId() == null)
			return null;
		return regionRepo.findById(ref.getId())
				.orElseThrow(() -> new RuntimeException("Region not found: " + ref.getId()));
	}

	private District resolveDistrict(DistrictReferenceDTO ref) {
		if (ref == null || ref.getId() == null)
			return null;
		return districtRepo.findById(ref.getId())
				.orElseThrow(() -> new RuntimeException("District not found: " + ref.getId()));
	}

	private BuildingType resolveBuildingType(BuildingTypeReferenceDTO ref) {
		if (ref == null || ref.getId() == null)
			return null;
		return buildingTypeRepo.findById(ref.getId())
				.orElseThrow(() -> new RuntimeException("Building Type not found: " + ref.getId()));
	}

	private BuildConstructionType resolveConstructionType(ConstructionTypeReferenceDTO ref) {
		if (ref == null || ref.getId() == null)
			return null;
		return constructionTypeRepo.findById(ref.getId())
				.orElseThrow(() -> new RuntimeException("District not found: " + ref.getId()));
	}
//
//	private Set<EquipEscapeMeans> resolveEscapeMeans(List<ReferenceDTO> refs) {
//	    if (refs == null) return new HashSet<>();
//	    return refs.stream()
//	        .map(r -> escapeMeansRepo.findById(r.getId())
//	            .orElseThrow(() -> new RuntimeException("EscapeMeans not found: " + r.getId())))
//	        .collect(Collectors.toSet());
//	}

	private Set<EquipEscapeMeans> resolveEscapeMeans(Set<EquipEscapeMeansReferenceDTO> refs) {
		if (refs == null)
			return new HashSet<>();
		return refs.stream()
				.map(r -> escapeMeansRepo.findById(r.getId())
						.orElseThrow(() -> new RuntimeException("EscapeMeans not found: " + r.getId())))
				.collect(Collectors.toSet());
	}

	private Set<EquipAlarmSystem> resolveAlarmSystems(Set<EquipAlarmSystemReferenceDTO> refs) {
		if (refs == null)
			return new HashSet<>();
		return refs.stream()
				.map(r -> alarmSystemRepo.findById(r.getId()).orElseThrow(
						() -> new EquipAlarmSystemNotFoundException("AlarmSystem not found: " + r.getId())))
				.collect(Collectors.toSet());
	}

	private Set<EquipFireFightingSystem> resolveFireSystems(Set<EquipFireSystemReferenceDTO> refs) {
		if (refs == null)
			return new HashSet<>();
		return refs.stream()
				.map(r -> fightingSystemRepo.findById(r.getId()).orElseThrow(
						() -> new EquipFireFightingSystemNotFoundException("FireSystem not found: " + r.getId())))
				.collect(Collectors.toSet());
	}

}
