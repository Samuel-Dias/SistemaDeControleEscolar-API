package com.sceapi.repository; 

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sceapi.model.Matricula;
//extends JpaRepository<Matricula, Long>
@Repository
public interface MatriculaRepository {
	
	List<Matricula> listar();
	Matricula buscar(Long id);
	Matricula salvar(Matricula matricula);
	void remover(Matricula matricula);

}
