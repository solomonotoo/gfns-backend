package com.gnfs.GNFS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegionNotFoundException extends RuntimeException{

	public RegionNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

	
}
