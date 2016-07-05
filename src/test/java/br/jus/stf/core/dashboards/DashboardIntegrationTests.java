package br.jus.stf.core.dashboards;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
 * Testes de integração do mecanismo de Dashboard.
 * 
 * @author Tomas.Godoi
 *
 */
@SpringApplicationConfiguration(classes = ApplicationContextInitializer.class)
@WebIntegrationTest({"server.port:0", "eureka.client.enabled:false"})
@ActiveProfiles({Profiles.DEVELOPMENT, "integration-test"})
@Ignore
public class DashboardIntegrationTests extends IntegrationTestsSupport {

	@Test
	public void recuperarDashboardPadrao() throws Exception {
		mockMvc.perform(get("/api/dashboards").header("login", "peticionador")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));

		// TODO Dashboards Alterar para as linhas comentadas assim que o mecanismo de filtragem de recursos tiver sido migrado
		mockMvc.perform(get("/api/dashboards").header("login", "preautuador-originario")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-tarefas")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
				
		mockMvc.perform(get("/api/dashboards").header("login", "autuador")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-tarefas")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));

		mockMvc.perform(get("/api/dashboards").header("login", "distribuidor")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-tarefas")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));

		mockMvc.perform(get("/api/dashboards").header("login", "recebedor")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
		
		mockMvc.perform(get("/api/dashboards").header("login", "cartoraria")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-tarefas")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
		
		mockMvc.perform(get("/api/dashboards").header("login", "representante")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
		
		mockMvc.perform(get("/api/dashboards").header("login", "gestor-autuacao")).andExpect(status().isOk())
				//.andExpect(jsonPath("$[1].dashlets[2].nome", is("grafico-gestao")));
				.andExpect(jsonPath("$[0].dashlets[0].nome", is("minhas-peticoes")));
	}

}