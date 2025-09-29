package com.cpt.payments.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {

	private Double amount;
	private String currency;
}
