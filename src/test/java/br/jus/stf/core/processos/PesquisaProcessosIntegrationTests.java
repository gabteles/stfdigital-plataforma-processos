package br.jus.stf.core.processos;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import br.jus.stf.core.ApplicationContextInitializer;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
import br.jus.stf.core.processos.domain.Processo;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 22.03.2016
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User(value = "peticionador", components = "sugerir-processos")
public class PesquisaProcessosIntegrationTests extends IntegrationTestsSupport {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void deveRejeitarUmaPesquisaInvalida() throws Exception {
        mockMvc.perform(post("/api/processos/pesquisa-avancada").contentType(APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
    }
    
    @Test
    public void deveSugerirUmProcesso() throws Exception {   	
    	Processo processo = new Processo(1L, "1");
    	processo.setId("teste");
    	processo.setClasse("HC");
    	processo.setNumero("123");
    	
    	IndexQueryBuilder builder = new IndexQueryBuilder();
    	builder.withIndexName("processos");
    	builder.withObject(processo);
    	elasticsearchTemplate.index(builder.build());
    	   	
        mockMvc.perform(get("/api/processos/sugestao")
        		.contentType(APPLICATION_JSON)
        		.param("identificacao", "hc1"))
        	.andExpect(status().isOk())
        	.andDo(print());
        
        elasticsearchTemplate.delete(Processo.class, "teste");
    }
    
}
