package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.exception.EntidadeEmUsoException;
import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Estado;
import com.algafoodapi.api.repository.EstadoRepository;
import com.algafoodapi.api.service.EstadoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estado", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

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

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado){
        try {
            estado = estadoService.adicionar(estado);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(estado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                .body(e.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId,
			@RequestBody Estado estado) {
		try {
			Optional<Estado> estadoOptional = estadoRepository.findById(estadoId);
            
			if (!estadoOptional.isEmpty()) {
                Estado estadoAtual = estadoOptional.get();
			
				BeanUtils.copyProperties(estado, estadoAtual, "id");
				
				estadoAtual = estadoService.alterar(estadoAtual);
				return ResponseEntity.ok(estadoAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId){
       try{
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
       
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
    
        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
    }
    
}
