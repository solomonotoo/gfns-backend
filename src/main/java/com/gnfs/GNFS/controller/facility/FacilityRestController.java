package com.gnfs.GNFS.controller.facility;

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
import com.gnfs.GNFS.dto.facility.FacilityRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityResponseDTO;
import com.gnfs.GNFS.service.facility.FacilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/facilities")
public class FacilityRestController {
	
	private final FacilityService facilityService;

	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listFaciltities(){
		List<?> facilitiesList = facilityService.listFacilities();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Facilities list fetched successfully!", facilitiesList);
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getFacility(@PathVariable Integer id){
		FacilityResponseDTO responseDTO = facilityService.getFacility(id);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Facility with ID: " + id + "exist in the database!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createFacility(@RequestBody FacilityRequestDTO facilityRequestDTO){
		FacilityResponseDTO responseDTO = facilityService.createFacility(facilityRequestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("New Faciility created successfully!", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/facilities" + responseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateFacility(@PathVariable Integer id, @RequestBody FacilityRequestDTO facilityRequestDTO){
		FacilityResponseDTO responseDTO = facilityService.updataFacility(id, facilityRequestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Faciility with ID: " + id + " updated successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteFacility(@PathVariable Integer id){
		facilityService.deleteFacility(id);
		
CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Faciility with ID: " + id + " deleted successfully!", null);
		
		return ResponseEntity.ok(response);
	}
	
}
