package com.ifce.dailypoint.dtos.inputs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioInputDTO {
    
    private String nome;
    private String cpf;
    private String endereco;

}
