package it.uniroma3.diadia.personaggi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class MagoTest {
    private Partita   partita;
    private Labirinto labirinto;
    private Mago      mago;
    private Stanza    stanza;

    @BeforeEach
	void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
		this.partita     = new Partita();
        this.labirinto   = new Labirinto();
        this.stanza      = new Stanza("stanza_con_cane");
        this.mago        = new Mago();

        this.partita.setLabirintoCustom(labirinto);
		this.labirinto.addStanzaCorrente(stanza);
        this.stanza.setPersonaggio(mago);
	}

    @Test
    void testAgisci() {}

    @Test
    void testRiceviRegaloBuono() {}

    @Test
    void testRiceviRegaloCattivo() {}
}
