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
import com.gnfs.GNFS.dto.facility.FacilityTypeRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityTypeResponseDTO;
import com.gnfs.GNFS.service.facility.FacilityTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/facilitytypes")
@RequiredArgsConstructor
public class FacilityTypeRestController {
	
	private final FacilityTypeService facilityTypeService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listFaciltities(){
		List<?> facilitiesList = facilityTypeService.listFacilityTypes();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Facilities list fetched successfully!", facilitiesList);
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getFacility(@PathVariable Integer id){
		FacilityTypeResponseDTO responseDTO = facilityTypeService.getFacilityType(id);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Facility Type with ID: " + id + "exist in the database!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createFacility(@RequestBody FacilityTypeRequestDTO facilityRequestDTO){
		FacilityTypeResponseDTO responseDTO = facilityTypeService.createFacilityType(facilityRequestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("New Faciility created successfully!", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/facilities" + responseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateFacility(@PathVariable Integer id, @RequestBody FacilityTypeRequestDTO facilityRequestDTO){
		FacilityTypeResponseDTO responseDTO = facilityTypeService.updataFacilityType(id, facilityRequestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Faciility with ID: " + id + " updated successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteFacility(@PathVariable Integer id){
		facilityTypeService.deleteFacilityType(id);
		
CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Faciility with ID: " + id + " deleted successfully!", null);
		
		return ResponseEntity.ok(response);
	}

}
