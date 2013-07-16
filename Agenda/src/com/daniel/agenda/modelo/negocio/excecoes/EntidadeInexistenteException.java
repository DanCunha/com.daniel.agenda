package com.daniel.agenda.modelo.negocio.excecoes;

public class EntidadeInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntidadeInexistenteException(String msg){
		super(msg);
	}

}
