package com.ifce.dailypoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

import com.ifce.dailypoint.exceptions.errors.ApiErrorDTO;

@RestControllerAdvice
public class BusinessRuleExceptionHandler {

	private static final HttpStatus defaultHttpStatus = HttpStatus.BAD_REQUEST;

	@ExceptionHandler(BusinessErrorException.class)
	public ApiErrorDTO handle(BusinessErrorException exception, HttpServletResponse response) {
		ApiErrorDTO error = new ApiErrorDTO();

		if(exception.getHttpStatus() != null) {
			error.setHttpStatus(exception.getHttpStatus());
		}

		error.setError(exception.getErrorDTO());
		HttpStatus httpStatus = exception.getHttpStatus() == null ? defaultHttpStatus : exception.getHttpStatus();
		response.setStatus(httpStatus.value());
		return error;
	}

}
