package com.sumauma.dscommerce.controllers.handlerExceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sumauma.dscommerce.dto.BodyError;
import com.sumauma.dscommerce.dto.ValidationError;
import com.sumauma.dscommerce.services.customExceptions.DataBaseException;
import com.sumauma.dscommerce.services.customExceptions.RessourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<BodyError> custonName(RessourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		BodyError err = new BodyError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<BodyError> database(DataBaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		BodyError err = new BodyError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BodyError> methodArgumentNotValidation(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos",
				request.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addListErrors(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}

}
