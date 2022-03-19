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

import com.sceapi.model.Matricula;
import com.sceapi.repository.MatriculaRepository;

@RestController
@RequestMapping(value = "/matriculas")
public class MatriculaController {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Matricula> listar() {
		return matriculaRepository.listar();
	}
	
	@GetMapping("/{matriculaId}")
	public ResponseEntity<Matricula> buscar(@PathVariable Long matriculaId) {
		
		Matricula matricula = matriculaRepository.buscar(matriculaId);
		
		if (matricula != null) {
			return ResponseEntity.ok(matricula);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Matricula adicionar(@RequestBody Matricula matricula) {
		return matriculaRepository.salvar(matricula);
	}
	
	@PutMapping("/{matriculaId}")
	public ResponseEntity<Matricula> atualizar(@PathVariable Long matriculaId, @RequestBody Matricula matricula) {
		Matricula matriculaAtual = matriculaRepository.buscar(matriculaId);
		
		if (matriculaAtual != null) {
			BeanUtils.copyProperties(matricula, matriculaAtual, "id");
			matriculaAtual = matriculaRepository.salvar(matriculaAtual);
			return ResponseEntity.ok(matriculaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{matriculaId}")
	public ResponseEntity<Matricula> remover(@PathVariable Long matriculaId) {
		try {
			Matricula matricula = matriculaRepository.buscar(matriculaId);
			
			if (matricula != null) {
				matriculaRepository.remover(matricula);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
