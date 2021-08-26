package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.model.Cozinha;
import com.algafoodapi.api.repository.CozinhaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(
        @PathVariable Long cozinhaId,
        @RequestBody Cozinha cozinha
    ){
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
       
        if(!cozinhaAtual.isEmpty()){
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            cozinhaRepository.save(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaAtual.get());
        }

        return ResponseEntity.notFound().build();        
    } 

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
       try{
            Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
            if(!cozinha.isEmpty()){
                cozinhaRepository.delete(cozinha.get());
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
       } catch(DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
        
    } 
}
