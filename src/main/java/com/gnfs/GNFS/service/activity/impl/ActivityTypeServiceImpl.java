package com.gnfs.GNFS.service.activity.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.gnfs.GNFS.dto.activity.ActivityTypeRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityTypeResponseDTO;
import com.gnfs.GNFS.entity.ActivityType;
import com.gnfs.GNFS.exceptions.DistrictNotFoundException;
import com.gnfs.GNFS.repository.ActivityTypeRepository;
import com.gnfs.GNFS.service.activity.ActivityTypeService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ActivityTypeServiceImpl implements ActivityTypeService {
	
	private final ActivityTypeRepository activityTypeRepo;
	private final ModelMapper modelMapper;

	@Override
	public List<ActivityTypeResponseDTO> listActivity() {
		List<ActivityType> activities = activityTypeRepo.findAll();
		
		return activities.stream().map(activity -> modelMapper.map(activity, ActivityTypeResponseDTO.class)
				).collect(Collectors.toList());
	}

	@Override
	public ActivityTypeResponseDTO createActivity(ActivityTypeRequestDTO activityRequestDTO) {
		ActivityType activityType = activityRequestDtoToActivityTypeEntity(activityRequestDTO);
		
		ActivityType savedActivityType = activityTypeRepo.save(activityType);
		
		return modelMapper.map(savedActivityType, ActivityTypeResponseDTO.class);
	}

	@Override
	public ActivityTypeResponseDTO getActivity(Integer id) {
		 ActivityType  activityId = activityTypeRepo.findById(id)
				 .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + id));
		return modelMapper.map(activityId, ActivityTypeResponseDTO.class);
	}

	@Override
	public ActivityTypeResponseDTO updataActivity(Integer id, ActivityTypeRequestDTO activityRequestDTO) {
		  // 1. Find the existing entity
		ActivityType  existingActivityType = activityTypeRepo.findById(id)
				 .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + id));
		
		 // 2. Map the DTO to the existing entity (preserving the ID)
		modelMapper.map(activityRequestDTO, existingActivityType);
		
		 // 3. Save the updated entity
		ActivityType updateActivityType = activityTypeRepo.save(existingActivityType);
		
		return modelMapper.map(updateActivityType, ActivityTypeResponseDTO.class);
	}

	@Override
	public void deleteAcitity(Integer id) {
Long countById = activityTypeRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new DistrictNotFoundException("Could not find District ID: " + id);
		}
		
		activityTypeRepo.deleteById(id);
	}
	
	
	
	
	
	
	
	
	//private method that convert Entity to DTO
	private ActivityTypeRequestDTO activityTypeToActivityTypeRequestDTO(ActivityType activity) {
		ActivityTypeRequestDTO activityRequestDTO = modelMapper.map(activity, ActivityTypeRequestDTO.class);
		
		return activityRequestDTO;
		
	}
	
	private ActivityType activityRequestDtoToActivityTypeEntity(ActivityTypeRequestDTO activityRequestDTO) {
		ActivityType activity = modelMapper.map(activityRequestDTO, ActivityType.class);
		
		return activity;
		
	}

}
