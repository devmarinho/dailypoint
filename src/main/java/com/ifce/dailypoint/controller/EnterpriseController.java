package com.ifce.dailypoint.controller;

import com.ifce.dailypoint.dtos.inputs.EnterpriseInputDTO;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.services.CadastroService;
import com.ifce.dailypoint.services.EnterpriseService;
import com.ifce.dailypoint.services.TokenService;
import com.ifce.dailypoint.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("enterprise")
public class EnterpriseController {
    
    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Void> criarEnterprise(@RequestBody EnterpriseInputDTO enterprise) throws BusinessErrorException {
        cadastroService.createEnterprise(enterprise);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value="/{cnpj}")
    public ResponseEntity<Void> editEnterprise(@PathVariable String cnpj, @RequestBody EnterpriseInputDTO enterprise,
    @RequestHeader(name="Authorization") String token) throws BusinessErrorException {
        tokenService.fetchValidTokenByIdToken(token);
        enterpriseService.editarEnterprise(enterprise, cnpj);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
