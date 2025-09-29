package com.cpt.payments.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cpt.payments.dto.PaymentRequestDTO;
import com.cpt.payments.service.impl.validators.ValidatorRule1;
import com.cpt.payments.service.impl.validators.ValidatorRule2;
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
	public String validateAndProcessPayment(PaymentRequestDTO paymentRequest) {
		log.info("Payment request received: {}", paymentRequest);
		log.info("Validator rules: {}", validatorRules);
		
		String[] rules = validatorRules.split(",");
		
		for(String rule: rules) {
			rule = rule.trim();
			log.info("Validating payment request using rule: {}", rule);
			
			Validator validator = null;
			if(rule.equals("VALIDATOR_RULE1")) {
				validator = applicationContext.getBean(ValidatorRule1.class);
			}else if(rule.equals("VALIDATOR_RULE2")) {
				validator = applicationContext.getBean(ValidatorRule2.class);
			}
			
			if(validator == null) {
				log.error("Validator not found for rule: {}", rule);
				continue; 	 
			}
			
			validator.validate(paymentRequest);
			
		}
		
		return "Response from service layer" + validatorRules;
	}

}
