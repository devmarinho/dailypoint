package com.ifce.dailypoint.services;

import java.time.LocalDateTime;

import com.ifce.dailypoint.entities.Token;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
	private TokenRepository tokenRepository;

    public Token save(Token token) {
		return tokenRepository.save(token);
	}

    public Token findByIdToken(String idToken) {
		return tokenRepository.findByIdToken(idToken);
	}

    public Token create(
			final String idToken,
			final String refreshToken,
			final String accessToken,
			final String tokenType,
			final Integer expiresIn,
			final String cognitoUserId,
            final String username) {
		Token token = new Token();
		token.setIdToken(idToken);
		token.setRefreshToken(refreshToken);
		token.setAccessToken(accessToken);
		token.setTokenType(tokenType);
		token.setExpiresIn(LocalDateTime.now().plusSeconds(expiresIn));
		token.setCognitoUserId(cognitoUserId);
        token.setUsername(username);
		return save(token);
	}

	public Token fetchValidTokenByIdToken(final String idToken) throws BusinessErrorException{
		Token token = findByIdToken(idToken);
		if(token == null)
			throw new BusinessErrorException(4, BusinessErrorEnum.INVALID_TOKEN, "Token inválido");

		if(token.getExpiresIn().isBefore(LocalDateTime.now()))
			throw new BusinessErrorException(5, BusinessErrorEnum.EXPIRED_TOKEN, "Token expirado");

		return token;
	}
}
