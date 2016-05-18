package br.jus.stf.core.workflow;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;

import br.jus.stf.core.ApplicationContextInitializer;
import br.jus.stf.core.framework.Profiles;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 16.03.2016
 */
@Ignore
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
@WebIntegrationTest({"server.port:0", "eureka.client.enabled:false"})
@ActiveProfiles({Profiles.DEVELOPMENT, "integration-test"})
public class TarefasIntegrationTests extends IntegrationTestsSupport {
    
    @Test
    public void deveRetornarTarefasPendentes() throws Exception {
        mockMvc.perform(get("/api/tarefas").contentType(APPLICATION_JSON)).andExpect(status().isOk());
    }
    
}
