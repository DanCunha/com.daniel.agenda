package com.daniel.agenda.modelo.negocio.excecoes;

public class EntidadeExistenteException extends Exception {

	private static final long serialVersionUID = -4967940379806496290L;

	/**
	 * O entityId da entidade j� existente no cadastro.
	 */
	private Long entityId;
	
	private String nomeEntidade;
	
	/**
	 * O construtor da classe. Inicializa a mensagem da super-classe com uma mensagem
	 * padr�o de entidade j� cadastrado e inicializa o entityId cujo entidade j� existe no cadastro.
	 *
	 * @param entityId o entityId da entidade j� existente no cadastro.
	 */
	public EntidadeExistenteException(Long entityId) {

		super(MSG_ENTIDADE_JA_EXISTENTE);
		this.entityId = entityId;
	}

	/**
	 * Retorna o c�digo da entidade j� existente no cadastro.
	 *
	 * @return Long o c�digo do entidade j� existente no cadastro.
	 */
	public Long getEntityId() {

		return entityId;
	}
	
	
	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}


	/**
	 * Constante com a mensagem de entidade j� cadastrado.
	 */
	private static final String MSG_ENTIDADE_JA_EXISTENTE =
		"Entidade j� cadastrada";

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
