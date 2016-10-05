package br.jus.stf.plataforma.identificadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.jus.stf.plataforma.identificadores.domain.Identificador;

/**
 * Testes unitários para identificador.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 02.08.2016
 */
public class IdentificadorUnitTests {
	
	@Test
	public void criaIdentificadorComCategoria() {
		Identificador identificador = new Identificador("ADI", 1L);
		
		assertNotNull("Identificador não pode ser nulo.", identificador);
		assertEquals("Categoria deve ser ADI.", "ADI", identificador.identity());
		assertEquals("Número deve ser igual a 1.", new Long(1L), identificador.numero());
	}
	
	@Test
	public void criaIdentificadorSemCategoria() {
		Identificador identificador = new Identificador("", 1L);
		
		assertNotNull("Identificador não pode ser nulo.", identificador);
		assertEquals("Categoria deve ser vazia.", "", identificador.identity());
		assertEquals("Número deve ser igual a 1.", new Long(1L), identificador.numero());
	}
	
	@Test
	public void criaIdentificadorComCategoriaNula() {
		Identificador identificador = new Identificador(null, 1L);
		
		assertNotNull("Identificador não pode ser nulo.", identificador);
		assertNull("Categoria deve ser nula.", identificador.identity());
		assertEquals("Número deve ser igual a 1.", new Long(1L), identificador.numero());
	}
	
	@Test(expected = NullPointerException.class)
	public void naodeveCriarIdentificadorComNumeroNulo() {
		new Identificador("RE", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naodeveCriarIdentificadorComNumeroNegativo() {
		new Identificador("RE", -1L);
	}
	
	@Test
	public void incrementaNumeroDaCategoria() {
		Identificador identificador = new Identificador(null, 1L);
		
		assertEquals("Número deve ser igual a 1.", new Long(1L), identificador.numero());
		
		identificador.incrementar();
		
		assertEquals("Número deve ser igual a 2.", new Long(2L), identificador.numero());
	}

}