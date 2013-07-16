package com.daniel.agenda.modelo.negocio.excecoes;

public class FormaPagamentoNula extends Exception {

	private static final long serialVersionUID = 3333205602404123187L;

	public FormaPagamentoNula(String msg) {
		super(msg);
	}
	
}
