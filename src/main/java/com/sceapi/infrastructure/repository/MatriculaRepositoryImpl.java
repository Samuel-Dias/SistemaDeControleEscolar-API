package com.sceapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.sceapi.model.Matricula;
import com.sceapi.repository.MatriculaRepository;

@Component
public class MatriculaRepositoryImpl implements MatriculaRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Matricula> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Matricula", Matricula.class).getResultList();
	}

	@Override
	public Matricula buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Matricula.class, id);
	}

	@Transactional
	@Override
	public Matricula salvar(Matricula matricula) {
		// TODO Auto-generated method stub
		return manager.merge(matricula);
	}

	@Transactional
	@Override
	public void remover(Matricula matricula) {
		// TODO Auto-generated method stub
		matricula = buscar(matricula.getId());
		manager.remove(matricula);
	}
	
	
}
