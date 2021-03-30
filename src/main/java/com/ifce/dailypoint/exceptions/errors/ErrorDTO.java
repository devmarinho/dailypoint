package com.ifce.dailypoint.exceptions.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

	private Integer errorCode;

	private String errorSource;

	private String errorReason;

}
