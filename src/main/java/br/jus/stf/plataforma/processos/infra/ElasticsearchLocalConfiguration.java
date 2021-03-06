package br.jus.stf.plataforma.processos.infra;

import java.io.File;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.FileSystemUtils;

import br.jus.stf.core.framework.Profiles;

/**
 * @author Tomas Godoi
 * @author Lucas Mariano
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 22.02.2016
 */
@Configuration
@Profile(Profiles.DEVELOPMENT)
public class ElasticsearchLocalConfiguration extends ElasticsearchAutoConfiguration implements InitializingBean {

	public ElasticsearchLocalConfiguration(ElasticsearchProperties properties) {
		super(properties);
	}

	private static final String ELASTIC_DATA_DIR = "data";
	
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// Em desenvolvimento sempre apagamos os índices para não comprometer os testes
		FileSystemUtils.deleteRecursively(new File(ELASTIC_DATA_DIR));
	}

}
