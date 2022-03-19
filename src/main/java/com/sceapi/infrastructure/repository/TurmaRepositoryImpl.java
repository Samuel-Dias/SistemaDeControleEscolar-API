package com.sceapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.sceapi.model.Turma;
import com.sceapi.repository.TurmaRepository;

@Component
public class TurmaRepositoryImpl implements TurmaRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Turma> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Turma", Turma.class).getResultList();
	}

	@Override
	public Turma buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Turma.class, id);
	}

	@Transactional
	@Override
	public Turma salvar(Turma turma) {
		// TODO Auto-generated method stub
		return manager.merge(turma);
	}

	@Transactional
	@Override
	public void remover(Turma turma) {
		// TODO Auto-generated method stub
		turma = buscar(turma.getId());
		manager.remove(turma);
	}
	
	
}
