package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	Stanza   presi;
	Attrezzo ppt;

    
    @BeforeEach
	public void setUp() {
		// Creazione di alcune stanze
        this.presi   = new StanzaBloccata("Presidenza");
        Stanza labIA   = new Stanza        ("LabIA");
		
        // Creazione di alcuni attrezzi
        this.ppt      = new Attrezzo("passepartout", 4);

        // Aggiungo attrezzi nelle stanze
		//this.magaz.addAttrezzo(  this.lanterna);
		
        // Collego le stanze
        this.presi.impostaStanzaAdiacente("est", labIA);

	}

	@Test
	public void verificaSeLaStanzaHaBlocco() {
        assertSame(this.presi.getStanzaAdiacente("est") , this.presi, "la stanza bloccata non sta bloccando luscita est");
	}

    @Test
	public void verificaSeLaStanzaNonHaBlocco() {
        this.presi.addAttrezzo(ppt);
		assertNotSame(this.presi.getStanzaAdiacente("est"), this.presi, "la direzione da sbloccare risulta ancora bloccata");
	}
}
