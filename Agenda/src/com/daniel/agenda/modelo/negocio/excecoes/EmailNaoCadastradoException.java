package com.daniel.agenda.modelo.negocio.excecoes;

public class EmailNaoCadastradoException extends Exception {
	
	private static final long serialVersionUID = -7982211941946797063L;

	public EmailNaoCadastradoException(String msg) {
		super(msg);
	}
}
