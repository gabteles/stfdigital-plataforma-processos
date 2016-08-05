package br.jus.stf.core.processos.domain;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.prefixQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.component.query.Query;
import br.jus.stf.core.framework.component.suggestion.Suggestion;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 20.02.2016
 */
@Component
public class ProcessoFinder {
	
	private static String CLASSE = "classe";
	private static String NUMERO = "numero";
	
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
	
    @Query(description = "Processos")
	public List<Processo> execute(PesquisarProcessosQuery processoQuery) {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		
		if (StringUtils.isNotBlank(processoQuery.getProtocolo())) {
			queryBuilder.withQuery(matchPhraseQuery("protocolo", processoQuery.getProtocolo()));
		}
		
		if (StringUtils.isNotBlank(processoQuery.getParte())) {
			queryBuilder.withQuery(matchPhraseQuery("partes", processoQuery.getParte()));
		}
        
        SearchQuery query = queryBuilder.build();
        
        return elasticsearchTemplate.queryForList(query, Processo.class);
	}
    
    @Suggestion
    public List<Processo> execute(PesquisarProcessosSuggestion processoSuggestion) {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		String identificacao = processoSuggestion.getIdentificacao();
		
		if (StringUtils.isNotBlank(identificacao)) {
			String classe = parseIdentificacao(identificacao, CLASSE);
			String numero = parseIdentificacao(identificacao, NUMERO);
			
			if (StringUtils.isNotBlank(classe) && StringUtils.isNotBlank(numero)) {
				queryBuilder.withQuery(boolQuery()
						.must(termQuery(CLASSE, classe))
						.must(prefixQuery(NUMERO, numero)));
		        SearchQuery query = queryBuilder.build();
		        return elasticsearchTemplate.queryForList(query, Processo.class);
			}
		}
    	return Collections.emptyList();
    }
    
	/**
	 * Faz o parse da identificação do processo, recuperando a informação desejada: sigla ou número.
	 * 
	 * @param identificacao a identificação de entrada 
	 * @param tipoIdentificacao o tipo de informação que se deseja
	 * @return sigla ou número do processo
	 */
	private String parseIdentificacao(String identificacao, String tipoIdentificacao) {
		if (StringUtils.isNotBlank(identificacao)) {

			Pattern p = Pattern.compile("([a-zA-Z]*)[\\s]*([0-9]*)");
			Matcher matcher = p.matcher(identificacao.replaceAll("\\.", ""));
	
			if (matcher.find()) {
				if (tipoIdentificacao.equals(NUMERO)) {
					return matcher.group(2);
				}
				if (tipoIdentificacao.equals(CLASSE)) {
					return matcher.group(1);
				}
			}
			
		}
		return null;
	}

}
