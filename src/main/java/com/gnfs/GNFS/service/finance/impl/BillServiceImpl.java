package com.gnfs.GNFS.service.finance.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.finance.BillRequestDTO;
import com.gnfs.GNFS.dto.finance.BillResponseDTO;
import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.BillTypeResponseDTO;
import com.gnfs.GNFS.entity.Bill;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.entity.Currency;
import com.gnfs.GNFS.exceptions.BillNotfoundException;
import com.gnfs.GNFS.repository.BillRepository;
import com.gnfs.GNFS.repository.BillTypeRepository;
import com.gnfs.GNFS.repository.CurrencyRepository;
import com.gnfs.GNFS.service.finance.BillService;
import com.gnfs.GNFS.service.finance.BillTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
	
	private final BillRepository billRepo;
	private final ModelMapper mapper;
	private final CurrencyRepository currencyRepo;
	private final BillTypeRepository billTypeRepo;

	@Override
	public List<BillResponseDTO> listBill() {
		// TODO Auto-generated method stub
//		return billRepo.findAll()
//				.stream().map((bill) -> convertFromEntityToDTO(bill))
//				.collect(Collectors.toList());
		List<Bill> listBills = billRepo.findAll();
		
		return convertFromListEntityToListDTO(listBills);
	}

	

	@Override
	public BillResponseDTO getBillId(Integer id) {
		Bill bill = getBillById(id);
		return convertFromEntityToDTO(bill);
	}

	@Override
	public BillResponseDTO createBill(BillRequestDTO billType) {
		Bill bill = convertFromDTOToEntity(billType);
		Bill saveBill = billRepo.save(bill);
		return convertFromEntityToDTO(saveBill);
	}

	@Override
	public BillResponseDTO updateBill(Integer id, BillRequestDTO billRequestDTO) {
		Bill existingBill = getBillById(id);
		Bill billsToUpdate = convertFromDTOToEntity(billRequestDTO);
		billsToUpdate.setId(id);
		//map the updated bill to the existing bill
		mapper.map(billsToUpdate, existingBill);
		Bill updateBill = billRepo.save(existingBill);
		return convertFromEntityToDTO(updateBill);
	}

	@Override
	public void deleteBill(Integer id) {
		Integer countById = billRepo.countById(id);
		
		if(countById == 0 || countById == null) {
			throw new BillNotfoundException("Could not find Bill ID: " + id);
		}
		
		billRepo.deleteById(id);
		
	}

	private Bill getBillById(Integer id) {
		return billRepo.findById(id)
				.orElseThrow(() -> new BillNotfoundException("Could not find Bill ID: " + id));
	}
	
	private BillResponseDTO convertFromEntityToDTO(Bill bill) {
		// TODO Auto-generated method stub
		return mapper.map(bill, BillResponseDTO.class);
	}
	
	private Bill convertFromDTOToEntity(BillRequestDTO billRequestDTO) {
		
		Bill bill =  mapper.map(billRequestDTO, Bill.class);
		
		 // Manually resolve and attach existing Currency
        if (billRequestDTO.getCurrencyRequestDTO() != null && billRequestDTO.getCurrencyRequestDTO().getId() != null) {
            Currency currency = currencyRepo.findById(billRequestDTO.getCurrencyRequestDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + billRequestDTO.getCurrencyRequestDTO().getId()));
            bill.setCurrency(currency);
        } else {
        	bill.setCurrency(null);
        }
        
     // Manually resolve and attach existing Bill Type
        if (billRequestDTO.getBillTypeRequestDTO() != null && billRequestDTO.getBillTypeRequestDTO().getId() != null) {
            BillType billType = billTypeRepo.findById(billRequestDTO.getBillTypeRequestDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + billRequestDTO.getBillTypeRequestDTO().getId()));
            bill.setBillType(billType);
        } else {
            bill.setBillType(null);
        }
		
		return bill;
	}
	
	private List<BillResponseDTO> convertFromListEntityToListDTO(List<Bill> listBills){
		return listBills.stream().map(bills -> convertFromEntityToDTO(bills))
				.collect(Collectors.toList());
	}

}
