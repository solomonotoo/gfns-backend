package com.gnfs.GNFS.controller.buildingType;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.building.BuildingTypeRequestDTO;
import com.gnfs.GNFS.dto.building.BuildingTypeResponseDTO;
import com.gnfs.GNFS.service.building.BuildingTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/v1/buildingtypes")
@RequiredArgsConstructor
public class BuildingTypeRestController {

	private final BuildingTypeService buildingTypeService;
	

	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<BuildingTypeResponseDTO>>> listBuiding() {
		List<BuildingTypeResponseDTO> listBuilding = buildingTypeService.listBuilding();
		CustomSuccessMessageResponse<List<BuildingTypeResponseDTO>> successMessageResponse = new CustomSuccessMessageResponse<>(
				"Building fetched successfully", listBuilding);
		return ResponseEntity.ok(successMessageResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<BuildingTypeResponseDTO>> getBuildingTypeById(@PathVariable Integer id) {
		BuildingTypeResponseDTO buildingType = buildingTypeService.getBuildingId(id);
		
		CustomSuccessMessageResponse<BuildingTypeResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>("Buiding Type exist in the database", buildingType);
		
		return ResponseEntity.ok(successMessageResponse);
	}
	

	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<BuildingTypeResponseDTO>> createBuilding(
	        @RequestBody BuildingTypeRequestDTO buildingTypeRequestDTO) {
	    
	    BuildingTypeResponseDTO created = buildingTypeService.createBuilding(buildingTypeRequestDTO);
	    CustomSuccessMessageResponse<BuildingTypeResponseDTO> response = 
	        new CustomSuccessMessageResponse<>("Building Type created successfully", created);

	   // return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    return ResponseEntity.created(URI.create("/api/v1/buildingtypes/" + created.getId())).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<BuildingTypeResponseDTO>> updateBuilding(
	        @PathVariable Integer id, 
	        @RequestBody BuildingTypeRequestDTO buildingTypeRequestDTO) {
	    
	    BuildingTypeResponseDTO updated = buildingTypeService.updateBuilding(id, buildingTypeRequestDTO);
	    CustomSuccessMessageResponse<BuildingTypeResponseDTO> response = 
	        new CustomSuccessMessageResponse<>("Building Type updated successfully", updated);

	    return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<String>> deleteBuildingType(@PathVariable Integer id){
		buildingTypeService.deleteBuilding(id);
		CustomSuccessMessageResponse<String> response = new CustomSuccessMessageResponse<String>("Building Type deleted successfully", null);
		
		return ResponseEntity.ok(response);
	}

}
