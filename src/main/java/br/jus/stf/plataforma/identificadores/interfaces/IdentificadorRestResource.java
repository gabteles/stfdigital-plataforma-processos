package br.jus.stf.plataforma.identificadores.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.plataforma.identificadores.domain.Identificador;
import br.jus.stf.plataforma.identificadores.domain.IdentificadorRepository;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.03.2016
 */
@RestController
@RequestMapping("/api/identificadores")
public class IdentificadorRestResource {
	
	@Autowired
    private IdentificadorRepository identificadorRepository;
	
	/**
	 * @param categoria
	 * @return
	 */
	@RequestMapping(method = GET)
	public Long numero(@RequestParam(required = false) String categoria) {
		if (StringUtils.isNotBlank(categoria)) {
			Identificador identficador = identificadorRepository.novoIdentificador(categoria);
			return identficador.numero();
		} else {
			return identificadorRepository.novoIdentificadorId();
		}
	}
	
}
