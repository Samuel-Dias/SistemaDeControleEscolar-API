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

import com.sceapi.exception.CampoCPFNaoPodeSerNuloException;
import com.sceapi.exception.CampoDataNascimentoNaoPodeSerNuloException;
import com.sceapi.exception.CampoNomeNaoPodeSerNuloException;
import com.sceapi.model.Aluno;
import com.sceapi.repository.AlunoRepository;
import com.sceapi.service.CadastroAlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CadastroAlunoService alunoService;
	
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
	@ResponseStatus //(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Aluno aluno) {
		try {
			aluno = alunoService.salvar(aluno);
			return ResponseEntity.status(201).body(aluno);
		} catch (CampoNomeNaoPodeSerNuloException | CampoDataNascimentoNaoPodeSerNuloException | CampoCPFNaoPodeSerNuloException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		
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
