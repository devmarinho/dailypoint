package com.ifce.dailypoint.services.aws;

import java.util.HashMap;



import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.ifce.dailypoint.config.aws.CognitoConfig;
import com.ifce.dailypoint.dtos.outputs.authentication.LoginOutputDTO;
import com.ifce.dailypoint.entities.Token;
import com.ifce.dailypoint.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CognitoService {
    @Value("${user_pool_id}")
	private String userPoolId;
	
	@Value("${client_id}")
	private String clientId;

    @Value("${client_secret}")
    private String clientSecret;

    @Autowired
	private CognitoConfig cognitoConfig;

    @Autowired
    private TokenService tokenService;

    public SignUpResult createUser(String username, String password) throws Exception {
		AWSCognitoIdentityProvider mIdentityProvider = cognitoConfig.getAmazonCognitoIdentityClient();

		SignUpRequest cognitoRequest = new SignUpRequest().withClientId(this.clientId).withUsername(username)
                .withUserAttributes(new AttributeType().withName("email").withValue("fozen112@gmail.com"),new AttributeType().withName("name").withValue("pedro"))
        		.withPassword(password);

    
        	SignUpResult result = mIdentityProvider.signUp(cognitoRequest);

        	return result;
    }

    public LoginOutputDTO login(String username, String password){
		AWSCognitoIdentityProvider mIdentityProvider = this.cognitoConfig.getAmazonCognitoIdentityClient();

		HashMap<String, String> authParams = new HashMap<String, String>();
		authParams.put("USERNAME", username);
		authParams.put("PASSWORD", password);

		InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
				.withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH).withClientId(this.clientId)
				.withAuthParameters(authParams);

		
        InitiateAuthResult authResult = mIdentityProvider.initiateAuth(initiateAuthRequest);
        AuthenticationResultType authenticationResultType = authResult.getAuthenticationResult();
        GetUserResult getUserResult = getUser(authResult.getAuthenticationResult().getAccessToken());
        String cognitoUserId = getUserResult.getUserAttributes().get(0).getValue();

        Token token = tokenService.create(authenticationResultType.getIdToken(), authenticationResultType.getRefreshToken(),
        authenticationResultType.getAccessToken(), authenticationResultType.getTokenType(),
        authenticationResultType.getExpiresIn(), cognitoUserId, username);

        return new LoginOutputDTO(token.getIdToken(), token.getRefreshToken(), token.getTokenType(), token.getExpiresIn(), cognitoUserId);
    }

    private GetUserResult getUser(String accessToken) {
        AWSCognitoIdentityProvider amazonCognitoIdentityClient = this.cognitoConfig.getAmazonCognitoIdentityClient();
		GetUserRequest initiateAuthRequest = new GetUserRequest().withAccessToken(accessToken);
		
		return amazonCognitoIdentityClient.getUser(initiateAuthRequest);
        
    }
}
