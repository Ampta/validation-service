package com.cpt.payments.constant;

public enum ErrorCodeEnum {
	
	INVALID_AMOUNT("10001", "amount cannot be negative, please currect the amount and try again");
	
	private String errorCode;
	private String errorMessage;
	
	ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
