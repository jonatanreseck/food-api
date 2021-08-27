package com.algafoodapi.api.service;

import java.util.Optional;

import com.algafoodapi.api.exception.EntidadeEmUsoException;
import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Cozinha;
import com.algafoodapi.api.repository.CozinhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaServive {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try{
            cozinhaRepository.deleteById(cozinhaId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de cozinha com o código %d.", cozinhaId)
            );
        }
        
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                String.format("Cozinha de código %d não pode ser removida. pois está em uso.", cozinhaId)
            );
        }
    }

}
