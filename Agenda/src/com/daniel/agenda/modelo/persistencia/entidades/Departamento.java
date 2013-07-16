package com.daniel.agenda.modelo.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Departamento extends ValueObject{
	
	private static final long serialVersionUID = 7502300473119597700L;

	@Column(name = "nome")
	private String nome_Departamento;

	private int cd_Departamento;

	public String getNome_Departamento() {
		return nome_Departamento;
	}

	public void setNome_Departamento(String nome_Departamento) {
		this.nome_Departamento = nome_Departamento;
	}

	public int getCd_Departamento() {
		return cd_Departamento;
	}

	public void setCd_Departamento(int cd_Departamento) {
		this.cd_Departamento = cd_Departamento;
	}
	
	
	
	

}
