package br.jus.stf.core.processos.domain;

import java.util.Date;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 29.02.2016
 */
public class PesquisarProcessosQuery {
	
	private String classe;
	
	private Long numero;
	
	private String protocolo;
	
	private Date dataDistribuicao;
	
	private String relator;
	
	private String parte;

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public String getRelator() {
		return relator;
	}

	public void setRelator(String relator) {
		this.relator = relator;
	}

	public String getParte() {
		return parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}
	
}
