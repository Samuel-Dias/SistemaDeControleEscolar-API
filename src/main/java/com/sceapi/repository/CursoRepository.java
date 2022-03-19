package com.sceapi.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sceapi.model.Curso;
//extends JpaRepository<Curso, Long>
@Repository
public interface CursoRepository {
	
	List<Curso> listar();
	Curso buscar(Long id);
	Curso salvar(Curso curso);
	void remover(Curso curso);

}
