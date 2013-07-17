package com.daniel.agenda.controle.mb.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.daniel.agenda.modelo.negocio.dominios.IAdministracaoDominio;
import com.daniel.agenda.modelo.negocio.servicos.IUsuarioServico;
import com.daniel.agenda.modelo.negocio.util.GenericoBean;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;
import com.daniel.agenda.modelo.persistencia.enums.TipoUsuarioEnum;


@Controller
@ManagedBean
@SessionScoped
public class LoginBean extends GenericoBean implements Serializable{
	
	private static final long serialVersionUID = -7415860251834955154L;

	@Autowired
	private IUsuarioServico usuarioServico;
	
	@Autowired
	private IAdministracaoDominio administracaoDominio;
	private Usuario usuarioLogado;
	private List<Usuario> listaUsuarios;
	private MenuModel menu;

	@PostConstruct
	public void inicializar() {
		usuarioLogado = new Usuario();
//		usuarioEsqueciMinhaSenha = new Usuario();
		menu = new DefaultMenuModel();
//		usuarioAlterarSenha = new UsuarioDTO();
		listaUsuarios = new ArrayList<Usuario>();

	}
	
	public String doLogin() {

		String forward = "home";
		try {
			
			System.out.println("Aqui Teste");
			administracaoDominio.popularBD();
			usuarioLogado = usuarioServico.autenticarUsuario(usuarioLogado);
			prepararMenu();
//			prepararPaineis();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
			
		} catch (Exception e) {
			forward = "login";
		}

		return forward;
	}
	
	public String showHome() {
		String forward = "home";

		System.out.println("....... show home");

		prepararMenu();
//		prepararPaineis();

		return forward;
	}
	
//	public void prepararPaineis() {
//		listaProdutosEmLeilao = (ArrayList<Produto>) leilaoDominio
//				.getProdutosPainelJogador();
//		listaProdutosArrematadosEPagos = (ArrayList<Produto>) leilaoDominio
//				.getMeusProdutosArrematadosEPagos(usuarioLogado);
//		listaProdutosAguardandoPagamento = (ArrayList<Produto>) leilaoDominio
//				.getMeusProdutosArrematadosAguardandoPagamento(usuarioLogado);
//	}

	
	public void prepararMenu() {

		menu = new DefaultMenuModel();

		if (usuarioLogado.getTipoUsuarioEnum() == TipoUsuarioEnum.ADMIN) {

			Submenu submenu = new Submenu();
			submenu.setLabel("Localização");

			MenuItem item = new MenuItem();
			item.setValue("Usuario");
			item.setUrl("/visao/usuarioVisao/listaUsuario.xhtml");
			submenu.getChildren().add(item);
			
			menu.addSubmenu(submenu);
		
		} else {

			Submenu submenu = new Submenu();
			submenu.setLabel("Leilao");

			menu.addSubmenu(submenu);

		}

		// criando um MenuItem dinamicamento chamando um Action!
		MenuItem item = new MenuItem();
		item.setValue("Home");
		item.setIcon("ui-icon ui-icon-home");
		// ao inves de usar setURL, utiliza-se o setAction!
		setAction(item, "loginBean.showHome");
		// setAjax = false eh que faz a diferenca, pois do contrário, o JSF nao
		// chama a Action
		item.setAjax(false);
		menu.addMenuItem(item);

	}

	public void setAction(MenuItem menuitem, String method_action) {
		FacesContext context = FacesContext.getCurrentInstance();
		MethodExpression action = context
				.getApplication()
				.getExpressionFactory()
				.createMethodExpression(context.getELContext(),
						"#{" + method_action + "}", null, new Class[0]);
		menuitem.setActionExpression(action);
	}

	public IUsuarioServico getUsuarioServico() {
		return usuarioServico;
	}

	public void setUsuarioServico(IUsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
	public MenuModel getMenu() {
		return menu;
	}

	public void setMenu(MenuModel menu) {
		this.menu = menu;
	}

}
