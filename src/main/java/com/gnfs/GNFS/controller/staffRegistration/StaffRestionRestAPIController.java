package com.gnfs.GNFS.controller.staffRegistration;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
import com.gnfs.GNFS.service.staffRegistration.StaffRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/staff-registration")
@RequiredArgsConstructor
public class StaffRestionRestAPIController {

	private final StaffRegistrationService staffRegistrationService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listStaffRegistration(){
		List<?> listStaff = staffRegistrationService.listStaffRegistration();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Registered Staff List Fetched Successfully!", listStaff);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getRegisteredStaffId(@PathVariable Long id){
		StaffRegistrationResponseDTO staff = staffRegistrationService.getStaffRegistrationId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Staff ID: " + id + " exist in the database",  staff);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createRegisteredStaff(@Valid @RequestBody StaffRegistrationCreateRequestDTO dto){
		StaffRegistrationResponseDTO createStaff = staffRegistrationService.createStaffRegistration(dto);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Staff Created Successfully!", createStaff);
		
		return ResponseEntity.created(URI.create("/api/v1/staff-registration/" + createStaff.id())).body(response);
	
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateRegisteredStaff(@PathVariable Long id,@Valid @RequestBody StaffRegistrationUpdateRequestDTO dto) {
		StaffRegistrationResponseDTO responseDTO = staffRegistrationService.updateStaffRegistration(id, dto);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Staff Updated Successfully!", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("/{id}")
	ResponseEntity<CustomSuccessMessageResponse<?>> deleteRegisteredStaff(@PathVariable Long id){
		staffRegistrationService.deleteStaffRegistration(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Staff ID: " + id + " Deleted Successfully!", null);
		return ResponseEntity.ok(response);
	}
	
}
