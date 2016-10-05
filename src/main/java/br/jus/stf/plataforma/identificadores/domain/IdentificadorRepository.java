package br.jus.stf.plataforma.identificadores.domain;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 27.02.2016
 */
@Repository
public class IdentificadorRepository {

	@Autowired
    private EntityManager entityManager;

	/**
	 * @param categoria
	 * @return
	 */
	@Transactional
	public Identificador novoIdentificador(String categoria) {
		Identificador identificador = entityManager.find(Identificador.class, categoria, PESSIMISTIC_WRITE);
		
		if (identificador == null) {
			identificador = new Identificador(categoria, 0L);
		}
		
		identificador.incrementar();
		
		entityManager.persist(identificador);
		
		return identificador;
	}
	
	/**
	 * @return
	 */
	public Long novoIdentificadorId() {
		Query query = entityManager.createNativeQuery("SELECT identificador.seq_identificador.NEXTVAL FROM DUAL");
		return ((BigInteger) query.getSingleResult()).longValue();
	}

}
