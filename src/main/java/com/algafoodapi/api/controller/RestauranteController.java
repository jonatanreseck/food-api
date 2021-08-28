package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Restaurante;
import com.algafoodapi.api.repository.RestauranteRepository;
import com.algafoodapi.api.service.RestauranteSerice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurante", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteSerice restauranteSerice;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Optional<Restaurante> buscar(@PathVariable Long restauranteId){
        return restauranteRepository.findById(restauranteId);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try {
            restaurante = restauranteSerice.adicionar(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            ResponseEntity.badRequest()
                .body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
    }

}
