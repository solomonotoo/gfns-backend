package com.gnfs.GNFS.controller.generateBillAndPayment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillRequestDTO;
import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillResponseDTO;
import com.gnfs.GNFS.service.generateBillAndPayment.PayBillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/paybills")
@RequiredArgsConstructor
public class PayBillRestController {
	
	private final PayBillService payBillService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listPayBills(){
		List<?> listPayBills = payBillService.listPayBill();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Bill Payment List fetched successfully! ", listPayBills);
		
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> createPayBill(@RequestBody PayBillRequestDTO billRequestDTO){
		PayBillResponseDTO responseDTO = payBillService.createPayBill(billRequestDTO);
		
CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Bill Payment made successfully! ", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
}
