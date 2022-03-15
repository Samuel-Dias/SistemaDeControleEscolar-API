package com.sceapi.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Double id;
	
	@Column //(nullable = false)
	private Date dataMatricula;
	
	@Column(nullable = false)
	private Integer prestacoesPagamento;
	
	@OneToOne(mappedBy = "matricula")
    private Aluno aluno;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Integer getPrestacoesPagamento() {
		return prestacoesPagamento;
	}

	public void setPrestacoesPagamento(Integer prestacoesPagamento) {
		this.prestacoesPagamento = prestacoesPagamento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return Objects.equals(id, other.id);
	}
	

}
