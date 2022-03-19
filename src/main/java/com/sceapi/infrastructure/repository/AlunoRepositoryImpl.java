package com.sceapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.sceapi.model.Aluno;
import com.sceapi.repository.AlunoRepository;

@Component
public class AlunoRepositoryImpl implements AlunoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Aluno> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Aluno", Aluno.class).getResultList();
	}

	@Override
	public Aluno buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Aluno.class, id);
	}

	@Transactional
	@Override
	public Aluno salvar(Aluno aluno) {
		// TODO Auto-generated method stub
		return manager.merge(aluno);
	}

	@Transactional
	@Override
	public void remover(Aluno aluno) {
		// TODO Auto-generated method stub
		aluno = buscar(aluno.getId());
		manager.remove(aluno);
	}
	
	
}
