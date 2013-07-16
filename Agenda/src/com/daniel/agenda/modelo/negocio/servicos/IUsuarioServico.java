package com.daniel.agenda.modelo.negocio.servicos;

import com.daniel.agenda.modelo.negocio.excecoes.EmailNaoCadastradoException;
import com.daniel.agenda.modelo.negocio.excecoes.MultiplosResultadosParaRecuperarObjetoException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.negocio.excecoes.UsuarioSenhaException;
import com.daniel.agenda.modelo.negocio.util.IGenericoServico;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;


public interface IUsuarioServico extends IGenericoServico {

	/**
	 * 
	 * Através de um objeto usuário, o sistema verificará se existe um usuario
	 * no sistema com o login e senha contidos no objeto passado. <br>
	 * <b> how-to </b> <br>
	 * 1. verificar no banco de existe um usuario com login e senha compativeis
	 * com o objeto passado por parametro.
	 * 
	 * <br>
	 * 2. se não for encontrado lançar a UsuarioSenhaException
	 * 
	 * <br>
	 * 3. se houver mais de um registro no banco para o usuario passado por
	 * parametro lançar MultiplosResultadosParaRecuperarObjetoException
	 * 
	 * @param u
	 * @return
	 * @throws UsuarioSenhaException
	 * @throws MultiplosResultadosParaRecuperarObjetoException
	 */
	public Usuario autenticarUsuario(Usuario u) throws UsuarioSenhaException,
			MultiplosResultadosParaRecuperarObjetoException;

	public Usuario validarEmailCadastrado(Usuario u)
			throws EmailNaoCadastradoException,
			MultiplosResultadosParaRecuperarObjetoException;

	public void enviarNovaSenha(Usuario u) throws EmailNaoCadastradoException,
			MultiplosResultadosParaRecuperarObjetoException, ObjetoNaoEncontradoException;

	public int getQtdeUsuarioAdmin();

}
