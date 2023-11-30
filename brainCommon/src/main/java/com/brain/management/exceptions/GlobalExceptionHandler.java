package com.brain.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brain.management.model.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<ResponseDto<String>> handleDuplicateException(DuplicateException ex) {
		ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "400", null);
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ErrorNotFoundException.class)
	public ResponseEntity<ResponseDto<String>> handleErrorNotFoundException(ErrorNotFoundException ex) {
		ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.NOT_FOUND, ex.getMessage(), "404", null);
		return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ErrorDeleteException.class)
	public ResponseEntity<ResponseDto<String>> handleErrorDeleteException(ErrorDeleteException ex) {
		ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "400", null);
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
	}

}
