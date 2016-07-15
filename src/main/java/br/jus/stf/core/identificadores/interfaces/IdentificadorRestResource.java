package br.jus.stf.core.identificadores.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.core.identificadores.domain.Identificador;
import br.jus.stf.core.identificadores.domain.IdentificadorRepository;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.03.2016
 */
@RestController
@RequestMapping("/api/identificadores")
public class IdentificadorRestResource implements IdentificadorResource {
	
	@Autowired
    private IdentificadorRepository identificadorRepository;
	
	@Override
	@RequestMapping(method = GET)
	public Long numero(@RequestParam(required = false) String categoria) {
		if (StringUtils.isNotBlank(categoria)) {
			Identificador identficador = identificadorRepository.novoIdentficador(categoria);
			return identficador.numero();
		} else {
			return identificadorRepository.novoIdentificadorId();
		}
	}
	
}
