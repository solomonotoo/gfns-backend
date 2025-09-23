package com.gnfs.GNFS.service.activity.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.activity.ActivityRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityResponseDTO;
import com.gnfs.GNFS.dto.activity.ActivityTypeDTO;
import com.gnfs.GNFS.entity.Activity;
import com.gnfs.GNFS.entity.ActivityType;
import com.gnfs.GNFS.exceptions.ActivityNotFoundException;
import com.gnfs.GNFS.exceptions.DistrictNotFoundException;
import com.gnfs.GNFS.repository.ActivityRespository;
import com.gnfs.GNFS.service.activity.ActivityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

	private final ActivityRespository activityRepo;
	private final ModelMapper modelMapper;
	

//	@Override
//	public List<ActivityResponseDTO> listActivity() {
//	    List<Activity> activities = activityRepo.findAll();
//
//	    return activities.stream().map(activity -> {
//	        // Manually map activity type
//	        ActivityType type = activity.getActivityType();
//	        ActivityTypeDTO typeDTO = null;
//
//	        if (type != null) {
//	            typeDTO = new ActivityTypeDTO(
//	                type.getId(),
//	                type.getName(),
//	                type.getDescription()
//	            );
//	        }
//
//	        // Map activity fields
//	        ActivityResponseDTO dto = new ActivityResponseDTO();
//	        dto.setId(activity.getId());
//	        dto.setName(activity.getName());
//	        dto.setDescription(activity.getDescription());
//	        dto.setActivityType(typeDTO);
//
//	        return dto;
//	    }).collect(Collectors.toList());
//	}

	
	@Override
	public List<ActivityResponseDTO> listActivity() {
		 return activityRepo.findAll().stream()
			        .map(activity -> modelMapper.map(activity, ActivityResponseDTO.class))
			        .collect(Collectors.toList());
	}
	
	@Override
	public ActivityResponseDTO createActivity(ActivityRequestDTO activityRequestDTO) {
		Activity activity = activityRequestDtoToActivityEntity(activityRequestDTO);
		
		Activity savedActivity = activityRepo.save(activity);
		
		
		return modelMapper.map(savedActivity, ActivityResponseDTO.class);
	}

	@Override
	public ActivityResponseDTO getActivity(Integer id) {
		Optional<Activity> activityOptional = activityRepo.findById(id);
		
		if(activityOptional.isEmpty()) {
			throw  new RuntimeException("ActivityType not found with id: " + id);
		}
		Activity activity = activityOptional.get();
		
		return modelMapper.map(activity, ActivityResponseDTO.class);
	}

//	@Override
//	public ActivityResponseDTO updataActivity(Integer id, ActivityRequestDTO activityRequestDTO) {
//	Activity existing = activityRepo.findById(id)
//    .orElseThrow(() -> new ActivityNotFoundException("Activity not found"));
//
//	// Update only fields from the DTO
//	existing.setName(activityRequestDTO.getName());
//	existing.setDescription(activityRequestDTO.getDescription());
//
//		if (activityRequestDTO.getActivityType() != null) {
//		    ActivityType type = new ActivityType();
//		    type.setId(activityRequestDTO.getActivityType().getId());
//		    existing.setActivityType(type);
//		}
//	
//		Activity updateActivity = activityRepo.save(existing);
//		
//		return modelMapper.map(updateActivity, ActivityResponseDTO.class);
//	}
	
	@Override
	public ActivityResponseDTO updataActivity(Integer id, ActivityRequestDTO activityRequestDTO) {
		Activity activity = activityRepo.findById(id)
				 .orElseThrow(() -> new ActivityNotFoundException("Activity not found"));
		
		//activity.setName(activityRequestDTO.getName());
		Activity activityDTO = activityRequestDtoToActivityEntity(activityRequestDTO);
		
		//Activity mapped = modelMapper.map(activityRequestDTO, Activity.class);
		activityDTO.setId(id); // Retain original ID
		
		Activity updateActivity = activityRepo.save(activity);
		
		return modelMapper.map(updateActivity, ActivityResponseDTO.class);
	}

	@Override
	public void deleteAcitity(Integer id) {
		Long countById = activityRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new DistrictNotFoundException("Could not find District ID: " + id);
		}
		
		activityRepo.deleteById(id);
	}
	
	
	//private method that convert Entity to DTO
	private ActivityRequestDTO activityToActivityRequestDTO(Activity activity) {
		ActivityRequestDTO activityRequestDTO = modelMapper.map(activity, ActivityRequestDTO.class);
		
		return activityRequestDTO;
		
	}
	
	private Activity activityRequestDtoToActivityEntity(ActivityRequestDTO activityRequestDTO) {
		Activity activity = modelMapper.map(activityRequestDTO, Activity.class);
		
		return activity;
		
	}

}
