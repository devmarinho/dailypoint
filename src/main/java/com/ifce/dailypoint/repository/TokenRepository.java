package com.ifce.dailypoint.repository;

import com.ifce.dailypoint.entities.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long>{
    
    Token findByIdToken(String idToken);
    
}
