package com.daniel.agenda.modelo.negocio.dominios;


public interface IAdministracaoDominio  {
 
	/**
	 *Este método é responsável por popular algumas classes principais, as quais não possuirão casos de uso para mantê-las.
	 *
	 *<b> How to: </b>
	 *1. persistir os estados brasileiros
	 *2. persistir as categorias: eletroeletronico, eletrodomesticos, brinquedos e moveis
	 *3. persistir 01 usuario administrador e 02 usuarios comuns
	 */
	public void popularBD();
	 
}
 
