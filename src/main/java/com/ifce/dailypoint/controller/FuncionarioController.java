package com.ifce.dailypoint.controller;

import com.ifce.dailypoint.dtos.inputs.FuncionarioInputDTO;
import com.ifce.dailypoint.services.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping()
    public ResponseEntity<Void> criarFuncionario(@RequestBody FuncionarioInputDTO funcionarioInputDTO) {
        funcionarioService.create(funcionarioInputDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
