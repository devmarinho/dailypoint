package com.ifce.dailypoint.exceptions;
import lombok.Getter;
import lombok.Setter;

import com.ifce.dailypoint.exceptions.errors.ErrorDTO;

import org.springframework.http.HttpStatus;

public class BusinessErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    @Getter
	private final transient ErrorDTO errorDTO;
    
    @Getter
    @Setter
	private HttpStatus httpStatus;

    public BusinessErrorException(Integer errorCode, BusinessErrorEnum businessError, String reason, HttpStatus httpStatus) {
		errorDTO = new ErrorDTO(errorCode, businessError.name() , reason);
		setHttpStatus(httpStatus); 
	}

    public BusinessErrorException(Integer errorCode, BusinessErrorEnum businessError, String reason) {
		this(errorCode, businessError, reason, null);
	}

}
