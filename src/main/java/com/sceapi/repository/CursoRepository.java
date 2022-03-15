package com.sceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sceapi.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Double> {

}
