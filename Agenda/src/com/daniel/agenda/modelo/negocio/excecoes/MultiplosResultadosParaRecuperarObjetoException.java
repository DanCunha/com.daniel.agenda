package com.daniel.agenda.modelo.negocio.excecoes;

/**
 * Esta exceção é lançada toda vez que um método do DAO que devá recuperar um
 * ÚNICO OBJETO possui no resultado do SQL, HQL ou Criteria mais de um objeto.
 * Em outras palavras, os métodos recuperarObjeto* devem possui parametros
 * suficiente para que a consulta sql seja capaz de trazer um e apenas um objeto
 * como retorno.
 * 
 * @author Rayfran Rocha
 * 
 */
public class MultiplosResultadosParaRecuperarObjetoException extends Exception {
	
	private static final long serialVersionUID = 3656588388742384749L;

	public MultiplosResultadosParaRecuperarObjetoException(String msg) {
		super(msg);
	}

}
