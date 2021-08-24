package com.algafoodapi.api.repository;

import com.algafoodapi.api.model.FormaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    
}
