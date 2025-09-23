package com.gnfs.GNFS.service.finance.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.finance.SellSoldFormsRequestDTO;
import com.gnfs.GNFS.dto.finance.SellSoldFormsResponseDTO;
import com.gnfs.GNFS.entity.ApplicationForm;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.entity.SellSoldForms;
import com.gnfs.GNFS.exceptions.BillNotfoundException;
import com.gnfs.GNFS.exceptions.SellSoldFormsNotFoundException;
import com.gnfs.GNFS.repository.ApplicationFormRespository;
import com.gnfs.GNFS.repository.RegionRepository;
import com.gnfs.GNFS.repository.SellSoldFormsRepository;
import com.gnfs.GNFS.service.finance.SellSoldFormsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellSoldFormsServiceImpl implements SellSoldFormsService{
	
	private final SellSoldFormsRepository sellSoldFormsRepo;
	private final ModelMapper mapper;
	private final ApplicationFormRespository applicationFormRespo;
	private final RegionRepository regionRepo;
	
	// Generate random alphanumeric string
  //  String randomAlphanumeric = generateRandomAlphanumeric(10); // 10 is the length of the string
	
//	@Override
//	public List<SellSoldFormsResponseDTO> listSellSoldForms() {
//		List<SellSoldForms> listsForms = sellSoldFormsRepo.findAll();
//		return listsForms.stream().map( forms -> convertFromEntityToDTO(forms))
//				.collect(Collectors.toList());
//	}
	
	//same as the above
	@Override
	public List<SellSoldFormsResponseDTO> listSellSoldForms() {
		List<SellSoldForms> listsForms = sellSoldFormsRepo.findAll();
		return listsForms.stream().map( this :: convertFromEntityToDTO)
				.toList(); //shorthand for .collect(Collectors.toList());
	}

	@Override
	public SellSoldFormsResponseDTO createSellSoldForms(SellSoldFormsRequestDTO sellSoldFormsRequestDTO) {
		
		SellSoldForms sellSoldForms = convertFromDTOToEntity(sellSoldFormsRequestDTO);
		//sellSoldForms.setFormNumber(randomAlphanumeric);
		SellSoldForms saveForms = sellSoldFormsRepo.save(sellSoldForms);
		return convertFromEntityToDTO(saveForms);
	}

	@Override
	public SellSoldFormsResponseDTO getSellSoldForms(Long id) {
		SellSoldForms sellSoldForms = getSellSoldFormsById(id);
		return convertFromEntityToDTO(sellSoldForms);
	}

	@Override
	public SellSoldFormsResponseDTO updataSellSoldForms(Long id, SellSoldFormsRequestDTO sellSoldFormsRequestDTO) {
		SellSoldForms existingSellSoldForms = getSellSoldFormsById(id);
		SellSoldForms sellSoldForms2Update = convertFromDTOToEntity(sellSoldFormsRequestDTO);
		sellSoldForms2Update.setId(id);
		
		mapper.map(sellSoldForms2Update, existingSellSoldForms);
		
		SellSoldForms updateSellSoldForms = sellSoldFormsRepo.save(existingSellSoldForms);
		
		return convertFromEntityToDTO(updateSellSoldForms);
	}

	@Override
	public void deleteSellSoldForms(Long id) {
		Long countById = sellSoldFormsRepo.countById(id);
		if(countById == 0 || countById == null) {
			throw new SellSoldFormsNotFoundException("Could not find form ID: " + id);
		}
		
		sellSoldFormsRepo.deleteById(id);
		
		
	}

	
	private SellSoldForms getSellSoldFormsById(Long id) {
		return sellSoldFormsRepo.findById(id)
				.orElseThrow(() -> new SellSoldFormsNotFoundException("Could not find form ID: " + id));
	}
	
	private SellSoldFormsResponseDTO convertFromEntityToDTO(SellSoldForms sellSoldForms) {
		return mapper.map(sellSoldForms, SellSoldFormsResponseDTO.class);
	}
	
	private SellSoldForms convertFromDTOToEntity(SellSoldFormsRequestDTO sellSoldFormsRequestDTO) {
		SellSoldForms sellSoldForms = mapper.map(sellSoldFormsRequestDTO, SellSoldForms.class);
		
		// Manually resolve and attach existing application form
        if (sellSoldFormsRequestDTO.getApplicationForm() != null && sellSoldFormsRequestDTO.getApplicationForm().getId() != null) {
            ApplicationForm applicationForm = applicationFormRespo.findById(sellSoldFormsRequestDTO.getApplicationForm().getId())
                    .orElseThrow(() -> new RuntimeException("Application Form not found with ID: " + sellSoldFormsRequestDTO.getApplicationForm().getId()));
            sellSoldForms.setApplicationForm(applicationForm);
        } else {
            sellSoldForms.setApplicationForm(null);
        }
        
     // Manually resolve and attach existing regions
        if (sellSoldFormsRequestDTO.getRegion() != null && sellSoldFormsRequestDTO.getRegion().getId() != null) {
            Region region = regionRepo.findById(sellSoldFormsRequestDTO.getRegion().getId())
                    .orElseThrow(() -> new RuntimeException("Region not found with ID: " + sellSoldFormsRequestDTO.getRegion().getId()));
            sellSoldForms.setRegion(region);
        } else {
            sellSoldForms.setRegion(null);
        }
		return sellSoldForms;
	}
	
	
	
//	private String generateRandomAlphanumeric(int length) {
//	    String alphanumericChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	    SecureRandom random = new SecureRandom();
//	    StringBuilder sb = new StringBuilder(length);
//	    
//	    for (int i = 0; i < length; i++) {
//	        int randomIndex = random.nextInt(alphanumericChars.length());
//	        sb.append(alphanumericChars.charAt(randomIndex));
//	    }
//	    
//	    return sb.toString();
//	}
	
}
