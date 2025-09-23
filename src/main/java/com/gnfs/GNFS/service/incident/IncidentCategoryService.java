package com.gnfs.GNFS.service.incident;

import java.util.List;

import com.gnfs.GNFS.dto.incident.IncidentCategoryRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentCategoryResponseDTO;



public interface IncidentCategoryService {

	public List<IncidentCategoryResponseDTO> listIncidentCategory();
	public IncidentCategoryResponseDTO getIncidentCategoryId(Integer id);
	public IncidentCategoryResponseDTO createIncidentCategory(IncidentCategoryRequestDTO dto);
	public IncidentCategoryResponseDTO updateIncidentCategory(Integer id, IncidentCategoryRequestDTO dto);
	public void deleteIncidentCategory(Integer id);
	
}
