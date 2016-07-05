package br.jus.stf.core.dashboards.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.dashboards.domain.model.Dashboard;
import br.jus.stf.core.dashboards.domain.model.DashboardId;
import br.jus.stf.core.dashboards.domain.model.DashboardRepository;

/**
 * Implementação do DashboardRepository.
 * 
 * @author Tomas.Godoi
 *
 */
@Repository
public class DashboardRepositoryImpl extends SimpleJpaRepository<Dashboard, DashboardId> implements DashboardRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public DashboardRepositoryImpl(EntityManager entityManager) {
		super(Dashboard.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	//@SecuredResource TODO Descomentar quando o mecanismo de segurança tiver sido migrado.
	public List<Dashboard> listar() {
		return findAll();
	}

	@Override
	public DashboardId proximoId() {
		Query query = entityManager.createNativeQuery("SELECT DASHBOARDS.SEQ_DASHBOARD.NEXTVAL FROM DUAL", Long.class);
		Long sequencial = (Long) query.getSingleResult();
		return new DashboardId(sequencial);
	}

}