package com.gnfs.GNFS.service.facility;

import java.util.List;

import com.gnfs.GNFS.dto.facility.FacilityTypeRequestDTO;
import com.gnfs.GNFS.dto.facility.FacilityTypeResponseDTO;


public interface FacilityTypeService {

	public List<FacilityTypeResponseDTO> listFacilityTypes();
	public FacilityTypeResponseDTO createFacilityType(FacilityTypeRequestDTO facilityRequestDTO);
	public FacilityTypeResponseDTO getFacilityType(Integer id);
	public FacilityTypeResponseDTO updataFacilityType(Integer id, FacilityTypeRequestDTO facilityRequestDTO);
	public void deleteFacilityType(Integer id);
}
