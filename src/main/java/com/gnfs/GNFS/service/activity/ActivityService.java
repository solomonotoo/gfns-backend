package com.gnfs.GNFS.service.activity;

import java.util.List;

import com.gnfs.GNFS.dto.activity.ActivityRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityResponseDTO;

public interface ActivityService {

	
	public List<ActivityResponseDTO> listActivity();
	public ActivityResponseDTO createActivity(ActivityRequestDTO activityRequestDTO);
	public ActivityResponseDTO getActivity(Integer id);
	public ActivityResponseDTO updataActivity(Integer id, ActivityRequestDTO activityRequestDTO);
	public void deleteAcitity(Integer id);
	
}
