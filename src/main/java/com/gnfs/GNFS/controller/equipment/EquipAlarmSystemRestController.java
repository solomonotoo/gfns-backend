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
import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemResponseDTO;
import com.gnfs.GNFS.service.Equipment.EquipAlarmSystemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/alarmsystems")
@RequiredArgsConstructor
public class EquipAlarmSystemRestController {
	
	private final EquipAlarmSystemService alarmSystemService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listAlarmSystems(){
		List<EquipAlarmSystemResponseDTO> alarmSystems = alarmSystemService.listAlarmSystem();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Alarm systems fetched successfully!", alarmSystems);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO>> getAlarmSystemId(
			@PathVariable Integer id){
		
		EquipAlarmSystemResponseDTO alarmSystemResponseDTO = alarmSystemService.getAlarmSystemId(id);
		CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems exist in the databae!", alarmSystemResponseDTO);
		return ResponseEntity.ok(response);
		
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO>> createAlarmSystem(
			@RequestBody EquipAlarmSystemRequestDTO requestDTO){
		
		
		EquipAlarmSystemResponseDTO alarmSystemResponseDTO = alarmSystemService.createAlarmSystem(requestDTO);
		CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems exist in the databae!", alarmSystemResponseDTO);
		return ResponseEntity.created(URI.create("/api/v1/alarmsystems" + alarmSystemResponseDTO.getId())).body(response);
		
	}
	
	
	@PutMapping("/{id}")
	public  ResponseEntity<CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO>> updateAlarmSystem(@PathVariable Integer id,
			@RequestBody EquipAlarmSystemRequestDTO requestDTO){
		EquipAlarmSystemResponseDTO alarmSystemResponseDTO = alarmSystemService.updateAlarmSystem(id, requestDTO);
		CustomSuccessMessageResponse<EquipAlarmSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems ID: " +id+ " updated successfully!", alarmSystemResponseDTO);
		return ResponseEntity.ok(response);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteAlarmSysetem(@PathVariable Integer id){
		alarmSystemService.deleteAlarmSystem(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Alarm systems ID: " +id+ " deleted successfully!", null);
		return ResponseEntity.ok(response);
	}
}
