package com.gnfs.GNFS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomSuccessMessageResponse<T> {
	
	private String message;
	private T data;
	
}
