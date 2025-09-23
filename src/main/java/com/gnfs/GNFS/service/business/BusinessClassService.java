package com.gnfs.GNFS.service.business;

import java.util.List;

import com.gnfs.GNFS.dto.business.BusinessClassRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessClassResponseDTO;



public interface BusinessClassService {

	public List<BusinessClassResponseDTO> listBusinessClass();
	public BusinessClassResponseDTO createBusinessClass(BusinessClassRequestDTO businessClassRequestDTO);
	public BusinessClassResponseDTO getBusinessClass(Integer id);
	public BusinessClassResponseDTO updataBusinessClass(Integer id, BusinessClassRequestDTO businessClassRequestDTO);
	public void deleteBusinessClass(Integer id);
}
