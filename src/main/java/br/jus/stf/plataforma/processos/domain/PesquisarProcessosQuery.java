package br.jus.stf.plataforma.processos.domain;


import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import br.jus.stf.core.framework.component.query.helper.Criteria;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 29.02.2016
 */
public class PesquisarProcessosQuery {
	
	@NotEmpty
	private List<Criteria> criterias;
	
	public List<Criteria> getCriterias() {
		return criterias;
	}
	
	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}
	
}
