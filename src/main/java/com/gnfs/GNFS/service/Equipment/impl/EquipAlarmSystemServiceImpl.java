package com.gnfs.GNFS.service.Equipment.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemResponseDTO;
import com.gnfs.GNFS.entity.BuildingType;
import com.gnfs.GNFS.entity.EquipAlarmSystem;
import com.gnfs.GNFS.exceptions.EquipAlarmSystemNotFoundException;
import com.gnfs.GNFS.repository.EquipAlarmSystemRepository;
import com.gnfs.GNFS.service.Equipment.EquipAlarmSystemService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EquipAlarmSystemServiceImpl implements EquipAlarmSystemService{
	
	private final EquipAlarmSystemRepository alarmRepo;
	private final ModelMapper mapper;

	@Override
	public List<EquipAlarmSystemResponseDTO> listAlarmSystem() {
		List<EquipAlarmSystem> listAlarmSystem = alarmRepo.findAll();
		return listAlarmSystem.stream().map(alarm -> convertEntityToDTO(alarm))
				.collect(Collectors.toList());
	}

	@Override
	public EquipAlarmSystemResponseDTO getAlarmSystemId(Integer id) {
		EquipAlarmSystem alarmSystem = alarmRepo.findById(id)
				.orElseThrow(()-> new EquipAlarmSystemNotFoundException("Could not find alarm system with ID: " + id));
		return convertEntityToDTO(alarmSystem);
	}

	@Override
	public EquipAlarmSystemResponseDTO createAlarmSystem(EquipAlarmSystemRequestDTO alarmSystemRequestDTO) {
		EquipAlarmSystem createAlarmSystem = converRequestDTOToEntity(alarmSystemRequestDTO);
		EquipAlarmSystem savedAlarmSystem = alarmRepo.save(createAlarmSystem);
		return convertEntityToDTO(savedAlarmSystem);
	}

	@Override
	public EquipAlarmSystemResponseDTO updateAlarmSystem(Integer id, 
			EquipAlarmSystemRequestDTO alarmSystemRequestDTO) {
		EquipAlarmSystem existingAlarmSystem = alarmRepo.findById(id)
				.orElseThrow(()-> new EquipAlarmSystemNotFoundException("Could not find alarm system with ID: " + id));
		
		existingAlarmSystem.setName(alarmSystemRequestDTO.getName());
		existingAlarmSystem.setDescription(alarmSystemRequestDTO.getDescription());
		
		EquipAlarmSystem updatedAlarmSystem = alarmRepo.save(existingAlarmSystem);
		
		return convertEntityToDTO(updatedAlarmSystem);
	}

	@Override
	public void deleteAlarmSystem(Integer id) {
		Integer countById = alarmRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new  EquipAlarmSystemNotFoundException("Could not find alarm system with ID: " + id);
		}
		alarmRepo.deleteById(countById);
	}

	
	private EquipAlarmSystemResponseDTO convertEntityToDTO(EquipAlarmSystem equipAlarmSystem) {
		return mapper.map(equipAlarmSystem, EquipAlarmSystemResponseDTO.class);
	}
	
	private EquipAlarmSystem converRequestDTOToEntity(EquipAlarmSystemRequestDTO equipAlarmSystemRequestDTO) {
		return mapper.map(equipAlarmSystemRequestDTO, EquipAlarmSystem.class);
	}
	
}
