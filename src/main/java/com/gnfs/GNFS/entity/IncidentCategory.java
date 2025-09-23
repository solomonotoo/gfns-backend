package com.gnfs.GNFS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "incident_categories")
@Data
public class IncidentCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "incident_class_id", nullable = false)
	private IncidentClassification classification;
}
