package com.gnfs.GNFS.service.Equipment.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.equipment.EquipFireSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipFireSystemResponseDTO;
import com.gnfs.GNFS.entity.BuildingType;
import com.gnfs.GNFS.entity.EquipFireFightingSystem;
import com.gnfs.GNFS.exceptions.EquipFireFightingSystemNotFoundException;
import com.gnfs.GNFS.repository.EquipFireFightingSystemRepository;
import com.gnfs.GNFS.service.Equipment.EquipFireFightingSystemService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EquipFireFightingSystemServiceImpl implements EquipFireFightingSystemService{
	
	private final EquipFireFightingSystemRepository fireRepo;
	private final ModelMapper mapper;

	@Override
	public List<EquipFireSystemResponseDTO> listFireSystem() {
		List<EquipFireFightingSystem> listFireSystem = fireRepo.findAll();
		return listFireSystem.stream().map(alarm -> convertEntityToDTO(alarm))
				.collect(Collectors.toList());
	}

	@Override
	public EquipFireSystemResponseDTO getFireSystemId(Integer id) {
		EquipFireFightingSystem fireSystem = fireRepo.findById(id)
				.orElseThrow(()-> new EquipFireFightingSystemNotFoundException("Could not find alarm system with ID: " + id));
		return convertEntityToDTO(fireSystem);
	}

	@Override
	public EquipFireSystemResponseDTO createFireSystem(EquipFireSystemRequestDTO fireSystemRequestDTO) {
		EquipFireFightingSystem createFireSystem = converRequestDTOToEntity(fireSystemRequestDTO);
		EquipFireFightingSystem savedFireSystem = fireRepo.save(createFireSystem);
		return convertEntityToDTO(savedFireSystem);
	}

	@Override
	public EquipFireSystemResponseDTO updateFireSystem(Integer id, 
			EquipFireSystemRequestDTO fireSystemRequestDTO) {
		EquipFireFightingSystem existingFireSystem = fireRepo.findById(id)
				.orElseThrow(()-> new EquipFireFightingSystemNotFoundException("Could not find alarm system with ID: " + id));
		
		existingFireSystem.setName(fireSystemRequestDTO.getName());
		existingFireSystem.setDescription(fireSystemRequestDTO.getDescription());
		
		EquipFireFightingSystem updatedFireSystem = fireRepo.save(existingFireSystem);
		
		return convertEntityToDTO(updatedFireSystem);
	}

	@Override
	public void deleteFireSystem(Integer id) {
		Integer countById = fireRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new  EquipFireFightingSystemNotFoundException("Could not find alarm system with ID: " + id);
		}
		fireRepo.deleteById(countById);
	}

	
	private EquipFireSystemResponseDTO convertEntityToDTO(EquipFireFightingSystem equipFireSystem) {
		return mapper.map(equipFireSystem, EquipFireSystemResponseDTO.class);
	}
	
	private EquipFireFightingSystem converRequestDTOToEntity(EquipFireSystemRequestDTO equipFireSystemRequestDTO) {
		return mapper.map(equipFireSystemRequestDTO, EquipFireFightingSystem.class);
	}
	
}
