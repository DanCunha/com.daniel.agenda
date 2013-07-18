package com.daniel.agenda.controle.mb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.agenda.controle.mb.ICRUDBean;
import com.daniel.agenda.modelo.negocio.excecoes.EntidadeInexistenteException;
import com.daniel.agenda.modelo.negocio.excecoes.EntityIdNuloException;
import com.daniel.agenda.modelo.negocio.excecoes.ObjetoNaoEncontradoException;
import com.daniel.agenda.modelo.negocio.servicos.IUsuarioServico;
import com.daniel.agenda.modelo.negocio.util.FacesUtil;
import com.daniel.agenda.modelo.negocio.util.GenericoBean;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;
import com.daniel.agenda.modelo.persistencia.enums.TipoOperacaoBeanEnum;
import com.daniel.agenda.modelo.persistencia.enums.TipoUsuarioEnum;


@Component
@ManagedBean
@ViewScoped
public class UsuarioBean extends GenericoBean implements ICRUDBean{
	
	@Autowired
	private IUsuarioServico usuarioServico;
	private Usuario usuario;
	private List<Usuario> lista;
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
		setIsExibirLista(true);
		
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
		String forward = "formUsuario";

		Long entityId = new Long(FacesUtil.getRequestParameter("entityId"));
		usuario = (Usuario) FacesUtil.getObjeto(lista, entityId);

		prepararListaUsuario();

		setTipoOperacao(TipoOperacaoBeanEnum.EDITANDO);

		return forward;
	}

	@Override
	public String showDetalhar() throws EntidadeInexistenteException {
		String forward = "formUsuario";

		Long entityId = new Long(FacesUtil.getRequestParameter("entityId"));
		usuario = (Usuario) FacesUtil.getObjeto(lista, entityId);

		prepararListaUsuario();

		setTipoOperacao(TipoOperacaoBeanEnum.DETALHANDO);

		return forward;
	}

	@Override
	public void doExcluir(ActionEvent event) {
		try {

			usuario = (Usuario) FacesUtil.getObjeto(lista,
					getEntityIdExclusao());

			usuarioServico.removerObjeto(usuario);
			
			addMessage(FacesMessage.SEVERITY_INFO, null, "O funcionario "+usuario.getNome()+" foi removido com sucesso!");

			doPesquisar(null);

		} catch (ObjetoNaoEncontradoException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Atenção", "Produto não encontrado!");
		} catch (EntityIdNuloException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Atenção", "O ID do Produto está nulo, contate o Administrador do Sistema!");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Atenção", e.getMessage());
		}
		
	}

	@Override
	public String doSalvar() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		String forward = "#";

		usuario.setTipoUsuarioEnum(TipoUsuarioEnum.USUARIO_COMUM);
		usuarioServico.salvarObjeto(usuario);

		doPesquisar(null);
		
		addMessage(FacesMessage.SEVERITY_INFO, null, "O produto "+usuario.getNome()+" foi salvo com sucesso!");

		return forward;
	}

	@Override
	public String doSalvarESair() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		String forward = "listaProduto";

		usuario.setTipoUsuarioEnum(TipoUsuarioEnum.USUARIO_COMUM);
		usuarioServico.salvarObjeto(usuario);

		doPesquisar(null);
		
		addMessage(FacesMessage.SEVERITY_INFO, null, "O produto "+usuario.getNome()+" foi salvo com sucesso!");

		return forward;
	}

	@Override
	public String doCancelar() throws ObjetoNaoEncontradoException,
			EntidadeInexistenteException {
		String forward = "listaUsuario";

		doPesquisar(null);

		return forward;
	}
	
	@SuppressWarnings("unchecked")
	public void prepararListaUsuario() throws EntidadeInexistenteException{
		if (lista.size() == 0) {
			lista = (List<Usuario>) usuarioServico.buscarTodos(Usuario.class);
		}
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
