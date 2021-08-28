package com.algafoodapi.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Restaurante;
import org.springframework.stereotype.Service;
import com.algafoodapi.api.repository.RestauranteRepository;
import com.algafoodapi.api.model.Cozinha;
import com.algafoodapi.api.repository.CozinhaRepository;

@Service
public class RestauranteSerice {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;


    public Restaurante adicionar(Restaurante restaurante){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(restaurante.getId());
        
        if(cozinha.get() == null){
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe cadastro de cozinha com código %d", restaurante.getCozinha().getId())
            );
        }

        restaurante.setCozinha(cozinha.get());

        return restauranteRepository.save(restaurante);
    }

}
