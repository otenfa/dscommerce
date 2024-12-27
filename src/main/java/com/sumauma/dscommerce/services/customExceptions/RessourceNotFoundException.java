package com.sumauma.dscommerce.services.customExceptions;

public class RessourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RessourceNotFoundException (String msg) {
		super(msg);
	}
}
