package com.gnfs.GNFS.service.building.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.building.ConstructionTypeRequestDTO;
import com.gnfs.GNFS.dto.building.ConstructionTypeResponseDTO;
import com.gnfs.GNFS.entity.BuildConstructionType;
import com.gnfs.GNFS.exceptions.ConstructionTypeNotFoundException;
import com.gnfs.GNFS.repository.BuildConstructionTypeRepository;
import com.gnfs.GNFS.service.building.ConstructionTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ConstructionTypeServiceImpl implements ConstructionTypeService{
	
	private final BuildConstructionTypeRepository constructionRepo;
	private final ModelMapper mapper;

	@Override
	public List<ConstructionTypeResponseDTO> listConstruction() {
		List<BuildConstructionType> listBuild = constructionRepo.findAll();
		return listBuild.stream().map(consType -> convertFromEntityToDTO(consType)).collect(Collectors.toList());
	}

	@Override
	public ConstructionTypeResponseDTO getConstructionId(Integer id) {
		BuildConstructionType getConstructionType = constructionRepo.findById(id)
				.orElseThrow(() -> new ConstructionTypeNotFoundException("Could not find contruction type with ID: " + id));
		
		
		return convertFromEntityToDTO(getConstructionType);
	}

	@Override
	public ConstructionTypeResponseDTO createConstruction(ConstructionTypeRequestDTO buildingType) {
		BuildConstructionType saveBuildConstructionType = convertFromDTOToEntity(buildingType);
		BuildConstructionType save = constructionRepo.save(saveBuildConstructionType);
		return convertFromEntityToDTO(save);
	}

	@Override
	public ConstructionTypeResponseDTO updateConstruction(Integer id, ConstructionTypeRequestDTO buildingType) {
		
		BuildConstructionType existingConstructionTypes = constructionRepo.findById(id)
				.orElseThrow(() -> new ConstructionTypeNotFoundException("Could not find contruction type with ID: " + id));
		
		existingConstructionTypes.setName(buildingType.getName());
		existingConstructionTypes.setDescription(buildingType.getDescription());
		BuildConstructionType updated = constructionRepo.save(existingConstructionTypes);
		return convertFromEntityToDTO(updated);
	}

	@Override
	public void deleteConstruction(Integer id) {
		Integer countById = constructionRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new ConstructionTypeNotFoundException("Could not found Construction Type with ID: " + id);
		}
		
		constructionRepo.deleteById(countById);
	}
	
	
	//private method that convert Entity to DTO
	public ConstructionTypeResponseDTO convertFromEntityToDTO(BuildConstructionType buildConstructionType) {
		return mapper.map(buildConstructionType, ConstructionTypeResponseDTO.class);
	}
	
	//private method that convert DTO to entity
	private BuildConstructionType convertFromDTOToEntity(ConstructionTypeRequestDTO constructionTypeResponseDTO) {
		return mapper.map(constructionTypeResponseDTO, BuildConstructionType.class);
	}

}
