package br.jus.stf.core.dashboards.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;

/**
 * Entidade Dashlet. Representa um componente de exibição de informações para o
 * usuário.
 * 
 * @author Tomas.Godoi
 *
 */
@javax.persistence.Entity
@Table(name = "DASHLET", schema = "DASHBOARDS", uniqueConstraints = @UniqueConstraint(columnNames = {"NOM_DASHLET"}))
public class Dashlet extends EntitySupport<Dashlet, DashletId> {
	
	@EmbeddedId
	private DashletId id;
	
	@Column(name = "NOM_DASHLET")
	private String nome;
	
	@Column(name = "DSC_DASHLET")
	private String descricao;
	
	Dashlet() {
		
	}

	public Dashlet(DashletId id, String nome, String descricao) {
		Validate.notNull(id, "dashlet.id.required");
		Validate.notBlank(nome, "dashlet.nome.required");
		Validate.notBlank(descricao, "dashlet.descricao.required");
		
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Override
	public DashletId identity() {
		return id;
	}
	
	public String nome() {
		return nome;
	}

	public String descricao() {
		return descricao;
	}

}
