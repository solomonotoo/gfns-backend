package com.gnfs.GNFS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "incident_types",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class IncidentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "incident_category_id", nullable = false)
	private IncidentCategory category;
}
