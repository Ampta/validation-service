package com.cpt.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDTO {
	
	private String transactionId;
	private String redirectUrl;
	
	

}
