package com.example.crm.handler;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.dto.RestErrorMessage;

@RestControllerAdvice
public class RestErrorHandler {
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("|"));
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new RestErrorMessage(e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleRuntimeException(RuntimeException e) {
		var message = "";
		for (var se : e.getSuppressed())
			message += se.getMessage() + "|";
		return new RestErrorMessage(message + e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleException(Exception e) {
		var message = "";
		for (var se : e.getSuppressed())
			message += se.getMessage() + "|";
		return new RestErrorMessage(message + e.getMessage());
	}
}
