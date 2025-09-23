package com.gnfs.GNFS.controller.buildingType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.building.ConstructionTypeRequestDTO;
import com.gnfs.GNFS.dto.building.ConstructionTypeResponseDTO;
import com.gnfs.GNFS.service.building.ConstructionTypeService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/constructiontypes")
@RequiredArgsConstructor
public class ConstructionTypeRestController {
	
	private final ConstructionTypeService constructionTypeService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<ConstructionTypeResponseDTO>>> listConstructionTypes() {
		List<ConstructionTypeResponseDTO> listConstructonTypes = constructionTypeService.listConstruction();
		CustomSuccessMessageResponse<List<ConstructionTypeResponseDTO>> response = new CustomSuccessMessageResponse<>("Construction types fetched successfully!", listConstructonTypes);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<ConstructionTypeResponseDTO>> MethodName(@PathVariable Integer id) {
		ConstructionTypeResponseDTO geConstructionTypeResponseDTO = constructionTypeService.getConstructionId(id);
		
		CustomSuccessMessageResponse<ConstructionTypeResponseDTO> response = new CustomSuccessMessageResponse<>("Construction types exist in the database!", geConstructionTypeResponseDTO);
		return ResponseEntity.ok(response);

	}
	
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<ConstructionTypeResponseDTO>> createConstructionType(
			@RequestBody ConstructionTypeRequestDTO constructionTypeRequestDTO) {
		ConstructionTypeResponseDTO created = constructionTypeService.createConstruction(constructionTypeRequestDTO);
		CustomSuccessMessageResponse<ConstructionTypeResponseDTO> success = new CustomSuccessMessageResponse<ConstructionTypeResponseDTO>(
				"Constructon Type created successfully!", created);

		return ResponseEntity.created(URI.create("/api/v1/constructiontypes" + created.getId())).body(success);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<ConstructionTypeResponseDTO>> updateConstructionType(@PathVariable Integer id,@RequestBody ConstructionTypeRequestDTO constructionTypeRequestDTO){
		ConstructionTypeResponseDTO responseDTO = constructionTypeService.updateConstruction(id, constructionTypeRequestDTO);
	
		CustomSuccessMessageResponse<ConstructionTypeResponseDTO> success = new CustomSuccessMessageResponse<ConstructionTypeResponseDTO>(
				"Constructon Type updated successfully!", responseDTO);

		return ResponseEntity.ok(success);
	
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteConstructionType(@PathVariable Integer id){
		constructionTypeService.deleteConstruction(id);
		CustomSuccessMessageResponse<Void> success = new CustomSuccessMessageResponse<>(
				"Constructon Type updated successfully!", null);

		return ResponseEntity.ok(success);
	}

}
