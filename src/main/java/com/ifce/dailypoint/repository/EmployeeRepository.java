package com.ifce.dailypoint.repository;

import java.util.List;

import com.ifce.dailypoint.entities.Enterprise;
import com.ifce.dailypoint.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    Employee findByCpf(String cpf);

    List<Employee> findByEnterprise(Enterprise enterprise);
}
