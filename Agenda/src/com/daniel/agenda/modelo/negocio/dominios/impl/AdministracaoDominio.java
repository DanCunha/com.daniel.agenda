package com.daniel.agenda.modelo.negocio.dominios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.agenda.modelo.negocio.dominios.IAdministracaoDominio;
import com.daniel.agenda.modelo.negocio.servicos.IUsuarioServico;
import com.daniel.agenda.modelo.negocio.util.impl.GenericoServico;
import com.daniel.agenda.modelo.persistencia.entidades.Usuario;
import com.daniel.agenda.modelo.persistencia.enums.TipoUsuarioEnum;

@Service("administracaoDominio")
public class AdministracaoDominio extends GenericoServico implements
		IAdministracaoDominio {

//	@Autowired
//	private IEstadoServico estadoServico;
//	
	@Autowired
	private IUsuarioServico usuarioServico;
//	
//	@Autowired
//	private ICategoriaServico categoriaServico;

	public void popularBD() {

		System.out.println("verifica se o banco está populado.");

		int qtdeUsuariosCadastrados = usuarioServico.getQtdeUsuarioAdmin();

		System.out.println("qtdeUsuariosCadastrados:"+qtdeUsuariosCadastrados);
		
		if (qtdeUsuariosCadastrados == 0) {

			Usuario e1 = new Usuario();
			e1.setNome("Daniel");
			e1.setLogin("dan");
			e1.setSenha("123");
			e1.setTipoUsuarioEnum(TipoUsuarioEnum.ADMIN);
//			List<String> lista = new ArrayList<String>();
//			e1.setFone(fone)
			System.out.println("...instanciou o Usuario:"+e1);
			
			Usuario e2 = new Usuario();
			e2.setNome("Davi");
			e2.setLogin("davi");
			e2.setSenha("321");
			e2.setTipoUsuarioEnum(TipoUsuarioEnum.USUARIO_COMUM);
			System.out.println("...instanciou o Usuario:"+e2);

			Usuario e3 = new Usuario();
			e3.setNome("Samantha");
			e3.setLogin("sam");
			e3.setSenha("masa");
			e3.setTipoUsuarioEnum(TipoUsuarioEnum.USUARIO_COMUM);
			
//			Estado e4 = new Estado();
//			e4.setNome("Rio de Janeiro");
//			e4.setSigla("RJ");
//			
//			Estado e5 = new Estado();
//			e5.setNome("Santa Catarina");
//			e5.setSigla("SC");
//			
//			Estado e6 = new Estado();
//			e6.setNome("Espírito Santo");
//			e6.setSigla("ES");
//			
//			Estado e7 = new Estado();
//			e7.setNome("Roraima");
//			e7.setSigla("RR");
//			
//			Estado e8 = new Estado();
//			e8.setNome("Amapá");
//			e8.setSigla("AP");
//			
//			Estado e9 = new Estado();
//			e9.setNome("São Paulo");
//			e9.setSigla("SP");
//			
//			Estado e10 = new Estado();
//			e10.setNome("Minas Gerais");
//			e10.setSigla("MG");
//			
//			Estado e11 = new Estado();
//			e11.setNome("Tocantins");
//			e11.setSigla("TO");
//			
//			Estado e12 = new Estado();
//			e12.setNome("Mato Grosso");
//			e12.setSigla("MT");
//			
//			Estado e13 = new Estado();
//			e13.setNome("Paraná");
//			e13.setSigla("PR");
//			
//			Estado e14 = new Estado();
//			e14.setNome("Pernambuco");
//			e14.setSigla("PE");
//
//			Categoria c1 = new Categoria();
//			c1.setNome("Limpeza");
//
//			Categoria c2 = new Categoria();
//			c2.setNome("Eletrônico");
//
//			Usuario u1 = new Usuario();
//			u1.setLogin("login1");
//			u1.setSenha("123");
//			u1.setTipoUsuarioEnum(TipoUsuarioEnum.ADMIN);
//
//			Usuario u2 = new Usuario();
//			u2.setLogin("login2");
//			u2.setSenha("123");
//			u2.setTipoUsuarioEnum(TipoUsuarioEnum.USUARIO_COMUM);

			try {
				usuarioServico.salvarObjeto(e1);
				usuarioServico.salvarObjeto(e2);
				usuarioServico.salvarObjeto(e3);
		
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
