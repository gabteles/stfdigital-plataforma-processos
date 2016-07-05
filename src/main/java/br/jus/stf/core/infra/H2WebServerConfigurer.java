package br.jus.stf.core.infra;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@Configuration
public class H2WebServerConfigurer {
	
	@Profile("development")
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8181");	
	}
	
}

