package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.EnterpriseInputDTO;
import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.entities.Enterprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {
    
    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EmployeeService employeeService;

    public void createEmployee(EmployeeInputDTO employeeInputDTO){
        Enterprise enterprise = enterpriseService.findByCnpj(employeeInputDTO.getEnterpriseCnpj());
        employeeService.create(employeeInputDTO.getNome(), employeeInputDTO.getCpf(), 
        employeeInputDTO.getEndereco(), enterprise);
    }

    public void createEnterprise(EnterpriseInputDTO enterpriseInputDTO){
        enterpriseService.create(enterpriseInputDTO.getAddress(),enterpriseInputDTO.getCnpj(),
        enterpriseInputDTO.getName(),enterpriseInputDTO.getEmail());
    }

}
