package com.daniel.agenda.modelo.negocio.excecoes;

public class OperacaoInvalidaException extends Exception {
	private static final long serialVersionUID = 6294973059580277764L;

	public OperacaoInvalidaException (String msg){
		super(msg);
	}

}
