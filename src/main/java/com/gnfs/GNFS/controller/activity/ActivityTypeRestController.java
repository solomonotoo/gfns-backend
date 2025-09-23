package com.gnfs.GNFS.controller.activity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.activity.ActivityRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityTypeRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityTypeResponseDTO;
import com.gnfs.GNFS.entity.Activity;
import com.gnfs.GNFS.service.activity.ActivityTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/activitytypes")
@RequiredArgsConstructor
public class ActivityTypeRestController {
	
	private final ActivityTypeService activityTypeService;

	@GetMapping
	public ResponseEntity<List<ActivityTypeResponseDTO>> listActitvityType() {
		return ResponseEntity.ok(activityTypeService.listActivity());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActivityTypeResponseDTO> getActivityType(@PathVariable Integer id){
		return ResponseEntity.ok(activityTypeService.getActivity(id));
	}

	@PostMapping
	public ResponseEntity<ActivityTypeResponseDTO> createActivityType(
			@RequestBody ActivityTypeRequestDTO activityTypeRequestDTO) {
		return new ResponseEntity<ActivityTypeResponseDTO>(activityTypeService.createActivity(activityTypeRequestDTO),
				HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ActivityTypeResponseDTO> updateActivity(@PathVariable Integer id, @RequestBody ActivityTypeRequestDTO activityTypeRequestDTO){
		return ResponseEntity.ok(activityTypeService.updataActivity(id, activityTypeRequestDTO));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteActivityType(@PathVariable Integer id){
		activityTypeService.deleteAcitity(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	


}
