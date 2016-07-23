package br.jus.stf.core.identificadores.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import br.jus.stf.core.identificadores.interfaces.IdentificadorResource;

/**
 * @author lucas.rodrigues
 *
 */
@Configuration
public class RMIServiceExporterConfiguration {

	@Autowired
	private IdentificadorResource identificadorResource;
	
	@Bean
	public RmiServiceExporter identificadorRmiService() {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("IdentificadorResource");
		exporter.setService(identificadorResource);
		exporter.setServiceInterface(IdentificadorResource.class);
		exporter.setRegistryPort(10981);
		exporter.setServicePort(10881);
		return exporter;
	}
	
}
