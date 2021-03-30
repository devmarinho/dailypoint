package com.ifce.dailypoint.services;

import com.ifce.dailypoint.entities.Token;
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
		token.setExpiresIn(expiresIn);
		token.setCognitoUserId(cognitoUserId);
        token.setUsername(username);
		return save(token);
	}
}
