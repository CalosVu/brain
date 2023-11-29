package com.brain.management.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseDto<E> {

	private HttpStatus httpStatus;

	private String message;
	
	private String errore;

	private E response;

	public ResponseDto(HttpStatus httpStatus, String message, String errore, E response) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.errore = errore;
		this.response = response;
	}	

}
