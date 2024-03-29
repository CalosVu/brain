package com.brain.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorNotFoundException(String message) {
		super(message);

	}

}
