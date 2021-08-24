package com.algafoodapi.api.repository;

import com.algafoodapi.api.model.Cozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    
}