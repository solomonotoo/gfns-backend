package com.gnfs.GNFS.service.business;

import java.util.List;

import com.gnfs.GNFS.dto.business.BusinessTypeRequestDTO;
import com.gnfs.GNFS.dto.business.BusinessTypeResponseDTO;



public interface BusinessTypeService {

	public List<BusinessTypeResponseDTO> listbusinessType();
	public BusinessTypeResponseDTO createbusinessType(BusinessTypeRequestDTO businessTypeRequestDTO);
	public BusinessTypeResponseDTO getbusinessType(Integer id);
	public BusinessTypeResponseDTO updatabusinessType(Integer id, BusinessTypeRequestDTO businessTypeRequestDTO);
	public void deletebusinessType(Integer id);
}
