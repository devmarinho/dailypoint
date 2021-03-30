package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.entities.Enterprise;
import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee create(String name, String cpf, String address, Enterprise enterprise){
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setCpf(cpf);
        employee.setName(name);
        employee.setEnterprise(enterprise);
        return save(employee);
    }


}
