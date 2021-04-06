package com.ifce.dailypoint.repository;

import com.ifce.dailypoint.entities.Enterprise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>{
    Enterprise findByCnpj(String cnpj);

    Enterprise findByUsername(String username);
}
