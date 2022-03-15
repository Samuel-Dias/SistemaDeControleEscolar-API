package com.sceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sceapi.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Double> {

}
