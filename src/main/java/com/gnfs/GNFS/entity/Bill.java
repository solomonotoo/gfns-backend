package com.gnfs.GNFS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bills")
@Data
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name = "bill_type_id")
	private BillType billType;

}
