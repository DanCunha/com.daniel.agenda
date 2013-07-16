package com.daniel.agenda.modelo.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fone extends ValueObject{

	private static final long serialVersionUID = 3015570498199129179L;
	
	@Column
	private int nr_fone;

	public int getNr_fone() {
		return nr_fone;
	}

	public void setNr_fone(int nr_fone) {
		this.nr_fone = nr_fone;
	}

	
}
