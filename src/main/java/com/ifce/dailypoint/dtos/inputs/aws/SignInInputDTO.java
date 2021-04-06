package com.ifce.dailypoint.dtos.inputs.aws;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInInputDTO {
    
    private String username;
    private String password;
}
