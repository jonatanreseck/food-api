package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.model.Restaurante;
import com.algafoodapi.api.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurante", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Optional<Restaurante> buscar(@PathVariable Long restauranteId){
        return restauranteRepository.findById(restauranteId);
    }

}
