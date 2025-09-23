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
import com.gnfs.GNFS.dto.equipment.EquipFireSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipFireSystemResponseDTO;
import com.gnfs.GNFS.service.Equipment.EquipAlarmSystemService;
import com.gnfs.GNFS.service.Equipment.EquipFireFightingSystemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/firesystems")
@RequiredArgsConstructor
public class EquipFireFightingSystemRestController {
	
	private final EquipFireFightingSystemService fireSystemService;
	
	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listAlarmSystems(){
		List<?> alarmSystems = fireSystemService.listFireSystem();
		
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Alarm systems fetched successfully!", alarmSystems);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<EquipFireSystemResponseDTO>> getAlarmSystemId(
			@PathVariable Integer id){
		
		EquipFireSystemResponseDTO alarmSystemResponseDTO = fireSystemService.getFireSystemId(id);
		CustomSuccessMessageResponse<EquipFireSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems exist in the databae!", alarmSystemResponseDTO);
		return ResponseEntity.ok(response);
		
	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<EquipFireSystemResponseDTO>> createAlarmSystem(
			@RequestBody EquipFireSystemRequestDTO requestDTO){
		
		
		EquipFireSystemResponseDTO alarmSystemResponseDTO = fireSystemService.createFireSystem(requestDTO);
		CustomSuccessMessageResponse<EquipFireSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems exist in the databae!", alarmSystemResponseDTO);
		return ResponseEntity.created(URI.create("/api/v1/alarmsystems" + alarmSystemResponseDTO.getId())).body(response);
		
	}
	
	
	@PutMapping("/{id}")
	public  ResponseEntity<CustomSuccessMessageResponse<EquipFireSystemResponseDTO>> updateAlarmSystem(@PathVariable Integer id,
			@RequestBody EquipFireSystemRequestDTO requestDTO){
		EquipFireSystemResponseDTO fireSystemResponseDTO = fireSystemService.updateFireSystem(id, requestDTO);
		CustomSuccessMessageResponse<EquipFireSystemResponseDTO> response = new CustomSuccessMessageResponse<>("Alarm systems ID: " +id+ " updated successfully!", fireSystemResponseDTO);
		return ResponseEntity.ok(response);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteAlarmSysetem(@PathVariable Integer id){
		fireSystemService.deleteFireSystem(id);
		
		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>("Alarm systems ID: " +id+ " deleted successfully!", null);
		return ResponseEntity.ok(response);
	}
}
