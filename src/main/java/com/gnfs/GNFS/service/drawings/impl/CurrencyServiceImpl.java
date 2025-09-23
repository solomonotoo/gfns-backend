package com.gnfs.GNFS.service.drawings.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.drawing.CurrencyRequestDTO;
import com.gnfs.GNFS.dto.drawing.CurrencyResponseDTO;
import com.gnfs.GNFS.entity.Currency;
import com.gnfs.GNFS.exceptions.CurrencyNotFoundException;
import com.gnfs.GNFS.repository.CurrencyRepository;
import com.gnfs.GNFS.service.drawings.CurrencyService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
	
	private final CurrencyRepository currencyRepo;
	private final ModelMapper mapper;

	@Override
	public List<CurrencyResponseDTO> listCurrency() {
		List<Currency> listCurrency = currencyRepo.findAll();
		return listCurrency.stream().map(currency -> convertFromEntityToDTO(currency))
				.collect(Collectors.toList());
	}

	@Override
	public CurrencyResponseDTO createCurrency(CurrencyRequestDTO drawingRequestDTO) {
		Currency currency = convertFromDTOToEntity(drawingRequestDTO);
		Currency savedCurrency = currencyRepo.save(currency);
		return convertFromEntityToDTO(savedCurrency);
	}

	@Override
	public CurrencyResponseDTO getCurrency(Integer id) {
		Currency currency = getCurrencyId(id);
		return convertFromEntityToDTO(currency);
	}

	@Override
	public CurrencyResponseDTO updataCurrency(Integer id, CurrencyRequestDTO currencyRequestDTO) {
		Currency existingCurrency = getCurrencyId(id);
		
		existingCurrency.setName(currencyRequestDTO.getName());
		existingCurrency.setDescription(currencyRequestDTO.getDescription());
		
		Currency updatedCurrency = currencyRepo.save(existingCurrency);
		
		return convertFromEntityToDTO(updatedCurrency);
	}

	@Override
	public void deleteCurrency(Integer id) {
		Integer countById = currencyRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new CurrencyNotFoundException("Could not find Currency with ID: " + id);
		}
		
		currencyRepo.deleteById(id);
	}

	
	private Currency getCurrencyId(Integer id) {
		return currencyRepo.findById(id)
				.orElseThrow(()-> new CurrencyNotFoundException("Could not find Currecy with ID: " + id));
	}
	
	
	private CurrencyResponseDTO convertFromEntityToDTO(Currency currency) {
		return mapper.map(currency, CurrencyResponseDTO.class);
	}
	
	private Currency convertFromDTOToEntity(CurrencyRequestDTO currencyRequestDTO) {
		return mapper.map(currencyRequestDTO, Currency.class);
	}
	
}
