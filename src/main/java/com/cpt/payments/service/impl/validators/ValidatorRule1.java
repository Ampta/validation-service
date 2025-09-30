package com.cpt.payments.service.impl.validators;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cpt.payments.constant.ErrorCodeEnum;
import com.cpt.payments.dto.PaymentRequestDTO;
import com.cpt.payments.exception.ValidationException;
import com.cpt.payments.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidatorRule1 implements Validator {

	@Override
	public void validate(PaymentRequestDTO paymentRequestDTO) {
		log.info("ValidatorRule1 | Validating payment request: {}", paymentRequestDTO);
		
		// TODO implement validating logic here 
		
		if(paymentRequestDTO.getAmount() < 0) {
			log.error("ValidationRule1 | payment request validation failed, amount cannot be negative" );
			
			ValidationException validationException = new ValidationException(
					ErrorCodeEnum.INVALID_AMOUNT.getErrorCode(), 
					ErrorCodeEnum.INVALID_AMOUNT.getErrorMessage(),
					HttpStatus.BAD_REQUEST);
			
			log.info("Raising validationException: {}", validationException);
			
			throw validationException; //throw new RuntimeException("amount cannot be negative");
		}
		
		log.info("ValidatorRule1 | Payment request validated successfully");

	}

}
