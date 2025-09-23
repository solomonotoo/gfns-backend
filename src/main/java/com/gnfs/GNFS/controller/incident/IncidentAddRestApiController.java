package com.gnfs.GNFS.controller.incident;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gnfs.GNFS.FileUploadUtil;
import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.incident.IncidentAddRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAddResponseDTO;
import com.gnfs.GNFS.service.incident.IncidentAddService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/incidents")
@Validated
public class IncidentAddRestApiController {

	private final IncidentAddService incidentAddService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listIncidents(){
		List<IncidentAddResponseDTO> incidents = incidentAddService.listIncidentAdd();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Incident List Fetched Successfully!", incidents);
		
		return ResponseEntity.ok(response);
	}
	
	
//	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<CustomSuccessMessageResponse<?>> createIncident(
//			@Valid @RequestPart("dto") IncidentAddRequestDTO dto, 
//			@RequestPart("photo") MultipartFile multipartFile ) throws IOException {
//		
//		
//		String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
//		FileUploadUtil.cleanDir(fileName);
//		FileUploadUtil.saveFile("uploads/", fileName, multipartFile);
//		dto.setPhoto(fileName);
//		
//		IncidentAddResponseDTO incident = incidentAddService.createIncidentAdd(dto);
//		
//		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Created Successfully!", incident);
//		
//		
//		return ResponseEntity.created(URI.create("/api/v1/incidents/" + incident.getId())).body(response);
//	}
//	
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomSuccessMessageResponse<?>> createIncident(
			@Valid @RequestPart("dto") IncidentAddRequestDTO dto, 
			@RequestPart("photo") MultipartFile multipartFile ) throws IOException {
		
		if (multipartFile.isEmpty()) {
	        throw new IllegalArgumentException("Incident picture is required");
	    }
		
			//gets the file name
			String fileName = StringUtils.cleanPath(UUID.randomUUID() + "_" + multipartFile.getOriginalFilename());
			dto.setPhoto(fileName);
			
			IncidentAddResponseDTO incident = incidentAddService.createIncidentAdd(dto);
			
			// directory name with saved user id
			String uploadDir = "Incident-photos/" + incident.getId(); 
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile("uploads/", fileName, multipartFile);
			
	
		
//		String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
//		FileUploadUtil.cleanDir(fileName);
//		FileUploadUtil.saveFile("uploads/", fileName, multipartFile);
//		dto.setPhoto(fileName);
//		
//		IncidentAddResponseDTO incident = incidentAddService.createIncidentAdd(dto);
	
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Incident Created Successfully!", incident);
		
		
		return ResponseEntity.created(URI.create("/api/v1/incidents/" + incident.getId())).body(response);
	}
	
	
	@PutMapping(value ="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateIncident(
			@PathVariable Long id, @Valid @RequestPart("dto") IncidentAddRequestDTO dto,
			@RequestPart(value = "photo", required = false) MultipartFile multipartFile) throws IOException {
		
		//replace photo if provided
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
			FileUploadUtil.saveFile("uploads/", fileName, multipartFile);
			dto.setPhoto(fileName);
		}
		
		IncidentAddResponseDTO incident = incidentAddService.updateIncidentAdd(id, dto);
		
		return ResponseEntity.ok(
				new CustomSuccessMessageResponse<>("Incident updated successfully!", incident)
	        );
	}
	
}
