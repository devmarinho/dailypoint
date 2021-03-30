package com.ifce.dailypoint.dtos.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterpriseInputDTO {
    
    private String name;
    private String cnpj;
    private String address;
    private String email;
}
