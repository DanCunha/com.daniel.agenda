package com.daniel.agenda.modelo.negocio.excecoes;

public class EntidadeExistenteException extends Exception {

	private static final long serialVersionUID = -4967940379806496290L;

	/**
	 * O entityId da entidade já existente no cadastro.
	 */
	private Long entityId;
	
	private String nomeEntidade;
	
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padrão de entidade já cadastrado e inicializa o entityId cujo entidade já existe no cadastro.
	 *
	 * @param entityId o entityId da entidade já existente no cadastro.
	 */
	public EntidadeExistenteException(Long entityId) {

		super(MSG_ENTIDADE_JA_EXISTENTE);
		this.entityId = entityId;
	}

	/**
	 * Retorna o código da entidade já existente no cadastro.
	 *
	 * @return Long o código do entidade já existente no cadastro.
	 */
	public Long getEntityId() {

		return entityId;
	}
	
	
	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}


	/**
	 * Constante com a mensagem de entidade já cadastrado.
	 */
	private static final String MSG_ENTIDADE_JA_EXISTENTE =
		"Entidade já cadastrada";

	public String getMessage() {
		String mensagem = super.getMessage(); 
		if(nomeEntidade != null) {
			mensagem = mensagem + "(" + nomeEntidade + ":" + entityId + ").";
		} else {
			mensagem = mensagem + "(ID " + entityId + ").";
		}
	    return mensagem;  	
	}
}
