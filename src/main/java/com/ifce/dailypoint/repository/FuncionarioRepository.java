package com.ifce.dailypoint.repository;

import com.ifce.dailypoint.entities.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}
