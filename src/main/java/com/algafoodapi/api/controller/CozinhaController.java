package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import com.algafoodapi.api.model.Cozinha;
import com.algafoodapi.api.repository.CozinhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cozinha", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public Optional<Cozinha> buscar(@PathVariable Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId);
    }
}
