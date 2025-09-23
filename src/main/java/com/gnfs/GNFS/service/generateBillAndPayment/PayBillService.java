package com.gnfs.GNFS.service.generateBillAndPayment;

import java.util.List;

import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillRequestDTO;
import com.gnfs.GNFS.dto.generateBillAndPayment.PayBillResponseDTO;



public interface PayBillService {

	public List<PayBillResponseDTO> listPayBill();
	public PayBillResponseDTO getPayBillId(Long id);
	public PayBillResponseDTO createPayBill(PayBillRequestDTO PayBillType);
	public PayBillResponseDTO updatePayBill(Long id, PayBillRequestDTO PayBillType);
	public void deletePayBill(Long id);
}
