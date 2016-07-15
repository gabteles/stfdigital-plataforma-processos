package br.jus.stf.core.identificadores.interfaces;

/**
 * Interface para o recurso de identificação
 * 
 * @author lucas.rodrigues
 *
 */
public interface IdentificadorResource {

	/**
	 * Retorna um sequencial baseado na categoria informada,
	 * caso não seja informada será retornado um sequencial único
	 * @param categoria
	 * @return
	 */
	Long numero(String categoria);

}