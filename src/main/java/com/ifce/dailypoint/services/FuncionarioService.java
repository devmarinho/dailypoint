package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.FuncionarioInputDTO;
import com.ifce.dailypoint.entities.Funcionario;
import com.ifce.dailypoint.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario create(FuncionarioInputDTO funcionarioInputDTO){
        Funcionario funcionario = new Funcionario();
        funcionario.setAddress(funcionarioInputDTO.getEndereco());
        funcionario.setCpf(funcionarioInputDTO.getCpf());
        funcionario.setName(funcionarioInputDTO.getNome());
        return save(funcionario);
    }


}
