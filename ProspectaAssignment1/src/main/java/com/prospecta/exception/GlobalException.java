package com.prospecta.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(EntryException.class)
	public ResponseEntity<MyErrorDetails> entryExceptionHandler(EntryException exception, WebRequest request){
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception exception, WebRequest request){
		
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);		
	}
}
