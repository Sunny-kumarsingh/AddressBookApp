package com.addressbook.addressbookapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
	}
	
	@ExceptionHandler(DuplicateContactException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateContact(DuplicateContactException e){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage()));
	}
	
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleContactNotFound(ContactNotFoundException e){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage()));
	}
}