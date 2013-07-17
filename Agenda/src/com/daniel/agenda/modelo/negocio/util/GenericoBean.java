package com.daniel.agenda.modelo.negocio.util;


import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.daniel.agenda.modelo.persistencia.enums.TipoOperacaoBeanEnum;

public abstract class GenericoBean {

	private TipoOperacaoBeanEnum tipoOperacao;
	private Boolean isExibirLista;
	private Long entityIdExclusao;

	/**
	 * A constante isModoDebug serve para definir se o templateLista incluirá o
	 * id dos objetos da lista ou não.
	 * 
	 * isModoDebug = true. Mostra o entityId. isModoDebug = false. Esconde o
	 * entityId.
	 * 
	 */
	public final Boolean isModoDebug = true;

	public void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TipoOperacaoBeanEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoBeanEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Boolean getIsExibirLista() {
		return isExibirLista;
	}

	public void setIsExibirLista(Boolean isExibirLista) {
		this.isExibirLista = isExibirLista;
	}

	public Boolean getIsModoDebug() {
		return isModoDebug;
	}

	public Long getEntityIdExclusao() {
		return entityIdExclusao;
	}

	public void setEntityIdExclusao(Long entityIdExclusao) {
		this.entityIdExclusao = entityIdExclusao;
	}

	public abstract void inicializar();
}
