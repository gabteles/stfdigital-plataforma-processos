package br.jus.stf.core.dashboards.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.jus.stf.core.framework.domaindrivendesign.AggregateRoot;
import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;

/**
 * Entidade Dashboard. É composta por um conjunto de Dashlets.
 * 
 * @author Tomas.Godoi
 *
 */
@javax.persistence.Entity
@Table(name = "DASHBOARD", schema = "DASHBOARDS")
public class Dashboard extends EntitySupport<Dashboard, DashboardId> implements AggregateRoot<Dashboard, DashboardId> {

	@EmbeddedId
	private DashboardId id;
	
	@Column(name = "NOM_DASHBOARD")
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "DASHBOARD_DASHLET", schema  = "DASHBOARDS",
			joinColumns = @JoinColumn(name = "SEQ_DASHBOARD", nullable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(name = "SEQ_DASHLET", nullable = false, updatable = false))
	private List<Dashlet> dashlets = new ArrayList<Dashlet>();
	
	Dashboard() {
		
	}
	
	public Dashboard(DashboardId id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public DashboardId identity() {
		return id;
	}
	
	public String nome() {
		return nome;
	}
	
	public List<Dashlet> dashlets() {
		return dashlets;
	}

}