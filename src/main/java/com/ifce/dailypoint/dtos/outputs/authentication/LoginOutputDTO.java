package com.ifce.dailypoint.dtos.outputs.authentication;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginOutputDTO {

	private String IdToken;
	private String refreshToken;
	private String tokenType;
	private Integer expiresIn;
	private String cognitoUserId;
	
	public LoginOutputDTO(String IdToken, String refreshToken, String tokenType, Integer expiresIn, String cognitoUserId) {
		this.IdToken = IdToken;
		this.refreshToken = refreshToken;	
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
		this.cognitoUserId = cognitoUserId;
    }
}
