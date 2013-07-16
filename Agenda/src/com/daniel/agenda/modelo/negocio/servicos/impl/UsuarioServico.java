package com.daniel.agenda.modelo.negocio.servicos.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daniel.agenda.modelo.negocio.excecoes.EmailNaoCadastradoException;
import com.daniel.agenda.modelo.negocio.excecoes.MultiplosResultadosParaRecuperarObjetoException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.negocio.excecoes.UsuarioSenhaException;
import com.daniel.agenda.modelo.negocio.servicos.IUsuarioServico;
import com.daniel.agenda.modelo.negocio.util.impl.GenericoServico;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;



@Service("usuarioServico")
public class UsuarioServico extends GenericoServico implements IUsuarioServico {

	public Usuario autenticarUsuario(Usuario u) throws UsuarioSenhaException,
			MultiplosResultadosParaRecuperarObjetoException {

		Usuario resultado = null;

		String hql = "from Usuario u " + " where u.senha = :senha"
				+ " and u.login = :login";

		String[] params = { "senha", "login" };
		Object[] valores = { u.getSenha(), u.getLogin() };

		try {
			resultado = (Usuario) recuperarObjetoPorHQL(hql, params,
					valores);
		} catch (ObjetoNaoEncontradoException e) {
			throw new UsuarioSenhaException(
					"Não foi encontrado um usuário com este login e senha.");
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public int getQtdeUsuarioAdmin() {

		// String hql = "from Usuario u where u.tipoUsuarioEnum = :tipoUsuario";
		//
		// String[] params = { "tipoUsuario" };
		// Object[] valores = { TipoUsuarioEnum.ADMIN };

		String hql = "from Usuario ";

		String[] params = {};
		Object[] valores = {};

		List<Usuario> resultado = (List<Usuario>) recuperarObjetosPorHQL(hql,
				params, valores);

		return resultado.size();

	}

	public Usuario validarEmailCadastrado(Usuario u)
			throws EmailNaoCadastradoException,
			MultiplosResultadosParaRecuperarObjetoException {

		Usuario resultado = null;

		String hql = "from Usuario u where u.login = :login";

		String[] params = { "login" };
		Object[] valores = { u.getLogin() };

		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuario = (List<Usuario>) recuperarObjetosPorHQL(
				hql, params, valores);

		if (listaUsuario.size() == 0) {
			throw new EmailNaoCadastradoException(
					"Este email não está cadastrado.");
		} else if (listaUsuario.size() > 1) {
			throw new MultiplosResultadosParaRecuperarObjetoException(
					"Existem mais de um usuario com este email. Contate o administrador do sistema.");
		} else {
			resultado = listaUsuario.get(0);
		}

		return resultado;
	}

	public void enviarNovaSenha(Usuario u) throws EmailNaoCadastradoException,
			MultiplosResultadosParaRecuperarObjetoException, ObjetoNaoEncontradoException {

		u = validarEmailCadastrado(u);
		u.setSenha(getNovaSenha(u));
		salvarObjeto(u);

		System.out.println("...enviou nova senha = " + u.getSenha());

	}

	private String getNovaSenha(Usuario u) {

		String resultado = "123";

		if (u.getSenha().equals("123")) {
			resultado = "1234";
		}

		return resultado;

	}

}
