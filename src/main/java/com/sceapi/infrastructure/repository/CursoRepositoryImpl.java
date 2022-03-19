package com.sceapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.sceapi.model.Curso;
import com.sceapi.repository.CursoRepository;

@Component
public class CursoRepositoryImpl implements CursoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Curso> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Curso", Curso.class).getResultList();
	}

	@Override
	public Curso buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Curso.class, id);
	}

	@Transactional
	@Override
	public Curso salvar(Curso curso) {
		// TODO Auto-generated method stub
		return manager.merge(curso);
	}

	@Transactional
	@Override
	public void remover(Curso curso) {
		// TODO Auto-generated method stub
		curso = buscar(curso.getId());
		manager.remove(curso);
	}
	
	
}
