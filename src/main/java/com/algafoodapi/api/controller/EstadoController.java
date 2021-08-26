package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.model.Estado;
import com.algafoodapi.api.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estado", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Optional<Estado> buscar(@PathVariable Long estadoId){
        return estadoRepository.findById(estadoId);
    }
    
}
