package com.gnfs.GNFS.service.incident;

import java.util.List;

import com.gnfs.GNFS.dto.incident.IncidentTypeRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentTypeResponseDTO;



public interface IncidentTypeService {
	public List<IncidentTypeResponseDTO> listIncidentType();
	public IncidentTypeResponseDTO getIncidentTypeId(Integer id);
	public IncidentTypeResponseDTO createIncidentType(IncidentTypeRequestDTO dto);
	public IncidentTypeResponseDTO updateIncidentType(Integer id, IncidentTypeRequestDTO dto);
	public void deleteIncidentType(Integer id);
	
}
