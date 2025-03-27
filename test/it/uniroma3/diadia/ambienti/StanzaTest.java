package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * classe di test, pdf poo 8 - 2025-homework A
 */
class StanzaTest {
	Stanza atrio;
	Stanza corridoio;
	Stanza reception;
	
	@BeforeEach
	public void setUp() {
		atrio = new Stanza("atrio");
		corridoio = new Stanza("corridoio");
		reception = new Stanza("reception");
		atrio    .impostaStanzaAdiacente("nord", corridoio);
		corridoio.impostaStanzaAdiacente("nord", reception);
		reception.impostaStanzaAdiacente("nord", atrio);
		atrio.addAttrezzo(new Attrezzo("lanterna",3));

	}

	@Test
	public void verificastanzaadiacenteesiste() {
		assertEquals(corridoio, atrio.getStanzaAdiacente("nord"));
	}
	@Test
	public void verificastanzaadiacentenonesiste() {
		assertNull(atrio.getStanzaAdiacente("sud"));
	}
	@Test
	public void verificaattrezzoesiste() {
		assertTrue(atrio.hasAttrezzo("lanterna"));
	}
	public void verificagetdirezioni() {
		assertArrayEquals(new String[] {"nord"}, atrio.getDirezioni());
	}
}
