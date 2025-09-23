package com.gnfs.GNFS.service.building;

import java.util.List;

import com.gnfs.GNFS.dto.building.ConstructionTypeRequestDTO;
import com.gnfs.GNFS.dto.building.ConstructionTypeResponseDTO;


public interface ConstructionTypeService {

	
	public List<ConstructionTypeResponseDTO> listConstruction();
	public ConstructionTypeResponseDTO getConstructionId(Integer id);
	public ConstructionTypeResponseDTO createConstruction(ConstructionTypeRequestDTO buildingType);
	public ConstructionTypeResponseDTO updateConstruction(Integer id, ConstructionTypeRequestDTO buildingType);
	public void deleteConstruction(Integer id);

}
