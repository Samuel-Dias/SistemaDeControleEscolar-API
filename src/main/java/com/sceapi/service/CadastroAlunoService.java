package com.sceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sceapi.exception.CampoNomeNaoPodeSerNuloException;
import com.sceapi.model.Aluno;
import com.sceapi.repository.AlunoRepository;

@Service
public class CadastroAlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno salvar(Aluno aluno) {
		
		if(aluno.getNome() == null) {
			throw new CampoNomeNaoPodeSerNuloException("Campo 'Nome' não pode ser nulo!!");
		}
		
		aluno = alunoRepository.salvar(aluno);
	
		return aluno;
	}
}
