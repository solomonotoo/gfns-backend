package com.gnfs.GNFS.controller.finance;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.service.finance.ApplicationFormService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applications")
public class ApplicationFormRestController {
	private final ApplicationFormService applicationService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listApplications(){
		List<?> applications = applicationService.listApplicationForm();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Application List fetched successfully!", applications);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getApplicationId(@PathVariable Integer id){
		ApplicationFormResponseDTO responseDTO = applicationService.getApplicationFormId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Application with ID: "+id+" exist in the database", responseDTO);
		return ResponseEntity.ok(response);
	}

	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createApplication(@RequestBody ApplicationFormRequestDTO requestDTO){
		ApplicationFormResponseDTO responseDTO = applicationService.createApplicationForm(requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Application created successfully!", responseDTO);
		return ResponseEntity.created(URI.create("/api/v1/applications" + responseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateApplication(@PathVariable Integer id, @RequestBody ApplicationFormRequestDTO requestDTO){
		ApplicationFormResponseDTO responseDTO = applicationService.updateApplicationForm(id, requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Application with ID: "+id+" updated successfully!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteApplication(@PathVariable Integer id){
		applicationService.deleteApplicationForm(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Application with ID: "+id+" deleted successfully!", null);
		return ResponseEntity.ok(response);
	}
	
}
