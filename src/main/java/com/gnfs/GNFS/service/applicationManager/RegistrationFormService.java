package com.gnfs.GNFS.service.applicationManager;

import java.util.List;

import com.gnfs.GNFS.dto.appManager.RegistrationFormRequestDTO;
import com.gnfs.GNFS.dto.appManager.RegistrationFormResponseDTO;



public interface RegistrationFormService {

	public List<RegistrationFormResponseDTO> listRegistrationForm();
	public RegistrationFormResponseDTO createRegistrationForm(RegistrationFormRequestDTO RegistrationFormRequestDTO);
	public RegistrationFormResponseDTO getRegistrationForm(Long id);
	public RegistrationFormResponseDTO updataRegistrationForm(Long id, RegistrationFormRequestDTO RegistrationFormRequestDTO);
	public void deleteRegistrationForm(Long id);
	
}
