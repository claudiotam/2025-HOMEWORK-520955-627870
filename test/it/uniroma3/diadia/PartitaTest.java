package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * test della classe partita 
 */
class PartitaTest {
	Partita partita;
	Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
		this.partita     = new Partita();
        
		this.giocatore   = this.partita.getGiocatore();
	}

	@Test
	// verifica che la partita non è finita prima di cominciare
	void verificaisFinita() {
		assertFalse(this.partita.isFinita(), "La partita è finita prima di cominciare");
	}
	
    @Test
    public void testInizializzazionePartita() {
        // Verifica che la partita sia inizializzata correttamente
        assertNotNull(this.partita.getStanzaCorrente(), "La stanza corrente non deve essere nulla");
        assertEquals("Atrio", this.partita.getStanzaCorrente().getNome() , "La stanza iniziale non è correttamente impostata");
        assertFalse(this.partita.isFinita(), "La partita non dovrebbe essere finita all'inizio");
    }

    @Test
    public void testVittoria() {
        // Impostiamo la stanza corrente su quella finale e verifichiamo la vittoria
        this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
        assertTrue(this.partita.vinta(), "La partita dovrebbe essere vinta se il giocatore è nella stanza finale");
    }

    @Test
    public void testFinePartitaConCfuZero() {
        // Impostiamo i CFU del giocatore a 0 e verifichiamo che la partita sia finita
    	for (int i=0; i < 20; i++) giocatore.togliUnCfu();
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita se i CFU del giocatore sono 0");
    }

    @Test
    public void testImpostazioneStanzaCorrente() {
        // Verifica che la stanza corrente possa essere impostata correttamente
        Stanza nuovaStanza = new Stanza("Sala della magia");
        partita.setStanzaCorrente(nuovaStanza);
        assertEquals("Sala della magia", partita.getStanzaCorrente().getNome(), "La stanza corrente non è impostata correttamente");
    }

    @Test
    public void testSetFinita() {
        // Verifica che la partita possa essere terminata manualmente
        partita.setFinita();
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita dopo aver chiamato setFinita");
    }
}
