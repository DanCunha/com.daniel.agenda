package com.daniel.agenda.modelo.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate3.HibernateQueryException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.EntityIdNuloException;
import com.daniel.agenda.modelo.negocio.excecoes.MultiplosResultadosParaRecuperarObjetoException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.persistencia.dao.IDataAccessObject;
import com.daniel.agenda.modelo.persistencia.entidades.ValueObject;

@Repository("dao")
public class DataAccessObjectHibernate implements IDataAccessObject {

	// private static DataAccessObjectHibernate daoSingleton;
	//
	// public static IDataAccessObject getInstance() {
	// if (daoSingleton == null) {
	// daoSingleton = new DataAccessObjectHibernate();
	// }
	// return daoSingleton;
	// }

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Collection buscarTodos(Class entityClass)
			throws EntidadeInexistenteException {
		Collection resultado = null;
		String hql = "from " + entityClass.getSimpleName()
				+ " as x order by x.entityId";

		try {
			resultado = hibernateTemplate.find(hql);
		} catch (Exception e) {
			if (e instanceof HibernateQueryException) {
				throw new EntidadeInexistenteException(
						"Esta Classe não está disponível em nossa base de dados, entre em contato com o administrador do Sistema");
			}
		}
		return resultado;
	}

	@Override
	public ValueObject recuperarObjeto(ValueObject vo)
			throws EntityIdNuloException, ObjetoNaoEncontradoException,MultiplosResultadosParaRecuperarObjetoException {

		ValueObject resultado = null;

		if (vo.getEntityId() == null) {
			throw new EntityIdNuloException(
					"O ID deste objeto não existe, contate o Administrador do Sistema");
		}

		String hql = " from " + vo.getClass().getSimpleName() + " as x"
				+ " where x.entityId = " + vo.getEntityId();
		String[] params = {};
		Object[] valores = {};

		resultado = recuperarObjetoPorHQL(hql, params, valores);

		return resultado;
	}

	@Override
	public ValueObject recuperarObjeto(Long id, ValueObject voVazio)
			throws ObjetoNaoEncontradoException, MultiplosResultadosParaRecuperarObjetoException {

		ValueObject resultado = null;

		voVazio.setEntityId(id);

		String hql = "from " + voVazio.getClass().getSimpleName()
				+ " obj where obj.entityId = :id";

		String[] params = { "id" };
		Object[] valores = { id };

		resultado = recuperarObjetoPorHQL(hql, params, valores);
		return resultado;

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ValueObject recuperarObjetoPorHQL(String stringHQL, String[] params,
			Object[] valores) throws ObjetoNaoEncontradoException, MultiplosResultadosParaRecuperarObjetoException {

		ValueObject resultado = null;

		ArrayList<ValueObject> lista = new ArrayList<ValueObject>();
		lista = (ArrayList<ValueObject>) hibernateTemplate.findByNamedParam(stringHQL, params, valores);

		if (lista.size() == 0) {
			throw new ObjetoNaoEncontradoException(
					"Nenhum objeto foi encontrado.");
		} else if (lista.size() > 1) {
			throw new MultiplosResultadosParaRecuperarObjetoException(
					"Existe mais de um objeto que atende aos parâmetros passados. Se você espera mais de um objeto utilize recuperarObjetosPorHQL");
		} else {
			resultado = lista.get(0);
		}

		return resultado;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Collection recuperarObjetosPorHQL(String stringHQL, String[] params,
			Object[] valores) {
		return hibernateTemplate.findByNamedParam(stringHQL, params, valores);
	}

	@Override
	public void removerObjeto(ValueObject vo)
			throws ObjetoNaoEncontradoException, EntityIdNuloException {

		if (vo.getEntityId() == null) {
			throw new EntityIdNuloException("Não é possível excluir o objeto "
					+ vo + ", pois seu entityId está nulo.");
		}

		try {
			hibernateTemplate.delete(vo);
		} catch (Exception e) {
			if (e instanceof HibernateOptimisticLockingFailureException) {
				throw new ObjetoNaoEncontradoException(
						"...O OBJETO NÃO PODE SER ENCONTRADO");
			} else {
				e.printStackTrace();
			}
		}

	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void salvarObjeto(ValueObject vo) throws ObjetoNaoEncontradoException {
		try {
			hibernateTemplate.saveOrUpdate(vo);
		} catch (Exception e) {
			if (e instanceof HibernateOptimisticLockingFailureException) {
				throw new ObjetoNaoEncontradoException(
						"....ESTE ID NÃO PODE SER CADASTRADO NO BANCO, CONTATE O ADMINISTRADOR DO SISTEMA");
			} else {
				e.printStackTrace();
			}
		}


	}

}
