package com.ifce.dailypoint.controller;

import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.services.CadastroService;
import com.ifce.dailypoint.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CadastroService cadastroService;
    
    @PostMapping
    public ResponseEntity<Void> criarEmployee(@RequestBody EmployeeInputDTO employeeInputDTO) {
        cadastroService.createEmployee(employeeInputDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
