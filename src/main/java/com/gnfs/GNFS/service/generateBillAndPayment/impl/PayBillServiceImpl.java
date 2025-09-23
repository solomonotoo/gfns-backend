package com.gnfs.GNFS.service.generateBillAndPayment.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillRequestDTO;
import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillResponseDTO;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.PayBill;
import com.gnfs.GNFS.entity.PaymentModeEnum;
import com.gnfs.GNFS.entity.SellSoldForms;
import com.gnfs.GNFS.exceptions.PayBillNotFoundException;
import com.gnfs.GNFS.exceptions.SellSoldFormsNotFoundException;
import com.gnfs.GNFS.repository.BillTypeRepository;
import com.gnfs.GNFS.repository.PayBillRepository;
import com.gnfs.GNFS.repository.SellSoldFormsRepository;
import com.gnfs.GNFS.service.generateBillAndPayment.PayBillService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayBillServiceImpl implements PayBillService{

	private final PayBillRepository payBillRepo;
	private final ModelMapper mapper;
	private final BillTypeRepository billTypeRepo;
	private final SellSoldFormsRepository sellSoldFormsRepo;
	
	@Override
	public List<PayBillResponseDTO> listPayBill() {
		List<PayBill> listPayBill = payBillRepo.findAll();
		return listPayBill.stream().map(m -> convertFromEntityToDTO(m))
				.toList();
	}

	@Override
	public PayBillResponseDTO getPayBillId(Long id) {
		PayBill payBill = getPayBillById(id);
		return convertFromEntityToDTO(payBill);
	}

	@Override
	public PayBillResponseDTO createPayBill(PayBillRequestDTO payBillRequestDTO) {
		//PayBill payBill = convertFromDTOToEntity(payBillRequestDTO);
		
		// First validate that sellSoldForms exists in the request
	    if (payBillRequestDTO.getSellSoldForms() == null || payBillRequestDTO.getSellSoldForms().getId() == null) {
	        throw new IllegalArgumentException("SellSoldForms ID must be provided");
	    }
	    
	    PayBill payBill = convertFromDTOToEntity(payBillRequestDTO);
	    
		SellSoldForms sellSoldForms = sellSoldFormsRepo.findById(payBill.getSellSoldForms().getId())
				.orElseThrow(()-> new SellSoldFormsNotFoundException("Could not find sold form with ID: " + payBill.getSellSoldForms().getId()));
		payBill.setSellSoldForms(sellSoldForms);
		
		PayBill savedPayBill = payBillRepo.save(payBill);
		
		return convertFromEntityToDTO(savedPayBill);
	}

	@Override
	public PayBillResponseDTO updatePayBill(Long id, PayBillRequestDTO payBillRequestDTO) {
		PayBill existingPayBill = getPayBillById(id);
		PayBill payBillToUpdate = convertFromDTOToEntity(payBillRequestDTO);
		payBillToUpdate.setId(id);
		
		//map the updated pay bill to the existing pay bill
		mapper.map(payBillToUpdate, existingPayBill);
		PayBill updatePayBill = payBillRepo.save(existingPayBill);
		return convertFromEntityToDTO(updatePayBill);
	}

	@Override
	public void deletePayBill(Long id) {
		Long coungById = billTypeRepo.countById(id);
		
		if(coungById == null || coungById == 0) {
			throw new PayBillNotFoundException("Could not find bill payment ID: " + id);
		}
		payBillRepo.deleteById(id);
	}

	
	private PayBill getPayBillById(Long id) {
		return payBillRepo.findById(id)
				.orElseThrow(() -> new PayBillNotFoundException("Could not find bill payment ID: " + id));
	}
	
	private PayBillResponseDTO convertFromEntityToDTO(PayBill payBill) {
		return mapper.map(payBill, PayBillResponseDTO.class);
	}
	
	private PayBill convertFromDTOToEntity(PayBillRequestDTO payBillRequestDTO) {
			PayBill payBill = mapper.map(payBillRequestDTO, PayBill.class);
			
		 // Manually resolve and attach existing Bill Type
        if (payBillRequestDTO.getBillType() != null && payBillRequestDTO.getBillType().getId() != null) {
        	//Long billTypeId = payBillRequestDTO.getBillType().getId().longValue(); // Convert Integer to Long
            BillType billType = billTypeRepo.findById(payBillRequestDTO.getBillType().getId())
                    .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + payBillRequestDTO.getBillType().getId()));
            payBill.setBillType(billType);
        } else {
            payBill.setBillType(null);
        }
         
        // Manually set SellSoldForms from the referenced DTO
        if (payBillRequestDTO.getSellSoldForms() == null || payBillRequestDTO.getSellSoldForms().getId() == null) {
            throw new IllegalArgumentException("SellSoldForms ID must be provided");
        }

        SellSoldForms sellSoldForms = new SellSoldForms();
        sellSoldForms.setId(payBillRequestDTO.getSellSoldForms().getId());
        payBill.setSellSoldForms(sellSoldForms);

        
        // ✅ Set PaymentType manually (important!)
        if (payBillRequestDTO.getPaymentType() != null && payBillRequestDTO.getPaymentType().getValue() != null) {
            PaymentModeEnum enumValue = payBillRequestDTO.getPaymentType().toEnum(); // 💡 your helper method
            payBill.setPaymentType(enumValue);
        }
        
		return payBill;
	}
	
	
	
	
}
