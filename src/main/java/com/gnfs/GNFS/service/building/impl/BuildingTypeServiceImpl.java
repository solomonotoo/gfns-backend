package com.gnfs.GNFS.service.building.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.building.BuildingTypeRequestDTO;
import com.gnfs.GNFS.dto.building.BuildingTypeResponseDTO;
import com.gnfs.GNFS.entity.BuildingType;
import com.gnfs.GNFS.exceptions.BuildingTypeNotFoundException;
import com.gnfs.GNFS.repository.BuildingTypeRepository;
import com.gnfs.GNFS.service.building.BuildingTypeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuildingTypeServiceImpl implements BuildingTypeService {

	private final BuildingTypeRepository buildRepo;
	private final  ModelMapper mapper;

	@Override
	public List<BuildingTypeResponseDTO> listBuilding() {
		List<BuildingType> buildingTypes = buildRepo.findAll();

		return buildingTypes.stream().map(building -> new BuildingTypeResponseDTO(building.getId(), building.getName(),
				building.getDescription())).collect(Collectors.toList());
	}

	@Override
	public BuildingTypeResponseDTO getBuildingId(Integer id) {
		BuildingType buildingType = buildRepo.findById(id).orElseThrow(()-> new BuildingTypeNotFoundException("Could not find building with ID: " + id));
		//return new BuildingTypeResponseDTO(buildingType.getId(), buildingType.getName(), buildingType.getDescription());
		return convertEntityToDTO(buildingType);
	}

	@Override
	public BuildingTypeResponseDTO createBuilding(BuildingTypeRequestDTO buildingType) {
		BuildingType saveBuildingType = converRequestDTOToEntity(buildingType);
		BuildingType save = buildRepo.save(saveBuildingType);
		
		
		return convertEntityToDTO(save);
	}

	@Override
	public BuildingTypeResponseDTO updateBuilding(Integer id, BuildingTypeRequestDTO buildingTypeDTO) {
		BuildingType existingBuildingType = buildRepo.findById(id)
				.orElseThrow(()-> new BuildingTypeNotFoundException("Could not find building with ID: " + id));
		
		//mapping can be used here but eg  mapper.map(buildingTypeDTO, existingBuildingType);
		//But be careful with that—it might overwrite unintended fields (e.g., ID or audit fields).
		
		existingBuildingType.setName(buildingTypeDTO.getName());
	    existingBuildingType.setDescription(buildingTypeDTO.getDescription());
	    
	    

	    BuildingType updated = buildRepo.save(existingBuildingType);

	    return convertEntityToDTO(updated);
	}

	@Override
	public void deleteBuilding(Integer id) {
		Integer countbyId = buildRepo.countById(id);

		if(countbyId == null || countbyId == 0) {
			throw new BuildingTypeNotFoundException("Could not find building type with ID: " + id);
		}
		buildRepo.deleteById(id);
	}
	
	
	private BuildingTypeResponseDTO convertEntityToDTO(BuildingType buildingType) {
		return mapper.map(buildingType, BuildingTypeResponseDTO.class);
	}
	
	private BuildingType converRequestDTOToEntity(BuildingTypeRequestDTO buildingTypeRequestDTO) {
		return mapper.map(buildingTypeRequestDTO, BuildingType.class);
	}

}
