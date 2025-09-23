package com.gnfs.GNFS.service.finance;

import java.util.List;

import com.gnfs.GNFS.dto.finance.BillRequestDTO;
import com.gnfs.GNFS.dto.finance.BillResponseDTO;

public interface BillService {
	public List<BillResponseDTO> listBill();
	public BillResponseDTO getBillId(Integer id);
	public BillResponseDTO createBill(BillRequestDTO billType);
	public BillResponseDTO updateBill(Integer id, BillRequestDTO billType);
	public void deleteBill(Integer id);
}
