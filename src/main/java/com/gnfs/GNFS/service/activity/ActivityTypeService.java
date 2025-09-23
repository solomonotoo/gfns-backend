package com.gnfs.GNFS.service.activity;

import java.util.List;

import com.gnfs.GNFS.dto.activity.ActivityTypeRequestDTO;
import com.gnfs.GNFS.dto.activity.ActivityTypeResponseDTO;



public interface ActivityTypeService {

	public List<ActivityTypeResponseDTO> listActivity();
	public ActivityTypeResponseDTO createActivity(ActivityTypeRequestDTO activityRequestDTO);
	public ActivityTypeResponseDTO getActivity(Integer id);
	public ActivityTypeResponseDTO updataActivity(Integer id, ActivityTypeRequestDTO activityRequestDTO);
	public void deleteAcitity(Integer id);
}
