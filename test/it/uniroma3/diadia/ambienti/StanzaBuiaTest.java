package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	Stanza   magaz;
	Attrezzo lanterna;

    
    @BeforeEach
	public void setUp() {
		// Creazione di alcune stanze
		this.magaz   = new StanzaBuia("Magazzino");
        
        // Creazione di alcuni attrezzi
		this.lanterna = new Attrezzo("lanterna", 3);

        // Aggiungo attrezzi nelle stanze
		//this.magaz.addAttrezzo(  this.lanterna);
		
        // Collego le stanze
		//this.atrio    .impostaStanzaAdiacente("nord", this.aulaN11);
	}

	@Test
	public void verificaSeLaStanzaHaBuio() {
        assertEquals(this.magaz.getDescrizione(), "qui c'è un buio pesto (forse serve una lanterna?)");
	}

    @Test
	public void verificaSeLaStanzaNonHaBuio() {
        this.magaz.addAttrezzo(lanterna);
		assertNotEquals(this.magaz.getDescrizione(), "qui c'è un buio pesto (forse serve una lanterna?)");
	}
}
