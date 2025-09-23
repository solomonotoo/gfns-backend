package com.gnfs.GNFS.service.finance.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.BillTypeResponseDTO;
import com.gnfs.GNFS.entity.BillType;
import com.gnfs.GNFS.exceptions.BillTypeNotFoundException;
import com.gnfs.GNFS.repository.BillTypeRepository;
import com.gnfs.GNFS.service.finance.BillTypeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BillTypeServiceImpl implements BillTypeService {

	
	private final BillTypeRepository billTypeRepos;
	private final ModelMapper mapper;
	
	@Override
	public List<BillTypeResponseDTO> listBillType() {
		
		return billTypeRepos.findAll().stream().map(bill -> convertFromEntityToDTO(bill) )
				.collect(Collectors.toList());
	}

	@Override
	public BillTypeResponseDTO getBillTypeId(Integer id) {
		BillType billType = getBillTypeById(id);
		return convertFromEntityToDTO(billType);
	}

	@Override
	public BillTypeResponseDTO createBillType(BillTypeRequestDTO BillTypeType) {
		BillType billType = convertDTOToEntity(BillTypeType);
		BillType saveBillType = billTypeRepos.save(billType);
		return convertFromEntityToDTO(saveBillType);
	}

	@Override
	public BillTypeResponseDTO updateBillType(Integer id, BillTypeRequestDTO billTypeType) {
		BillType existingBillType = getBillTypeById(id);
		  existingBillType.setName(billTypeType.getName());
		  existingBillType.setDescription(billTypeType.getDescription());
		  
		  BillType updateBillType = billTypeRepos.save(existingBillType);
		return convertFromEntityToDTO(updateBillType);
	}

	@Override
	public void deleteBillType(Integer id) {
		Integer countById = billTypeRepos.countById(id);
		
		if(countById == null || countById == 0) {
			throw new BillTypeNotFoundException("Could not find Bill Type ID: " + id);
		}
		
		billTypeRepos.deleteById(id);
	}

	private BillType getBillTypeById(Integer id) {
		return billTypeRepos.findById(id)
				.orElseThrow(() -> new BillTypeNotFoundException("Could not find Bill Type ID: " + id));
	}
	
	private BillTypeResponseDTO convertFromEntityToDTO(BillType billType) {
		return mapper.map(billType, BillTypeResponseDTO.class);
	}
	
	private BillType convertDTOToEntity(BillTypeRequestDTO billTypeRequestDTO) {
		return mapper.map(billTypeRequestDTO, BillType.class);
	}
}
