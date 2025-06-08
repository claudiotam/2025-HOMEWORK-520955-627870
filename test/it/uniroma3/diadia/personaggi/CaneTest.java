package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.CiboPreferitoCane;
import it.uniroma3.diadia.giocatore.Giocatore;

public class CaneTest {
    private Partita   partita;
    private Labirinto labirinto;
    private Cane      cane;
    private Stanza    stanza;

    @BeforeEach
	void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
		this.partita     = new Partita();
        this.labirinto   = new Labirinto();
        this.stanza      = new Stanza("stanza_con_cane");
        this.cane        = new Cane();

        this.partita.setLabirintoCustom(labirinto);
		this.labirinto.addStanzaCorrente(stanza);
        this.stanza.setPersonaggio(cane);
	}

    
    @Test
    void testAgisciUnCfu() {
        Giocatore giocatore   = new Giocatore(1);
        this.partita.setGiocatoreCustom(giocatore);

        assertFalse(giocatore.hasZeroCfu());
        this.cane.agisci(partita);
        assertTrue(giocatore.hasZeroCfu());
    }

    @Test
    void testAgisciDueCfu() {
        Giocatore giocatore   = new Giocatore(2);
        this.partita.setGiocatoreCustom(giocatore);

        this.cane.agisci(partita);
        assertFalse(giocatore.hasZeroCfu());
        this.cane.agisci(partita);
        assertFalse(giocatore.hasZeroCfu());

    }
    @Test
    void testRiceviRegaloBuono() {
        CiboPreferitoCane cibo = new CiboPreferitoCane("cibo_per_cani_tipotre", 1);
        Giocatore giocatore    = new Giocatore(0);
        this.partita.setGiocatoreCustom(giocatore);

        assertTrue(giocatore.hasZeroCfu());
        this.cane.riceviRegalo(partita, cibo);
        assertFalse(giocatore.hasZeroCfu());
    }

    @Test
    void testRiceviRegaloCattivo() {
        Attrezzo altro         = new Attrezzo("altro", 1);
        Giocatore giocatore    = new Giocatore(1);
        this.partita.setGiocatoreCustom(giocatore);

        assertFalse(giocatore.hasZeroCfu());
        this.cane.riceviRegalo(partita, altro);
        assertTrue(giocatore.hasZeroCfu());
    }
}
