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
import com.gnfs.GNFS.dto.finance.SellSoldFormsRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsResponseDTO;
import com.gnfs.GNFS.service.finance.SellSoldFormsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sell-forms")
@RequiredArgsConstructor
public class SellSoldFormsRestController {

	private final SellSoldFormsService sellSoldFormsService;
	
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listApplications(){
		List<?> applications = sellSoldFormsService.listSellSoldForms();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Sold Forms List fetched successfully!", applications);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getApplicationId(@PathVariable Long id){
		SellSoldFormsResponseDTO responseDTO = sellSoldFormsService.getSellSoldForms(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Sold Form with ID: "+id+" exist in the database", responseDTO);
		return ResponseEntity.ok(response);
	}

	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createApplication(@RequestBody SellSoldFormsRequestDTO requestDTO){
		SellSoldFormsResponseDTO responseDTO = sellSoldFormsService.createSellSoldForms(requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Sell Form created successfully!", responseDTO);
		return ResponseEntity.created(URI.create("/api/v1/applications" + responseDTO.getId())).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateApplication(@PathVariable Long id, @RequestBody SellSoldFormsRequestDTO requestDTO){
		SellSoldFormsResponseDTO responseDTO = sellSoldFormsService.updataSellSoldForms(id, requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Sold Form with ID: "+id+" updated successfully!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteApplication(@PathVariable Long id){
		sellSoldFormsService.deleteSellSoldForms(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Sold Form with ID: "+id+" deleted successfully!", null);
		return ResponseEntity.ok(response);
	}
	
}
