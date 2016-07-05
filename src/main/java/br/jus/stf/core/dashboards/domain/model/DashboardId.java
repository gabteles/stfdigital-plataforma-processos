package br.jus.stf.core.dashboards.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Lucas.Rodrigues
 *
 */
@Embeddable
public class DashboardId extends ValueObjectSupport<DashboardId> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SEQ_DASHBOARD", nullable = false)
	private Long sequencial;

	DashboardId() {

	}
	
	public DashboardId(final Long sequencial){
		Validate.notNull(sequencial, "dashboardId.sequencial.required");
		
		this.sequencial = sequencial;
	}

	public Long toLong(){
		return sequencial;
	}
	
	@Override
	public String toString(){
		return sequencial.toString();
	}	

}
