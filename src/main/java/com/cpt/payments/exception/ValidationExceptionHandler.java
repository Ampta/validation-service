package com.cpt.payments.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cpt.payments.pojo.PaymentError;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {
	
	@ExceptionHandler(ValidationException.class) 
	public ResponseEntity<PaymentError> handleCustomUncheckedException(ValidationException ex){

		log.error("----- ValidationException occurred: {}", ex);
		
		PaymentError paymentError = new PaymentError(ex.getErrorCode(), ex.getErrorMessage());
		
		log.info("Returning from ValidationExceptionHandler: {}", paymentError);
		
		return new ResponseEntity<>(paymentError, ex.getHttpStatus());
	}

}
