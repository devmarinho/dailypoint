package com.ifce.dailypoint.controller;
import com.ifce.dailypoint.services.aws.CognitoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("authentication")
public class AuthenticationController {
    
    @Autowired
    private CognitoService cognitoService;

    @PostMapping(value="register")
    public void registerCompany() throws Exception {
        cognitoService.createUser("teste06", "password");
    }

    @PostMapping(value="login")
    public void login() throws Exception {
        cognitoService.login("teste06", "password");
    }
    
}
