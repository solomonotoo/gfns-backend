package com.gnfs.GNFS.service.building;

import java.util.List;


import com.gnfs.GNFS.dto.building.BuildingTypeRequestDTO;
import com.gnfs.GNFS.dto.building.BuildingTypeResponseDTO;

public interface BuildingTypeService {

	public List<BuildingTypeResponseDTO> listBuilding();
	public BuildingTypeResponseDTO getBuildingId(Integer id);
	public BuildingTypeResponseDTO createBuilding(BuildingTypeRequestDTO buildingType);
	public BuildingTypeResponseDTO updateBuilding(Integer id, BuildingTypeRequestDTO buildingType);
	public void deleteBuilding(Integer id);

	
}