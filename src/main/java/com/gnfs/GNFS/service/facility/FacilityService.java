package com.gnfs.GNFS.service.facility;

import java.util.List;

import com.gnfs.GNFS.dto.facility.FacilityRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityResponseDTO;


public interface FacilityService {

	
	public List<FacilityResponseDTO> listFacilities();
	public FacilityResponseDTO createFacility(FacilityRequestDTO facilityRequestDTO);
	public FacilityResponseDTO getFacility(Integer id);
	public FacilityResponseDTO updataFacility(Integer id, FacilityRequestDTO facilityRequestDTO);
	public void deleteFacility(Integer id);
}
