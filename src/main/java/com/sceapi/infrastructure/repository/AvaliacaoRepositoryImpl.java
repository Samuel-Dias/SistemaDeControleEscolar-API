package com.sceapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.sceapi.model.Avaliacao;
import com.sceapi.repository.AvaliacaoRepository;

@Component
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Avaliacao> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}

	@Override
	public Avaliacao buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Avaliacao.class, id);
	}

	@Transactional
	@Override
	public Avaliacao salvar(Avaliacao avaliacao) {
		// TODO Auto-generated method stub
		return manager.merge(avaliacao);
	}

	@Transactional
	@Override
	public void remover(Avaliacao avaliacao) {
		// TODO Auto-generated method stub
		avaliacao = buscar(avaliacao.getId());
		manager.remove(avaliacao);
	}
	
	
}
