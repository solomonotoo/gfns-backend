package com.gnfs.GNFS.controller.appManager;

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
import com.gnfs.GNFS.dto.appManager.RegistrationFormRequestDTO;
import com.gnfs.GNFS.dto.appManager.RegistrationFormResponseDTO;
import com.gnfs.GNFS.service.applicationManager.RegistrationFormService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registrations")
public class RegistrationFormRestController {
	
	private final RegistrationFormService registrationFormService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listRegistration(){
		List<?> listRegistration = registrationFormService.listRegistrationForm();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Registration form list fetched successfully!", listRegistration);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> editRegistrationForm(@PathVariable Long id){
		RegistrationFormResponseDTO responseDTO = registrationFormService.getRegistrationForm(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Item exist in the database",responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createRegistration(@RequestBody @Valid RegistrationFormRequestDTO dto){
		RegistrationFormResponseDTO responseDTO = registrationFormService.createRegistrationForm(dto);
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Registration  saved successfully", responseDTO);
		
		return ResponseEntity.created(URI.create("/api/v1/registration" + responseDTO.getId())).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateRegistrationForm(
			@PathVariable Long id,
			@RequestBody @Valid RegistrationFormRequestDTO dto){
		RegistrationFormResponseDTO responseDTO = registrationFormService.updataRegistrationForm(id, dto);
		
CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Registration Form updated successfully",responseDTO);
		
		return ResponseEntity.ok(response);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> deleteRegistrationForm(@PathVariable Long id){
		registrationFormService.deleteRegistrationForm(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Item exist in the database",null);
		
		return ResponseEntity.ok(response);
	}
	
}
