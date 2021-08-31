package com.algafoodapi.api.service;

import com.algafoodapi.api.exception.EntidadeEmUsoException;
import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Estado;
import com.algafoodapi.api.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado adicionar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado alterar(Estado estado){
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId){
        try{
            estadoRepository.deleteById(estadoId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de cozinha com o código %d.", estadoId)
            );
        }
        
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                String.format("Cozinha de código %d não pode ser removida. pois está em uso.", estadoId)
            );
        }
    }
}
