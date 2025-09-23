package com.gnfs.GNFS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "pay_bill")
@Data
public class PayBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String amount;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "bill_type_id")
	private BillType billType;

	@Enumerated(EnumType.STRING)
	private PaymentModeEnum paymentType;
	
	@OneToOne
	@JoinColumn(name = "sell_sold_forms_id")
	private SellSoldForms sellSoldForms;
	
	
	
}
