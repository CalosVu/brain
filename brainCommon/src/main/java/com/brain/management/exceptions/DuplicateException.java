package com.brain.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateException(String message) {
		super(message);
	}

}
