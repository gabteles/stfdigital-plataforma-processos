package br.jus.stf.core.workflow;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.jus.stf.core.ApplicationContextInitializer;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 16.03.2016
 */
@Ignore
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User("peticionador")
public class TarefasIntegrationTests extends IntegrationTestsSupport {
    
    @Test
    public void deveRetornarTarefasPendentes() throws Exception {
        mockMvc.perform(get("/api/tarefas").contentType(APPLICATION_JSON)).andExpect(status().isOk());
    }
    
}
