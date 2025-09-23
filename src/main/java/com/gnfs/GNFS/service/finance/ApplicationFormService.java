package com.gnfs.GNFS.service.finance;

import java.util.List;

import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;


public interface ApplicationFormService {


	public List<ApplicationFormResponseDTO> listApplicationForm();
	public ApplicationFormResponseDTO getApplicationFormId(Integer id);
	public ApplicationFormResponseDTO createApplicationForm(ApplicationFormRequestDTO ApplicationFormType);
	public ApplicationFormResponseDTO updateApplicationForm(Integer id, ApplicationFormRequestDTO ApplicationFormType);
	public void deleteApplicationForm(Integer id);
}
