package com.gnfs.GNFS.service.finance;

import java.util.List;

import com.gnfs.GNFS.dto.finance.SellSoldFormsRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsResponseDTO;



public interface SellSoldFormsService {

	public List<SellSoldFormsResponseDTO> listSellSoldForms();
	public SellSoldFormsResponseDTO createSellSoldForms(SellSoldFormsRequestDTO sellSoldFormsRequestDTO);
	public SellSoldFormsResponseDTO getSellSoldForms(Long id);
	public SellSoldFormsResponseDTO updataSellSoldForms(Long id, SellSoldFormsRequestDTO sellSoldFormsRequestDTO);
	public void deleteSellSoldForms(Long id);
}
