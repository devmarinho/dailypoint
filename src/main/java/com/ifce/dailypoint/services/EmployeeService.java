package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.entities.Enterprise;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EnterpriseService enterpriseService;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee create(String name, String cpf, String address, String username) throws BusinessErrorException{
        Enterprise enterprise = enterpriseService.fetchValidByUsername(username);
        Employee employee = findExistingEmployee(cpf);
        employee.setAddress(address);
        employee.setCpf(cpf);
        employee.setName(name);
        employee.setEnterprise(enterprise);
        return save(employee);
    }

    public Employee findByValidCpf(final String cpf) throws BusinessErrorException{
        Employee employee = employeeRepository.findByCpf(cpf);
        if(employee == null)
            throw new BusinessErrorException(10, BusinessErrorEnum.EMPLOYEE_NOT_FOUND, "Empregado não encontrado");
        return employee;
    }

    public Employee findExistingEmployee(final String cpf) throws BusinessErrorException{
        Employee employee = employeeRepository.findByCpf(cpf);
        if(employee == null)
            return new Employee();
        throw new BusinessErrorException(12, BusinessErrorEnum.EMPLOYEE_ALREADY_EXISTS, "Empregado já existe");
    }
}
