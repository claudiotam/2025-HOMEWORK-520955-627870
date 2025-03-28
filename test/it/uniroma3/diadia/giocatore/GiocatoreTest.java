package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * test della classe giocatore
 */
class GiocatoreTest {
	Giocatore giocatore;

	@BeforeEach
	void setUp() {
		// Setup per ogni test, viene eseguito prima di ciascun test
		this.giocatore = new Giocatore();
	}

	@Test
	public void testGiocatoreConCfuIniziali() {
		// Verifica che il giocatore abbia alcuni CFU iniziali
		assertFalse(this.giocatore.hasZeroCfu(), "I CFU iniziali del giocatore devono essere più di zero");
	}
	
    @Test
    public void testPrendereAttrezzo() {
        // Verifica che l'attrezzo possa essere preso correttamente
        assertTrue(this.giocatore.addAttrezzo(new Attrezzo("spada", 2)), "Il giocatore dovrebbe riuscire a prendere l'attrezzo");
        assertTrue(this.giocatore.hasAttrezzo("spada"), "Il giocatore dovrebbe avere l'attrezzo 'spada'");
    }

    @Test
    public void testPosareAttrezzoNonEsistente() {
        assertFalse(this.giocatore.removeAttrezzo(null), "Non dovrebbe essere possibile posare un attrezzo che non è nella borsa");
    }
}
