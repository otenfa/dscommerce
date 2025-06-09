package com.sumauma.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends BodyError{

	private List<FieldError> listFieldError = new ArrayList<>();
	
	public ValidationError(Instant timeStamp, Integer status, String msgError, String pathUri) {
		super(timeStamp, status, msgError, pathUri);
	}

	public List<FieldError> getListFieldError() {
		return listFieldError;
	}
	
	public void addListErrors(String fieldError, String messageError) {
		listFieldError.add(new FieldError(fieldError, messageError));
	}

}
