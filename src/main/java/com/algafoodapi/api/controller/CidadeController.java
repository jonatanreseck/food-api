package com.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import com.algafoodapi.api.exception.EntidadeEmUsoException;
import com.algafoodapi.api.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.api.model.Cidade;
import com.algafoodapi.api.repository.CidadeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		
		if (cidade != null) {
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cidade = cidadeRepository.save(cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(
        @PathVariable Long cidadeId,
		@RequestBody Cidade cidade
    ) {
		try {
			Optional<Cidade> cidadeOptional = cidadeRepository.findById(cidadeId);
			
			if (!cidadeOptional.isEmpty()) {
                Cidade cidadeAtual = cidadeOptional.get();
				BeanUtils.copyProperties(cidade, cidadeAtual, "id");
				
				cidadeAtual = cidadeRepository.save(cidadeAtual);
				return ResponseEntity.ok(cidadeAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
