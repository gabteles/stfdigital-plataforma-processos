package br.jus.stf.core.dashboards.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.elasticsearch.common.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Lucas.Rodrigues
 *
 */
@Embeddable
public class DashletId extends ValueObjectSupport<DashletId> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SEQ_DASHLET")
	private Long sequencial;

	DashletId() {
		
	}
	
	public DashletId(Long sequencial) {
		Validate.notNull(sequencial, "dashletid.sequencial.required");
		
		this.sequencial = sequencial;
	}
	
	public Long toLong() {
		return sequencial;
	}
	
	@Override
	public String toString() {
		return sequencial.toString();
	}

}