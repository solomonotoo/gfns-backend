package com.gnfs.GNFS.service.incident;

import java.util.List;

import com.gnfs.GNFS.dto.incident.IncidentClassificationRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentClassificationResponseDTO;



public interface IncidentClassificationService {

	public List<IncidentClassificationResponseDTO> listIncidentClassification();
	public IncidentClassificationResponseDTO getIncidentClassificationId(Integer id);
	public IncidentClassificationResponseDTO createIncidentClassification(IncidentClassificationRequestDTO dto);
	public IncidentClassificationResponseDTO updateIncidentClassification(Integer id, IncidentClassificationRequestDTO dto);
	public void deleteIncidentClassification(Integer id);
	
}
