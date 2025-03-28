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
	Stanza aulaN11;
	Stanza aulaN10;
	Attrezzo lanterna;
	Attrezzo osso;
	
	@BeforeEach
	public void setUp() {
		// set up prima di ogni test
		this.atrio     = new Stanza("Atrio");
		this.corridoio = new Stanza("corridoio");
		this.reception = new Stanza("reception");
		this.aulaN11   = new Stanza("Aula N11");
		this.aulaN10   = new Stanza("Aula N10");
        
        // Creazione di alcuni attrezzi
		this.lanterna = new Attrezzo("lanterna", 3);
		this.osso     = new Attrezzo("osso", 1);

        // Aggiungo attrezzi nelle stanze
		this.atrio.addAttrezzo(  this.osso);
		this.aulaN10.addAttrezzo(this.lanterna);
        
        // Collego le stanze
		this.atrio    .impostaStanzaAdiacente("nord", this.aulaN11);
		this.atrio    .impostaStanzaAdiacente("est",  this.aulaN10);
		this.atrio    .impostaStanzaAdiacente("sud",  this.corridoio);
		this.corridoio.impostaStanzaAdiacente("sud",  this.reception);
		this.reception.impostaStanzaAdiacente("sud",  this.atrio);
		
	}

	@Test
	public void verificastanzaadiacenteesiste() {
		assertEquals(this.corridoio, this.atrio.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void verificastanzaadiacentenonesiste() {
		assertNull(this.atrio.getStanzaAdiacente("ovest"));
	}
	
    @Test
    public void testGetNome() {
        // Verifica che il nome della stanza sia corretto
        assertEquals("Atrio", this.atrio.getNome(), "Il nome della stanza non corrisponde.");
    }

    @Test
    public void testAddAttrezzo() {
        // Verifica che l'attrezzo venga aggiunto correttamente alla stanza
        assertTrue(this.atrio.addAttrezzo(this.lanterna), "Non è stato possibile aggiungere l'attrezzo 'lanterna' nell'atrio.");
    }


    @Test
    public void testGetStanzaAdiacente() {
        // Verifica che la stanza adiacente venga correttamente restituita
        Stanza stanzaAdiacenteNord = this.atrio.getStanzaAdiacente("nord");
        assertNotNull(stanzaAdiacenteNord, "La stanza adiacente a nord dovrebbe essere presente.");
        assertEquals("Aula N11", stanzaAdiacenteNord.getNome(), "La stanza adiacente a nord dovrebbe essere 'Aula N11'");
        
        Stanza stanzaAdiacenteEst = this.atrio.getStanzaAdiacente("est");
        assertNotNull(stanzaAdiacenteEst, "La stanza adiacente a est dovrebbe essere presente.");
        assertEquals("Aula N10", stanzaAdiacenteEst.getNome(), "La stanza adiacente a est dovrebbe essere 'Aula N10'");
    }

    @Test
    public void testGetDirezioni() {
        // Verifica che le direzioni siano correttamente registrate
        String[] direzioni = this.atrio.getDirezioni();
        assertEquals(3, direzioni.length, "Le direzioni non sono corrette.");
        assertTrue(java.util.Arrays.asList(direzioni).contains("nord"), "La direzione 'nord' dovrebbe essere presente.");
        assertTrue(java.util.Arrays.asList(direzioni).contains("est"), "La direzione 'est' dovrebbe essere presente.");
    }


    @Test
    public void testToString() {
        // Verifica che la rappresentazione stringa della stanza sia corretta
        String descrizioneAtrio = this.atrio.getDescrizione();
        assertTrue(descrizioneAtrio.contains("Atrio"), "La descrizione della stanza dovrebbe contenere il nome 'Atrio'.");
        assertTrue(descrizioneAtrio.contains("osso"), "La descrizione della stanza dovrebbe contenere l'attrezzo 'osso'.");
        assertTrue(descrizioneAtrio.contains("nord"), "La descrizione della stanza dovrebbe contenere la direzione 'nord'.");
    }
}
