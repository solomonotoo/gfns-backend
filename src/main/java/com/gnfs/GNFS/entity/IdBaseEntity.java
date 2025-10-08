package com.gnfs.GNFS.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdBaseEntity <T>{

	@Id
	protected T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	
}
