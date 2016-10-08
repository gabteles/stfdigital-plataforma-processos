package br.jus.stf.plataforma.processos.interfaces;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.plataforma.processos.domain.PesquisarProcessosQuery;
import br.jus.stf.plataforma.processos.domain.Processo;
import br.jus.stf.plataforma.processos.domain.ProcessoFinder;
import br.jus.stf.plataforma.processos.domain.SugerirProcessosQuery;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 07.03.2016
 */
@RestController
@RequestMapping("/api")
public class ProcessoRestResource {
	
	@Autowired
	private ProcessoFinder finder;

	@RequestMapping(value = "/pesquisa-avancada", method = POST)
	public List<Processo> pesquisar(@RequestBody @Valid PesquisarProcessosQuery query, BindingResult result) {
		if (isBlank(query.getProtocolo()) && isBlank(query.getParte())) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		return finder.execute(query);
	}
	
	@RequestMapping(value = "/sugestao", method = GET)
	public List<Processo> sugerir(SugerirProcessosQuery query) {
		return finder.execute(query);
	}
	
}
