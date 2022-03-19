package com.sceapi.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sceapi.model.Avaliacao;
//extends JpaRepository<Avaliacao, Long>
@Repository
public interface AvaliacaoRepository {
	
	List<Avaliacao> listar();
	Avaliacao buscar(Long id);
	Avaliacao salvar(Avaliacao avaliacao);
	void remover(Avaliacao avaliacao);

}
