package com.sumauma.dscommerce.dto;

public class FieldError {

	private String fieldError;
	private String messageError;

	public FieldError(String fieldError, String messageError) {
		this.fieldError = fieldError;
		this.messageError = messageError;
	}

	public String getFieldError() {
		return fieldError;
	}

	public String getMessageError() {
		return messageError;
	}

}
