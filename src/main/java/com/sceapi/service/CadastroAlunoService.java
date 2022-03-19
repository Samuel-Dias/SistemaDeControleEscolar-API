package com.sceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sceapi.model.Aluno;
import com.sceapi.repository.AlunoRepository;

@Service
public class CadastroAlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	/*
	public Aluno buscar(Long alunoId) {
		Aluno aluno = alunoRepository.getById(alunoId);
		return aluno;
	}
	
	public Aluno salvar(Aluno aluno) {
		aluno = alunoRepository.save(aluno);
		return aluno;
	}
	
	public void remover(Long alunoId) {
		alunoRepository.deleteById(alunoId);
	}
*/
}
