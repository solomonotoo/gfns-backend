package com.gnfs.GNFS.controller.business;

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
import com.gnfs.GNFS.dto.business.BusinessTypeRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessTypeResponseDTO;
import com.gnfs.GNFS.service.business.BusinessTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/business-types")
@RequiredArgsConstructor
public class BusinessTypeController {
	
	private final BusinessTypeService businessTypeService;

	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listBusinessTypes(){
		List<BusinessTypeResponseDTO> responseDTO = businessTypeService.listbusinessType();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Business Type Fetched successfull!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getBusinessType(@PathVariable Integer id){
		BusinessTypeResponseDTO responseDTO = businessTypeService.getbusinessType(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Type ID: " +id+" exist in the database", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createBusinessType(@RequestBody BusinessTypeRequestDTO requestDTO){
		 BusinessTypeResponseDTO businessTypeResponseDTO = businessTypeService.createbusinessType(requestDTO);
		 CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Type created successfully!", businessTypeResponseDTO);
		 return ResponseEntity.created(URI.create("/api/v1/businesstypes" + businessTypeResponseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateBusinessType(@PathVariable Integer id, @RequestBody BusinessTypeRequestDTO requestDTO){
		BusinessTypeResponseDTO responseDTO = businessTypeService.updatabusinessType(id, requestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Type ID: " +id+ " updated successfully!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteBusinessType(@PathVariable Integer id){
		businessTypeService.deletebusinessType(id);
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<Void>("Business Type ID: " + id + " deleted successfully!" , null);
		
		return ResponseEntity.ok(response);
	}
}
