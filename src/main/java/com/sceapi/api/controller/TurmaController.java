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

import com.sceapi.model.Turma;
import com.sceapi.repository.TurmaRepository;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Turma> listar() {
		return turmaRepository.listar();
	}
	
	@GetMapping("/{turmaId}")
	public ResponseEntity<Turma> buscar(@PathVariable Long turmaId) {
		
		Turma turma = turmaRepository.buscar(turmaId);
		
		if (turma != null) {
			return ResponseEntity.ok(turma);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Turma adicionar(@RequestBody Turma turma) {
		return turmaRepository.salvar(turma);
	}
	
	@PutMapping("/{turmaId}")
	public ResponseEntity<Turma> atualizar(@PathVariable Long turmaId, @RequestBody Turma turma) {
		Turma turmaAtual = turmaRepository.buscar(turmaId);
		
		if (turmaAtual != null) {
			BeanUtils.copyProperties(turma, turmaAtual, "id");
			turmaAtual = turmaRepository.salvar(turmaAtual);
			return ResponseEntity.ok(turmaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{turmaId}")
	public ResponseEntity<Turma> remover(@PathVariable Long turmaId) {
		try {
			Turma turma = turmaRepository.buscar(turmaId);
			
			if (turma != null) {
				turmaRepository.remover(turma);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
