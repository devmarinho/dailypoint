package com.ifce.dailypoint.controller;

import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.entities.Token;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.services.CadastroService;
import com.ifce.dailypoint.services.EmployeeService;
import com.ifce.dailypoint.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<Void> criarEmployee(@RequestBody @Validated EmployeeInputDTO employeeInputDTO,
    @RequestHeader(name="Authorization") String authorization) throws BusinessErrorException {
        Token token = tokenService.fetchValidTokenByIdToken(authorization);
        cadastroService.createEmployee(employeeInputDTO, token.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
