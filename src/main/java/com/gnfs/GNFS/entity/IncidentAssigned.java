package com.gnfs.GNFS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "incident_assigned")
public class IncidentAssigned extends IdBaseEntityNumeric<Integer>{

	
	private String description;
}
