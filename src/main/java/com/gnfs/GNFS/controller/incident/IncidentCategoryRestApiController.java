package com.gnfs.GNFS.controller.incident;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.incident.IncidentCategoryRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentCategoryResponseDTO;
import com.gnfs.GNFS.service.incident.IncidentCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/incident-categories")
@Validated
public class IncidentCategoryRestApiController {

	private final IncidentCategoryService incidentCategoryService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listIncidentCategories(){
		List<?> listIncidentCategories = incidentCategoryService.listIncidentCategory();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Incident Category list fetched successfully!", listIncidentCategories);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getIncidentCategory(@PathVariable Integer id){
		IncidentCategoryResponseDTO responseDTO = incidentCategoryService.getIncidentCategoryId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Category ID: " + id + " exist in the database", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createIncidentCategory(@Valid @RequestBody IncidentCategoryRequestDTO dto) {

		IncidentCategoryResponseDTO responseDTO = incidentCategoryService.createIncidentCategory(dto);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Category Created Successfully!", responseDTO);
	
		return ResponseEntity.created(URI.create("/api/v1/incident-categories/" + responseDTO.getId())).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateIncidentCategory(@PathVariable Integer id, @RequestBody @Valid IncidentCategoryRequestDTO dto) {
		IncidentCategoryResponseDTO responseDTO = incidentCategoryService.updateIncidentCategory(id,dto);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Category Updated Successfully!", responseDTO);
	
		
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> deleteIncidentCategory(@PathVariable Integer id){
		incidentCategoryService.deleteIncidentCategory(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Category Deleted Successfully!", null);
	
		
		return ResponseEntity.ok(response);
	}
}
