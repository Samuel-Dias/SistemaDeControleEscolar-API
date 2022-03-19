package com.sceapi.api.controller;

import java.util.List;

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

import com.sceapi.model.Avaliacao;
import com.sceapi.repository.AvaliacaoRepository;

@RestController
@RequestMapping(value = "/avaliacaos")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Avaliacao> listar() {
		return avaliacaoRepository.listar();
	}
	
	@GetMapping("/{avaliacaoId}")
	public ResponseEntity<Avaliacao> buscar(@PathVariable Long avaliacaoId) {
		
		Avaliacao avaliacao = avaliacaoRepository.buscar(avaliacaoId);
		
		if (avaliacao != null) {
			return ResponseEntity.ok(avaliacao);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Avaliacao adicionar(@RequestBody Avaliacao avaliacao) {
		return avaliacaoRepository.salvar(avaliacao);
	}
	
	@PutMapping("/{avaliacaoId}")
	public ResponseEntity<Avaliacao> atualizar(@PathVariable Long avaliacaoId, @RequestBody Avaliacao avaliacao) {
		Avaliacao avaliacaoAtual = avaliacaoRepository.buscar(avaliacaoId);
		
		if (avaliacaoAtual != null) {
			BeanUtils.copyProperties(avaliacao, avaliacaoAtual, "id");
			avaliacaoAtual = avaliacaoRepository.salvar(avaliacaoAtual);
			return ResponseEntity.ok(avaliacaoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{avaliacaoId}")
	public ResponseEntity<Avaliacao> remover(@PathVariable Long avaliacaoId) {
		try {
			Avaliacao avaliacao = avaliacaoRepository.buscar(avaliacaoId);
			
			if (avaliacao != null) {
				avaliacaoRepository.remover(avaliacao);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
