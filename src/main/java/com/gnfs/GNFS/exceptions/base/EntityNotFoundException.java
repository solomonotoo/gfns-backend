package com.gnfs.GNFS.exceptions.base;

public class EntityNotFoundException extends ResourceNotFoundException{

	public EntityNotFoundException(Class<?> entity, Object value) {
		//return the entity class name eg. User
		super(entity.getSimpleName() + " with ID: (" + value + ") not found ");
		// TODO Auto-generated constructor stub
	}

}
