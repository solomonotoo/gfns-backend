package com.gnfs.GNFS.controller.incident;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.incident.IncidentTypeRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentTypeResponseDTO;
import com.gnfs.GNFS.service.incident.IncidentTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/incident-types")
@RequiredArgsConstructor
public class IncidentTypeRestApiController {
	
	private final IncidentTypeService incidentTypeService;
	
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listAllIncidentType(){
		
		List<?> listIncidentType = incidentTypeService.listIncidentType();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<>("Incident Types List fetched successfully!", listIncidentType);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getIncidenttype(@PathVariable Integer id){
		IncidentTypeResponseDTO incidentType = incidentTypeService.getIncidentTypeId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Types exist in the database!", incidentType);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createIncidentType(@RequestBody IncidentTypeRequestDTO dto) {
		IncidentTypeResponseDTO incidentType = incidentTypeService.createIncidentType(dto);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Types Created Successfully!", incidentType);
		
		return ResponseEntity.created(URI.create("/api/v1/incident-types/" + incidentType.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updatedIncidentType(@PathVariable Integer id, @RequestBody @Valid IncidentTypeRequestDTO dto){
		
		IncidentTypeResponseDTO incidentType = incidentTypeService.updateIncidentType(id, dto);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Types Updated Successfully!", incidentType);
		
		return ResponseEntity.ok(response);	
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> deleteIncidentType(@PathVariable Integer id){
		incidentTypeService.deleteIncidentType(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Types Deleted Successfully!", null);
		
		return ResponseEntity.ok(response);	
	}

}
