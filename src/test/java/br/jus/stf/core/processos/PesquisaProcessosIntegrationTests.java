package br.jus.stf.core.processos;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
 * @since 22.03.2016
 */
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
@WebIntegrationTest({"server.port:0", "eureka.client.enabled:false"})
@ActiveProfiles(Profiles.DEVELOPMENT)
public class PesquisaProcessosIntegrationTests extends IntegrationTestsSupport {

    @Test
    public void deveRejeitarUmaPesquisaInvalida() throws Exception {
        mockMvc.perform(post("/api/processos").contentType(APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
    }
    
}
