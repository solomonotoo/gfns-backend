package com.gnfs.GNFS.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdBaseEntityUUid extends IdBaseEntity<String> {

	@GeneratedValue(strategy = GenerationType.UUID)
	protected String id;
}
