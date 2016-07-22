package br.jus.stf.core.processos;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
 * @since 22.03.2016
 */
@Ignore
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User("peticionador")
public class PesquisaProcessosIntegrationTests extends IntegrationTestsSupport {

    @Test
    public void deveRejeitarUmaPesquisaInvalida() throws Exception {
        mockMvc.perform(post("/api/processos").contentType(APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
    }
    
}
