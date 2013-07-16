package com.daniel.agenda.modelo.negocio.dominios;


public interface IAdministracaoDominio  {
 
	/**
	 *Este m�todo � respons�vel por popular algumas classes principais, as quais n�o possuir�o casos de uso para mant�-las.
	 *
	 *<b> How to: </b>
	 *1. persistir os estados brasileiros
	 *2. persistir as categorias: eletroeletronico, eletrodomesticos, brinquedos e moveis
	 *3. persistir 01 usuario administrador e 02 usuarios comuns
	 */
	public void popularBD();
	 
}
 
