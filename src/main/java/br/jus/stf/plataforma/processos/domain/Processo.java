package br.jus.stf.plataforma.processos.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 20.02.2016
 */
@Document(indexName = "processos", type = "processo")
public class Processo {
	
    @Id
    private String id;
    
    @Field(type = FieldType.Long)
    private Long protocoloId;
    
    @Field(type = FieldType.String)
    private String processoId;
    
    @Field(type = FieldType.String)
    private String classe;
    
    @Field(type = FieldType.String)
    private String numero;
    
    @Field(type = FieldType.String, analyzer = "keyword")
    private String protocolo;
    
    @Field(type = FieldType.String)
    private Set<String> partes = new HashSet<>();
    
    @Field(type = FieldType.Nested)
    private Set<Distribuicao> distribuicoes = new TreeSet<>();
    
    public Processo() {
    	// O Parser JSON (Jackson) sempre usa o construtor default antes de popular uma nova instância.
	}
    
    public Processo(Long protocoloId, String protocolo) {
    	this.id = UUID.randomUUID().toString();
    	this.protocoloId = protocoloId;
		this.protocolo = protocolo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getProtocoloId() {
		return protocoloId;
	}

	public void setProtocoloId(Long protocoloId) {
		this.protocoloId = protocoloId;
	}

	public String getProcessoId() {
		return processoId;
	}

	public void setProcessoId(String processoId) {
		this.processoId = processoId;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Set<String> getPartes() {
		return partes;
	}

	public void setPartes(Set<String> partes) {
		this.partes = partes;
	}

	public Set<Distribuicao> getDistribuicoes() {
		return distribuicoes;
	}

	public void setDistribuicoes(Set<Distribuicao> distribuicoes) {
		this.distribuicoes = distribuicoes;
	}
    
}
