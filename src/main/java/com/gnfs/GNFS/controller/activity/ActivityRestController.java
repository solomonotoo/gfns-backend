package com.gnfs.GNFS.controller.activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.activity.ActivityRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityResponseDTO;
import com.gnfs.GNFS.service.activity.ActivityService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityRestController {
	
	private final ActivityService activityService;
	
	@GetMapping
	public ResponseEntity<List<ActivityResponseDTO>> listAcitivities() {
		List<ActivityResponseDTO> activityResponseDTO = activityService.listActivity();
		
		return ResponseEntity.ok(activityResponseDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActivityResponseDTO> getAcitvity (@PathVariable Integer id) {
		ActivityResponseDTO activityResponseDTO = activityService.getActivity(id);
		return ResponseEntity.ok(activityResponseDTO);
	}
	

	@PostMapping
	public ResponseEntity<ActivityResponseDTO> createActivity(@RequestBody ActivityRequestDTO activity) {
		
		ActivityResponseDTO saveActivity = activityService.createActivity(activity);
		
		return ResponseEntity.ok(saveActivity);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateActivity(@PathVariable Integer id, @RequestBody ActivityRequestDTO activity) {
		
		ActivityResponseDTO updatedActivity = activityService.updataActivity(id, activity);
		
		return ResponseEntity.ok(updatedActivity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteActivity(@PathVariable Integer id){
		activityService.deleteAcitity(id);
		
		return ResponseEntity.ok().build();
	}
	
	
}
