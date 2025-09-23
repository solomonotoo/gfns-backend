package com.gnfs.GNFS.controller.equipment;

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
import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansResponseDTO;
import com.gnfs.GNFS.service.Equipment.EquipEscapeMeansService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/escapemeans")
public class EquipEscapeMeansController {
	
	private final EquipEscapeMeansService escapeMeansService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listEscapeMeans(){
		List<?> listEscape = escapeMeansService.listEscapeMeans();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<>("Escape Means List fetched successfully!", listEscape);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getEscapeMeansId(@PathVariable Integer id){
		EquipEscapeMeansResponseDTO responseDTO = escapeMeansService.getEscapeMeansId(id);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Escape Means ID: "+id+ "exist in the database", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createEscapeMeans(@RequestBody EquipEscapeMeansRequestDTO requestDTO){
		
		EquipEscapeMeansResponseDTO saveEscapeMeans = escapeMeansService.createEscapeMeans(requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Escape Means successfully created", saveEscapeMeans);
		
		return ResponseEntity.created(URI.create("/api/v1/escapemeans" + saveEscapeMeans.getId())).body(response);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateEscapeMeans(@PathVariable Integer id, @RequestBody EquipEscapeMeansRequestDTO requestDTO){
		EquipEscapeMeansResponseDTO responseDTO = escapeMeansService.updateEscapeMeans(id, requestDTO);
		
		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Escape Means ID: "+id+ " successfully updated", responseDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteEscapeMeans(@PathVariable Integer id){
		escapeMeansService.deleteEscapeMeans(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Escape Means ID: "+id+ " successfully deleted", null);
		
		return ResponseEntity.ok(response);
	}
	
}
