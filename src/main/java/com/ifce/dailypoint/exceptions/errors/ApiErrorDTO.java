package com.ifce.dailypoint.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {

	private ErrorDTO error;
	
	@JsonIgnore
	private HttpStatus httpStatus;
}
