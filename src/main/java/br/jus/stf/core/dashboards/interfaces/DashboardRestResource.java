package br.jus.stf.core.dashboards.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.dashboards.domain.model.DashboardRepository;
import br.jus.stf.core.dashboards.interfaces.dto.DashboardDto;
import br.jus.stf.core.dashboards.interfaces.dto.DashboardDtoAssembler;

/**
 * Api REST para recuperar dashboards.
 * 
 * @author Tomas.Godoi
 */
@RestController
@RequestMapping("/api/dashboards")
public class DashboardRestResource {

	@Autowired
	private DashboardRepository dashboardRepository;
	
	@Autowired
	private DashboardDtoAssembler dashboardDtoAssembler;

	@ApiOperation("Recupera o dashboard padrão para o papel recebido")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<DashboardDto> recuperarPadrao() {
		return dashboardRepository.listar().stream()
				.map(dashboard -> dashboardDtoAssembler.toDto(dashboard))
				.collect(Collectors.toList());
	}

}
