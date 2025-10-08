package com.gnfs.GNFS.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "incidents")
@Data
public class IncidentAdd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String locationArea;
	
	@Column(nullable = false)
	private String photo;
	
	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;
	
	@ManyToOne
	@JoinColumn(name = "incident_cat_id", nullable = false)
	private IncidentCategory incidentCategory;
	
	@ManyToOne
	@JoinColumn(name = "incident_type_id", nullable = false)
	private IncidentType incidentType;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createDate;
}
