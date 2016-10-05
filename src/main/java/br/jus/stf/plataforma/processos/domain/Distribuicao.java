package br.jus.stf.plataforma.processos.domain;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author lucas.rodrigues
 *
 */
public class Distribuicao implements Comparable<Distribuicao> {

	@Field(type = FieldType.Long)
	private Long relatorId;
	
	@Field(type = FieldType.String)
	private String relator;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "dd-MM-yyyy'T'HH:mm:ss.SSSZZ")
	private Date data;
	
	public Distribuicao(Long relatorId, String relator, Date data) {
		this.relatorId = relatorId;
		this.relator = relator;
		this.data = data;
	}

	public Long getRelatorId() {
		return relatorId;
	}
	
	public void setRelatorId(Long relatorId) {
		this.relatorId = relatorId;
	}
	
	public String getRelator() {
		return relator;
	}
	
	public void setRelator(String relator) {
		this.relator = relator;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int compareTo(Distribuicao o) {
		return data.compareTo(o.data);
	}	
	
}
