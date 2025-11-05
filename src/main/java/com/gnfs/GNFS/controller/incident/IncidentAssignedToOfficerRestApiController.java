package com.gnfs.GNFS.controller.incident;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerResponseDTO;
import com.gnfs.GNFS.service.incident.IncidentAssignedToOfficerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assigned-officer")
public class IncidentAssignedToOfficerRestApiController {
	
	private final IncidentAssignedToOfficerService incidentAssignedToOfficerService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listIncidents(){
		List<?> responseDTO = incidentAssignedToOfficerService.getIncidentList();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<>("Officer ID retrieved successfully!", responseDTO);
		
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getAssignedOfficerId(@PathVariable Long id){
		
		IncidentAssignedToOfficerResponseDTO responseDTO = incidentAssignedToOfficerService.getIncidentAssignedToOfficerId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Officer ID retrieved successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createAssignedOfficer(@RequestBody IncidentAssignedToOfficerRequestDTO requestDTO){
		
		IncidentAssignedToOfficerResponseDTO responseDTO = incidentAssignedToOfficerService.createIncidentAssignedToOfficer(requestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Officer Assigned to Incident Successfully!", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/assigned-officer/" + requestDTO.id())).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateIncidentAssignedOfficer(
			@PathVariable Long id, 
			@RequestBody IncidentAssignedToOfficerRequestDTO requestDTO) {
	
		IncidentAssignedToOfficerResponseDTO responseDTO = incidentAssignedToOfficerService.updateIncidentAssignedToOfficer(id, requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Officer Assigned to Incident Updated Successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/priorities")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	

}
