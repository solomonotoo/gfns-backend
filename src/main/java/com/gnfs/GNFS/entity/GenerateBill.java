package com.gnfs.GNFS.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class GenerateBill {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private double amount;
}
