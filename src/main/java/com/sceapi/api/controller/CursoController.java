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

import com.sceapi.model.Curso;
import com.sceapi.repository.CursoRepository;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listar() {
		return cursoRepository.listar();
	}
	
	@GetMapping("/{cursoId}")
	public ResponseEntity<Curso> buscar(@PathVariable Long cursoId) {
		
		Curso curso = cursoRepository.buscar(cursoId);
		
		if (curso != null) {
			return ResponseEntity.ok(curso);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Curso adicionar(@RequestBody Curso curso) {
		return cursoRepository.salvar(curso);
	}
	
	@PutMapping("/{cursoId}")
	public ResponseEntity<Curso> atualizar(@PathVariable Long cursoId, @RequestBody Curso curso) {
		Curso cursoAtual = cursoRepository.buscar(cursoId);
		
		if (cursoAtual != null) {
			BeanUtils.copyProperties(curso, cursoAtual, "id");
			cursoAtual = cursoRepository.salvar(cursoAtual);
			return ResponseEntity.ok(cursoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cursoId}")
	public ResponseEntity<Curso> remover(@PathVariable Long cursoId) {
		try {
			Curso curso = cursoRepository.buscar(cursoId);
			
			if (curso != null) {
				cursoRepository.remover(curso);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
