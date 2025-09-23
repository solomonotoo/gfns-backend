package com.gnfs.GNFS.controller.incident;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.incident.IncidentClassificationRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentClassificationResponseDTO;
import com.gnfs.GNFS.service.incident.IncidentClassificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/incidents-class")
@RequiredArgsConstructor
@Validated
public class IncidentClassificationRestApiController {
	
	private final IncidentClassificationService incidentClassificationService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listIncidentClassifications(){
		List<?> incidentTypeList = incidentClassificationService.listIncidentClassification();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<>("Incident Type List Fetched Successfully!", incidentTypeList);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getIncidentClassificationId(@PathVariable Integer id) {
		IncidentClassificationResponseDTO responseDTO = incidentClassificationService.getIncidentClassificationId(id);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Type with ID: " + id + " found", responseDTO);
		return  ResponseEntity.ok(response);
				
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createIncidentType(@RequestBody @Valid IncidentClassificationRequestDTO dto) {
		IncidentClassificationResponseDTO responseDTO = incidentClassificationService.createIncidentClassification(dto);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Type Created Successfully", responseDTO);
		return ResponseEntity.created(URI.create("/api/v1/incedents/" + responseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateIncidentType(@PathVariable Integer id, @RequestBody @Valid IncidentClassificationRequestDTO dto) {
		IncidentClassificationResponseDTO responseDTO = incidentClassificationService.updateIncidentClassification(id, dto);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Type Updated Successfully", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> deleteIncidentType(@PathVariable Integer id){
		incidentClassificationService.deleteIncidentClassification(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Type Deleted Successfully", null);
		return ResponseEntity.ok(response);
	}
	

}
