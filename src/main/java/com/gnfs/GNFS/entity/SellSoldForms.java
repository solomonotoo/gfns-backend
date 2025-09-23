package com.gnfs.GNFS.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sell_sold_forms")
@Data
@EntityListeners(AuditingEntityListener.class)
public class SellSoldForms {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	private String location;
	private String formNumber;
	
	@ManyToOne
	@JoinColumn(name = "application_form_type_id")
	private ApplicationForm applicationForm;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
}
