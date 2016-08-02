package br.jus.stf.core.identificadores.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 27.02.2016
 */
@Entity
@Table(name = "IDENTIFICADOR", schema = "IDENTIFICADOR")
public class Identificador extends EntitySupport<Identificador, String> {
	
	@Id
	@Column(name = "DSC_CATEGORIA")
	private String categoria;
	
	@Column(name = "NUM_IDENTIFICADOR")
	private Long numero;
	
	public Identificador() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}

	/**
	 * @param categoria
	 * @param numero
	 */
	public Identificador(String categoria, Long numero) {
		Validate.notNull(numero, "Número é requerido.");
		Validate.isTrue(numero >= 0, "Número deve ser maior ou igual a zero.");
		
		this.categoria = categoria;
		this.numero = numero;
	}

	@Override
	public String identity() {
		return categoria;
	}
	
	/**
	 * @return
	 */
	public Long numero() {
		return numero;
	}

	/**
	 * Incremeta o número vinculado a categoria. 
	 */
	public void incrementar() {
		++numero;
	}
	
}
