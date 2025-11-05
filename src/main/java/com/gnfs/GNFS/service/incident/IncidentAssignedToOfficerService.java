package com.gnfs.GNFS.service.incident;


import java.util.List;

import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerRequestDTO;
import com.gnfs.GNFS.dto.incident.IncidentAssignedToOfficerResponseDTO;

public interface IncidentAssignedToOfficerService {
	public List<IncidentAssignedToOfficerResponseDTO> getIncidentList();
	public IncidentAssignedToOfficerResponseDTO getIncidentAssignedToOfficerId(Long id);
	public IncidentAssignedToOfficerResponseDTO createIncidentAssignedToOfficer(IncidentAssignedToOfficerRequestDTO dto);
	public IncidentAssignedToOfficerResponseDTO updateIncidentAssignedToOfficer(Long id, IncidentAssignedToOfficerRequestDTO dto);
}
