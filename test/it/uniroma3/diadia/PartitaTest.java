package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * test della classe partita 
 */
class PartitaTest {
	Partita partita;
	Stanza atrio;
	Stanza laboratorio;
	Stanza biblioteca;
	@BeforeEach
	void setUp() {
		partita = new Partita();
		atrio = this.partita.getStanzaCorrente();
		laboratorio = atrio.getStanzaAdiacente("ovest");
		biblioteca = atrio.getStanzaAdiacente("nord");
	}

	@Test
	void verificaGetStanzaVincente() {
		assertEquals(biblioteca, partita.getStanzaVincente());
	}
	@Test
	void verificaGetStanzaCorrente() {
		assertEquals(atrio, partita.getStanzaCorrente());
		
	}
	@Test
	void verificaisFinita() {
		assertFalse(partita.isFinita());
	}
}
