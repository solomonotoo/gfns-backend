package com.gnfs.GNFS.service.Equipment.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansResponseDTO;
import com.gnfs.GNFS.entity.EquipEscapeMeans;
import com.gnfs.GNFS.exceptions.EquipEscapeMeansNotFoundException;
import com.gnfs.GNFS.repository.EquipEscapeMeansRepository;
import com.gnfs.GNFS.service.Equipment.EquipEscapeMeansService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipEscapeMeansServiceImpl implements EquipEscapeMeansService{
	
	private final EquipEscapeMeansRepository escapeRepo;
	private final ModelMapper mapper;

	@Override
	public List<EquipEscapeMeansResponseDTO> listEscapeMeans() {
         List<EquipEscapeMeans>	listEscapeMeans = escapeRepo.findAll();
         return listEscapeMeans.stream().map(escape -> convertFromEntityToResponseDTO(escape)).collect(Collectors.toList());
	}

	@Override
	public EquipEscapeMeansResponseDTO getEscapeMeansId(Integer id) {
		EquipEscapeMeans equipEscapeMeans = getEscapeMeansIdOrThrow(id);
		return convertFromEntityToResponseDTO(equipEscapeMeans);
	}

	@Override
	public EquipEscapeMeansResponseDTO createEscapeMeans(EquipEscapeMeansRequestDTO escapeMeansRequestDTO) {
		EquipEscapeMeans escapeMeans = convertFromRequestDTOToEntity(escapeMeansRequestDTO);
		EquipEscapeMeans createEscapeMeans = escapeRepo.save(escapeMeans);
		return convertFromEntityToResponseDTO(createEscapeMeans);
	}

	@Override
	public EquipEscapeMeansResponseDTO updateEscapeMeans(Integer id, EquipEscapeMeansRequestDTO escapeMeansRequestDTO) {
		EquipEscapeMeans existingEscapeMeans = getEscapeMeansIdOrThrow(id);
		
		existingEscapeMeans.setName(escapeMeansRequestDTO.getName());
		existingEscapeMeans.setDescription(escapeMeansRequestDTO.getDescription());
		EquipEscapeMeans updatedEscapeMeans = escapeRepo.save(existingEscapeMeans);
		return convertFromEntityToResponseDTO(updatedEscapeMeans);
	}

	@Override
	public void deleteEscapeMeans(Integer id) {
		Integer countById = escapeRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new EquipEscapeMeansNotFoundException("Could not find Escape Means with ID: " + id);
		}
		
		escapeRepo.deleteById(id);
		
	}

	//private method that get escape means id or throw exception
	private EquipEscapeMeans getEscapeMeansIdOrThrow(Integer id) {
		return escapeRepo.findById(id)
				.orElseThrow(() -> new EquipEscapeMeansNotFoundException("Could not find Escape Means with ID: " + id));
		
	}
	
	private EquipEscapeMeansResponseDTO convertFromEntityToResponseDTO(EquipEscapeMeans equipEscapeMeans) {
		return mapper.map(equipEscapeMeans, EquipEscapeMeansResponseDTO.class);
	}
	

	private EquipEscapeMeans convertFromRequestDTOToEntity(EquipEscapeMeansRequestDTO equipEscapeMeansRequestDTO) {
		return mapper.map(equipEscapeMeansRequestDTO, EquipEscapeMeans.class);
	}
}
