package com.gnfs.GNFS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incident_assigned")
@Getter
@Setter
public class IncidentAssignedToOfficer extends IdBaseEntity<Long>{
	
	@ManyToOne
	@JoinColumn(name = "officer_id")
	private StaffRegistration officer;
	
	@ManyToOne
	@JoinColumn(name = "incident_id")
	private IncidentAdd incident;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private IncidentPriorityEnum priority;
}
