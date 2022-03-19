package com.sceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sceapi.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
