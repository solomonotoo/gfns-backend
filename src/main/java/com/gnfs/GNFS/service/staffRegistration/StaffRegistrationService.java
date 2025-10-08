package com.gnfs.GNFS.service.staffRegistration;

import java.util.List;

import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;



public interface StaffRegistrationService {
	public List<StaffRegistrationResponseDTO> listStaffRegistration();
	public StaffRegistrationResponseDTO getStaffRegistrationId(Long id);
	public StaffRegistrationResponseDTO createStaffRegistration(StaffRegistrationCreateRequestDTO dto);
	public StaffRegistrationResponseDTO updateStaffRegistration(Long id, StaffRegistrationUpdateRequestDTO dto);
	public void deleteStaffRegistration(Long id);
}
