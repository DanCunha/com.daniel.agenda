package com.daniel.agenda.modelo.negocio.util.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.EntityIdNuloException;
import com.daniel.agenda.modelo.negocio.excecoes.MultiplosResultadosParaRecuperarObjetoException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.negocio.util.IGenericoServico;
import com.daniel.agenda.modelo.persistencia.dao.IDataAccessObject;
import com.daniel.agenda.modelo.persistencia.entidades.ValueObject;




public abstract class GenericoServico implements IGenericoServico {

	@Autowired
	IDataAccessObject dao;

	@SuppressWarnings("rawtypes")
	@Override
	public Collection buscarTodos(Class entityClass) throws EntidadeInexistenteException {
		return this.dao.buscarTodos(entityClass);
	}

	@Override
	public ValueObject recuperarObjeto(ValueObject vo)
			throws ObjetoNaoEncontradoException, EntityIdNuloException, MultiplosResultadosParaRecuperarObjetoException {
		return this.dao.recuperarObjeto(vo);
	}

	@Override
	public ValueObject recuperarObjeto(Long id, ValueObject voVazio)
			throws ObjetoNaoEncontradoException, MultiplosResultadosParaRecuperarObjetoException {
		return this.dao.recuperarObjeto(id, voVazio);
	}

	@Override
	public ValueObject recuperarObjetoPorHQL(String stringHQL, String[] params,
			Object[] valores)
			throws MultiplosResultadosParaRecuperarObjetoException,
			ObjetoNaoEncontradoException {
		return this.dao.recuperarObjetoPorHQL(stringHQL, params, valores);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Collection recuperarObjetosPorHQL(String stringHQL, String[] params,
			Object[] valores) {
		return this.dao.recuperarObjetosPorHQL(stringHQL, params, valores);
	}

	@Override
	public void removerObjeto(ValueObject vo)
			throws ObjetoNaoEncontradoException, EntityIdNuloException {
		this.dao.removerObjeto(vo);
	}

	@Override
	public void salvarObjeto(ValueObject vo) throws ObjetoNaoEncontradoException {
		this.dao.salvarObjeto(vo);
	}
	
}
