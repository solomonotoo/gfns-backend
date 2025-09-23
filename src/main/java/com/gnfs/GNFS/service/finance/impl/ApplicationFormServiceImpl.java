package com.gnfs.GNFS.service.finance.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.finance.ApplicationFormRequestDTO;
import com.gnfs.GNFS.dto.finance.ApplicationFormResponseDTO;
import com.gnfs.GNFS.dto.finance.BillTypeEnumDTO;
import com.gnfs.GNFS.entity.ApplicationForm;
import com.gnfs.GNFS.entity.Currency;
import com.gnfs.GNFS.exceptions.ApplicationFormNotFoundException;
import com.gnfs.GNFS.repository.ApplicationFormRespository;
import com.gnfs.GNFS.repository.CurrencyRepository;
import com.gnfs.GNFS.service.finance.ApplicationFormService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ApplicationFormServiceImpl implements ApplicationFormService{
	
	private final ApplicationFormRespository applicationFormRepo;
	private final CurrencyRepository currencyRepo;
	private final ModelMapper mapper;
	
	
	@Override
	public List<ApplicationFormResponseDTO> listApplicationForm() {
		
		return  applicationFormRepo.findAll()
				.stream().map(application -> convertFromEntityToDTO(application) )
				.collect(Collectors.toList());
	}

	@Override
	public ApplicationFormResponseDTO getApplicationFormId(Integer id) {
		ApplicationForm applicationForm = getApplicationFormById(id);
		return convertFromEntityToDTO(applicationForm);
	}

	@Override
	public ApplicationFormResponseDTO createApplicationForm(ApplicationFormRequestDTO requestDTO) {
		ApplicationForm applicationForm = convertFromDTOToEntity(requestDTO);
		ApplicationForm saveApplication = applicationFormRepo.save(applicationForm);
		return convertFromEntityToDTO(saveApplication);
	}

	@Override
	public ApplicationFormResponseDTO updateApplicationForm(Integer id, ApplicationFormRequestDTO requestDTO) {
		ApplicationForm existingApplication = getApplicationFormById(id);
		ApplicationForm updatedData = convertFromDTOToEntity(requestDTO);
		updatedData.setId(id);
		mapper.map(updatedData, existingApplication);
		ApplicationForm updatedApplication = applicationFormRepo.save(existingApplication);
		return convertFromEntityToDTO(updatedApplication);
	}

	@Override
	public void deleteApplicationForm(Integer id) {
		Integer countById = applicationFormRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new ApplicationFormNotFoundException("Could not find application with ID: " + id);
		}
		
		applicationFormRepo.deleteById(id);
	}
	
	
	private ApplicationForm getApplicationFormById(Integer id) {
		return applicationFormRepo.findById(id)
				.orElseThrow(()-> new ApplicationFormNotFoundException("Could not find application form with ID: " + id));
	}

	
	private ApplicationFormResponseDTO convertFromEntityToDTO(ApplicationForm applicationForm) {
		ApplicationFormResponseDTO dto = mapper.map(applicationForm, ApplicationFormResponseDTO.class);
		// Manually map the billType to DTO
        dto.setBillType(BillTypeEnumDTO.fromEnum(applicationForm.getBillType()));
		return dto;
	}
	
	private ApplicationForm convertFromDTOToEntity(ApplicationFormRequestDTO requestDTO) {
		
		ApplicationForm entity = mapper.map(requestDTO, ApplicationForm.class);

        // Manually map the billType DTO to enum
        if (requestDTO.getBillType() != null) {
            entity.setBillType(requestDTO.getBillType().toEnum());
        }
        
     // Manually resolve and attach existing Currency
        if (requestDTO.getCurrency() != null && requestDTO.getCurrency().getId() != null) {
            Currency currency = currencyRepo.findById(requestDTO.getCurrency().getId())
                    .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + requestDTO.getCurrency().getId()));
            entity.setCurrency(currency);
        } else {
            entity.setCurrency(null);
        }
		
		return entity;
	}
}
