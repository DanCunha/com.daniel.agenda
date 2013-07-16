package com.daniel.agenda.modelo.persistencia.dao;

import java.util.Collection;

import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.EntityIdNuloException;
import com.daniel.agenda.modelo.negocio.excecoes.MultiplosResultadosParaRecuperarObjetoException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.persistencia.entidades.ValueObject;



public interface IDataAccessObject {

	/**
	 * Recuperar o session a ser utilizado pelo dao. <br>
	 * Quando utilizamos o entityManager, estamos utilizando o Java Persistence
	 * API - JPA. Neste caso, implementada pelo Hibernate
	 * 
	 * @return
	 */
	// public EntityManagerFactory getEntityManager();

	/**
	 * @throws EntidadeInexistenteException 
	 * Buscar todas as ocorrencias.
	 * 
	 * @param entityClass
	 *            O nome da classe mapeada.
	 * @return Collection
	 * @throws  
	 */
	@SuppressWarnings("rawtypes")
	public Collection buscarTodos(Class entityClass) throws EntidadeInexistenteException;

	/**
	 * Este metodo recupera um objeto (ValueObject) atraves de um
	 * ValueObject.getEntityId.
	 * 
	 * @param vo
	 *            Value object to find
	 * @return ValueObject
	 * @throws MultiplosResultadosParaRecuperarObjetoException 
	 * @throws EntityIdNuloException 
	 */
	public ValueObject recuperarObjeto(ValueObject vo)
			throws ObjetoNaoEncontradoException, EntityIdNuloException, MultiplosResultadosParaRecuperarObjetoException;

	/**
	 * Este metodo recupera um objeto (ValueObject) atraves do seu entityId.
	 * 
	 * @param id
	 *            Long
	 * @param voVazio
	 *            - é preciso que se passe o vo que retornará com os valores do
	 *            banco, pois sem isso, o sistema não tem como saber de qual
	 *            entidade você está se referindo
	 * @return
	 * @throws ObjetoNaoEncontradoException
	 * @throws MultiplosResultadosParaRecuperarObjetoException 
	 */
	public ValueObject recuperarObjeto(Long id, ValueObject voVazio)
			throws ObjetoNaoEncontradoException, MultiplosResultadosParaRecuperarObjetoException;

	/**
	 * Este metodo recupera uma coleção de objetos (ValueObject) atraves de um
	 * comando SQL dos tipos NATIVE, NAMED ou JPAQL.
	 * 
	 * @param query
	 * @param queryType
	 * @param args
	 * @return Collection
	 * @throws ObjetoNaoEncontradoException
	 */
	// @SuppressWarnings("rawtypes")
	// public Collection recuperarObjetosPorSQL(Query q);

	/**
	 * Este metodo recupera um único objeto (ValueObject) através de um comando
	 * HQL
	 * 
	 */
	public ValueObject recuperarObjetoPorHQL(String stringHQL, String[] params,
			Object[] valores)
			throws MultiplosResultadosParaRecuperarObjetoException,
			ObjetoNaoEncontradoException;

	/**
	 * Este metodo recupera uma coleção de objetos (ValueObject) atraves de um
	 * comando HQL
	 * 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public Collection recuperarObjetosPorHQL(String stringHQL, String[] params,
			Object[] valores);

	/**
	 * Este metodo remove (deleta) um objeto atraves do seu entityId.
	 * 
	 * @param vo
	 *            Value object to remove
	 */
	public void removerObjeto(ValueObject vo)
			throws ObjetoNaoEncontradoException, EntityIdNuloException;

	/**
	 * Este metodo salva (persiste) um objeto atraves de um ValueObject. Se o
	 * atributo entityId do VO passado como parametro for igual a "null", o
	 * metodo irah inserir, senao, realizar uma atualizacao.
	 * 
	 * @param vo
	 *            Value object to save
	 * @throws ObjetoNaoEncontradoException 
	 * @throws ObjetoExistenteException
	 */
	public void salvarObjeto(ValueObject vo) throws ObjetoNaoEncontradoException;

}
