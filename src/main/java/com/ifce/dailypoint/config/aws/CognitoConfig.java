package com.ifce.dailypoint.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CognitoConfig {
    @Value("${access_key}")
	private String acessKey;

	@Value("${secret_key}")
	private String secretKey;
	
	@Value("${user_pool_id}")
	private String userPoolId;
	
	@Value("${client_id}")
	private String clientId;

	
	public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
		AWSCredentials credentials = new BasicAWSCredentials(this.acessKey, this.secretKey);
		
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(credentials);

		AWSCognitoIdentityProvider client = AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_2)
                .build();
		
		return client;
	}
}
