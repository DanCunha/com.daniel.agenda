package com.daniel.agenda.controle.mb;

import javax.faces.event.ActionEvent;

import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;

public interface ICRUDBean {
	
	public abstract void doPesquisar(ActionEvent event) throws EntidadeInexistenteException;

	public abstract String showNovo() throws EntidadeInexistenteException;

	public abstract String showEditar() throws EntidadeInexistenteException;

	public abstract String showDetalhar() throws EntidadeInexistenteException;

	public abstract void doExcluir(ActionEvent event);

	public abstract String doSalvar() throws ObjetoNaoEncontradoException, EntidadeInexistenteException;

	public abstract String doSalvarESair() throws ObjetoNaoEncontradoException, EntidadeInexistenteException;

	public abstract String doCancelar() throws ObjetoNaoEncontradoException, EntidadeInexistenteException;

}
