package com.gnfs.GNFS.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "business_class")
@Data
public class BusinessClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	// One BusinessClass has many BusinessTypes
	@OneToMany(mappedBy = "businessClass", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BusinessType> businessTypes = new ArrayList<>();
	
}
