package com.cpt.payments.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cpt.payments.constant.ValidatorEnum;
import com.cpt.payments.dto.PaymentRequestDTO;
import com.cpt.payments.dto.PaymentResponseDTO;
import com.cpt.payments.service.interfaces.PaymentService;
import com.cpt.payments.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${validator.rules}")
	private String validatorRules;
	
	private ApplicationContext applicationContext;
	public PaymentServiceImpl(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public PaymentResponseDTO validateAndProcessPayment(PaymentRequestDTO paymentRequest) {
		log.info("Payment request received: {}", paymentRequest);
		log.info("Validator rules: {}", validatorRules);
		
		String[] rules = validatorRules.split(",");
		
		for(String rule: rules) {
			tiggerValidationRule(paymentRequest, rule);
			
		}
		
		// return errorCode and errorMessage
		
		log.info("Payment request processed successfully. All rules passed");
		
		String transactionId = "TX123";
		String redirectUrl = "www.gogole.clm";
		
		PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(transactionId, redirectUrl);
		
		log.info("Payment response: {}", paymentResponseDTO);
		
		return paymentResponseDTO;
		
	}

	private String tiggerValidationRule(PaymentRequestDTO paymentRequest, String rule) {
		rule = rule.trim();
		log.info("Validating payment request using rule: {}", rule);
		
		Validator validator = null;
		Class<? extends Validator> validatorClass = ValidatorEnum.getClassByName(rule);
		
		if(validatorClass != null) {
			validator = applicationContext.getBean(validatorClass);
			if(validator != null) {
				log.info("Calling validator rule: {}", rule);
				validator.validate(paymentRequest);
				
			}
		}
		
		if(validatorClass == null || validator == null) {
			log.error("Either Validation class not found or Validator Instance not found for "
					+ "| rule: {} | validatorClass: {} | validator: {}", rule, validatorClass, validator);
			return rule;
		}
		return rule;
	}

}
