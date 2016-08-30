package br.jus.stf.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
public class ApplicationContextInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationContextInitializer.class, args);
	}

}
