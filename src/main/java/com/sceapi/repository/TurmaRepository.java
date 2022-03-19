package com.sceapi.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sceapi.model.Turma;
//extends JpaRepository<Turma, Long>
@Repository
public interface TurmaRepository {
	
	List<Turma> listar();
	Turma buscar(Long id);
	Turma salvar(Turma turma);
	void remover(Turma turma);

}
