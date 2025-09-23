package com.gnfs.GNFS.service.incident;

import java.util.List;

import com.gnfs.GNFS.dto.incident.IncidentAddRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAddResponseDTO;

public interface IncidentAddService {
	public List<IncidentAddResponseDTO> listIncidentAdd();
	public IncidentAddResponseDTO getIncidentAddId(Long id);
	public IncidentAddResponseDTO createIncidentAdd(IncidentAddRequestDTO dto);
	public IncidentAddResponseDTO updateIncidentAdd(Long id, IncidentAddRequestDTO dto);
	public void deleteIncidentAdd(Long id);
	
}
