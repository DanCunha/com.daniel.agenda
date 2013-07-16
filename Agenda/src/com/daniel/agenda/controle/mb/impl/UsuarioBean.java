package com.daniel.agenda.controle.mb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.agenda.controle.mb.ICRUDBean;
import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.negocio.servicos.IUsuarioServico;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;
import com.daniel.agenda.modelo.persistencia.enums.TipoOperacaoBeanEnum;

@Component
@ManagedBean
@ViewScoped
public class UsuarioBean implements ICRUDBean{
	
	@Autowired
	private IUsuarioServico usuarioServico;
	private Usuario usuario;
	private List<Usuario> lista;
	private Boolean isExibirLista;
	private TipoOperacaoBeanEnum tipoOperacao;
	
	@PostConstruct
	public void inicializar() {

		lista = new ArrayList<Usuario>();

		usuario = new Usuario();

		setIsExibirLista(false);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPesquisar(ActionEvent event)
			throws EntidadeInexistenteException {

		lista = (List<Usuario>) usuarioServico.buscarTodos(Usuario.class);
		
	}

	@Override
	public String showNovo() throws EntidadeInexistenteException {
		String forward = "formUsuario";

		usuario = new Usuario();

		setTipoOperacao(TipoOperacaoBeanEnum.NOVO);

		prepararListaUsuario();

		return forward;
	}

	@Override
	public String showEditar() throws EntidadeInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showDetalhar() throws EntidadeInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doExcluir(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String doSalvar() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doSalvarESair() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doCancelar() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void prepararListaUsuario() throws EntidadeInexistenteException{
		if (lista.size() == 0) {
			lista = (List<Usuario>) usuarioServico.buscarTodos(Usuario.class);
		}
	}

	public Boolean getIsExibirLista() {
		return isExibirLista;
	}

	public void setIsExibirLista(Boolean isExibirLista) {
		this.isExibirLista = isExibirLista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public TipoOperacaoBeanEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoBeanEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	

}
