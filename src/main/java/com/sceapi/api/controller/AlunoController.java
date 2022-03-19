package com.sceapi.api.controller;

import java.util.List;
import java.util.Optional;

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

//import com.foodapi.model.Cozinha;
import com.sceapi.exception.EntidadeNaoEncontradaException;
import com.sceapi.model.Aluno;
import com.sceapi.repository.AlunoRepository;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Aluno> listar() {
		return alunoRepository.listar();
	}
	
	@GetMapping("/{alunoId}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId) {
		
		Aluno aluno = alunoRepository.buscar(alunoId);
		
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno adicionar(@RequestBody Aluno aluno) {
		return alunoRepository.salvar(aluno);
	}
	
	@PutMapping("/{alunoId}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long alunoId, @RequestBody Aluno aluno) {
		Aluno alunoAtual = alunoRepository.buscar(alunoId);
		
		if (alunoAtual != null) {
			BeanUtils.copyProperties(aluno, alunoAtual, "id");
			alunoAtual = alunoRepository.salvar(alunoAtual);
			return ResponseEntity.ok(alunoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{alunoId}")
	public ResponseEntity<Aluno> remover(@PathVariable Long alunoId) {
		try {
			Aluno aluno = alunoRepository.buscar(alunoId);
			
			if (aluno != null) {
				alunoRepository.remover(aluno);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
