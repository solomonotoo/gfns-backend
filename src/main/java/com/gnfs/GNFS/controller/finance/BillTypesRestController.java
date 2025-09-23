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
import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.BillTypeResponseDTO;
import com.gnfs.GNFS.service.finance.BillTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/billtypes")
public class BillTypesRestController {
	
	private final BillTypeService billTypeService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> getBillTypes(){
		List<?> listBillTypes = billTypeService.listBillType();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Bill Types Fetched Successfully!", listBillTypes);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getBillTypeById(@PathVariable Integer id) {
		BillTypeResponseDTO responseDTO = billTypeService.getBillTypeId(id);
CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill Types exist in database !", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createBillType(@RequestBody BillTypeRequestDTO billTypeRequestDTO){
		BillTypeResponseDTO responseDTO = billTypeService.createBillType(billTypeRequestDTO);
		
CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill Types created successfully !", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/billtypes" + responseDTO.getId())).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateBillType(@PathVariable Integer id, @RequestBody BillTypeRequestDTO billTypeRequestDTO){
		BillTypeResponseDTO responseDTO = billTypeService.updateBillType(id, billTypeRequestDTO);
		
CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill Type updated successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteBillType(@PathVariable Integer id){
		billTypeService.deleteBillType(id);
		
CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Bill Type with ID: " +id+ " updated successfully !", null);
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
}
