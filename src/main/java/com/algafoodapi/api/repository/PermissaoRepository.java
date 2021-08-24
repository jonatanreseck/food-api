package com.algafoodapi.api.repository;

import com.algafoodapi.api.model.Permissao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
}
