package com.sceapi.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sceapi.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Double> {

}
