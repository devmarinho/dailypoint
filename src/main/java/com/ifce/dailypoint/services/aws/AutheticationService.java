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
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.ifce.dailypoint.config.aws.CognitoConfig;
import com.ifce.dailypoint.dtos.outputs.authentication.LoginOutputDTO;
import com.ifce.dailypoint.entities.Token;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.services.EnterpriseService;
import com.ifce.dailypoint.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AutheticationService {
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

    @Autowired
    private EnterpriseService enterpriseService;

    public SignUpResult createUser(final String username, final String password, final String email) throws BusinessErrorException {
		SignUpResult result;
        AWSCognitoIdentityProvider mIdentityProvider = cognitoConfig.getAmazonCognitoIdentityClient();

		SignUpRequest cognitoRequest = new SignUpRequest()
                .withClientId(this.clientId)
                .withUsername(username)
                .withUserAttributes(new AttributeType().withName("email").withValue(email))
        		.withPassword(password);

    
                try {
                    result = mIdentityProvider.signUp(cognitoRequest);
                } catch (UsernameExistsException e) {
                    throw new BusinessErrorException(1,BusinessErrorEnum.USERNAME_ALREADY_EXISTS, "Username já usado");
                } catch (InvalidPasswordException e) {
                    throw new BusinessErrorException(2,BusinessErrorEnum.INVALID_PASSWORD, "Senha inválida");
                } catch (InvalidParameterException e) {
                    throw new BusinessErrorException(3,BusinessErrorEnum.INVALID_PARAMETER, "Paramentro inválido");
                }

        	return result;
    }

    public LoginOutputDTO login(String username, String password) throws BusinessErrorException{
        enterpriseService.fetchValidByUsername(username);
        InitiateAuthResult authResult;
		AWSCognitoIdentityProvider mIdentityProvider = this.cognitoConfig.getAmazonCognitoIdentityClient();

		HashMap<String, String> authParams = new HashMap<String, String>();
		authParams.put("USERNAME", username);
		authParams.put("PASSWORD", password);

		InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
				.withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH).withClientId(this.clientId)
				.withAuthParameters(authParams);

        try {
			authResult = mIdentityProvider.initiateAuth(initiateAuthRequest);
		} catch (NotAuthorizedException e) {
			throw new BusinessErrorException(7,BusinessErrorEnum.USER_NOT_AUTHORIZED,  "Suas informações de autenticação estão incorretas. Tente novamente.");
		} catch (InvalidParameterException e) {
			throw new BusinessErrorException(8,BusinessErrorEnum.INVALID_PARAMETER, "Paramentro inválido" );
		} catch (UserNotFoundException e) {
			throw new BusinessErrorException(9,BusinessErrorEnum.USERNAME_NOT_FOUND, "Empresa não encontrada");
		}
        
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
