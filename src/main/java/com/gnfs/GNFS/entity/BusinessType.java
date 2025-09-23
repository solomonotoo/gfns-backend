package com.gnfs.GNFS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "business_type")
@Data
public class BusinessType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	// Many BusinessTypes belong to one BusinessClass
	@ManyToOne
	@JoinColumn(name = "business_class_id", nullable = false)
	private BusinessClass businessClass;
}
