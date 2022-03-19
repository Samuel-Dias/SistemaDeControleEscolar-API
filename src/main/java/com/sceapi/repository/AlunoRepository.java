package com.sceapi.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sceapi.model.Aluno;
//extends JpaRepository<Aluno, Long>

@Repository
public interface AlunoRepository  {
	
	List<Aluno> listar();
	Aluno buscar(Long id);
	Aluno salvar(Aluno aluno);
	void remover(Aluno aluno);

}
