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
import com.gnfs.GNFS.dto.business.BusinessClassRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessClassResponseDTO;
import com.gnfs.GNFS.service.business.BusinessClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/businessclass")
@RequiredArgsConstructor
public class BusinessClassController {
	
	private final BusinessClassService businessClassService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listBusinesses(){
		List<?> buinsessClassList = businessClassService.listBusinessClass();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Business Class List fetched successfully!", buinsessClassList);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBusinessClass(@PathVariable Integer id){
		BusinessClassResponseDTO responseDTO = businessClassService.getBusinessClass(id);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Class ID: " +id+ " retrieved successfully!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createBusinessClass(@RequestBody BusinessClassRequestDTO requestDTO){
		BusinessClassResponseDTO responseDTO = businessClassService.createBusinessClass(requestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Class saved successfully!", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/businessclass" + requestDTO.getId())).body(response);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateBusinessClass(@PathVariable Integer id, @RequestBody BusinessClassRequestDTO requestDTO){
		BusinessClassResponseDTO updateBusinessClass = businessClassService.updataBusinessClass(id, requestDTO);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Business Class ID: " +id+ " saved successfully!", updateBusinessClass);
		
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteBusinessClass(@PathVariable Integer id){
		businessClassService.deleteBusinessClass(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Business Class ID: " +id+ " saved successfully!", null);
		
		return ResponseEntity.ok(response);
	}
	
}
