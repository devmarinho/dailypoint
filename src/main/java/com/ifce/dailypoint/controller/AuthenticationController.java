package com.ifce.dailypoint.controller;
import com.ifce.dailypoint.dtos.inputs.aws.SignInInputDTO;
import com.ifce.dailypoint.dtos.inputs.aws.SignUpInputDTO;
import com.ifce.dailypoint.dtos.outputs.authentication.LoginOutputDTO;
import com.ifce.dailypoint.services.aws.AutheticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("authentication")
public class AuthenticationController {
    
    @Autowired
    private AutheticationService autheticationService;

    @PostMapping(value="register")
    public void registerCompany(@RequestBody SignUpInputDTO signUp) throws Exception {
        autheticationService.createUser(signUp.getUsername(), signUp.getPassword(), signUp.getEmail());
    }

    @PostMapping(value="login")
    public LoginOutputDTO login(@RequestBody SignInInputDTO signIn) throws Exception {
       return autheticationService.login(signIn.getUsername(), signIn.getPassword());
    }
    
}
