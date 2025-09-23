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
import com.gnfs.GNFS.dto.finance.BillRequestDTO;
import com.gnfs.GNFS.dto.finance.BillResponseDTO;
import com.gnfs.GNFS.service.finance.BillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bills")
public class BillRestController {
	
	private final BillService billService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listBills(){
		List<?> listBills = billService.listBill();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<>("Bills list fetched successfully!", listBills);
		return ResponseEntity.ok(response);
				
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getBillById(@PathVariable Integer id){
		BillResponseDTO responseDTO = billService.getBillId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill ID: " +id+ " exist in the database!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createBill(@RequestBody BillRequestDTO requestDTO){
		
		BillResponseDTO responseDTO = billService.createBill(requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill created successfully!", responseDTO);
		return ResponseEntity.created(URI.create("/api/v1/bills" + requestDTO.getId())).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateBill(@PathVariable Integer id, @RequestBody BillRequestDTO billRequestDTO){
		BillResponseDTO responseDTO = billService.updateBill(id, billRequestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill ID: " +id+ " is successfully updated!", responseDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteBill(@PathVariable Integer id){
		billService.deleteBill(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Bill ID: " +id+ " is successfully deleted!", null);
		return ResponseEntity.ok(response);
	}

}
