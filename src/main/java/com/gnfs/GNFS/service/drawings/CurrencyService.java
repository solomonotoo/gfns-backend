package com.gnfs.GNFS.service.drawings;

import java.util.List;

import com.gnfs.GNFS.dto.drawing.CurrencyRequestDTO;
import com.gnfs.GNFS.dto.drawing.CurrencyResponseDTO;



public interface CurrencyService {

	public List<CurrencyResponseDTO> listCurrency();
	public CurrencyResponseDTO createCurrency(CurrencyRequestDTO drawingRequestDTO);
	public CurrencyResponseDTO getCurrency(Integer id);
	public CurrencyResponseDTO updataCurrency(Integer id, CurrencyRequestDTO drawingRequestDTO);
	public void deleteCurrency(Integer id);
}
