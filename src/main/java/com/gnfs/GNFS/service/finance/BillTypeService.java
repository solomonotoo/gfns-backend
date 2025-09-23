package com.gnfs.GNFS.service.finance;

import java.util.List;

import com.gnfs.GNFS.dto.finance.BillTypeRequestDTO;
import com.gnfs.GNFS.dto.finance.BillTypeResponseDTO;

public interface BillTypeService {

	public List<BillTypeResponseDTO> listBillType();
	public BillTypeResponseDTO getBillTypeId(Integer id);
	public BillTypeResponseDTO createBillType(BillTypeRequestDTO BillTypeType);
	public BillTypeResponseDTO updateBillType(Integer id, BillTypeRequestDTO BillTypeType);
	public void deleteBillType(Integer id);
}
