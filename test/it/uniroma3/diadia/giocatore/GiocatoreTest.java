package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test della classe giocatore
 */
class GiocatoreTest {
    Giocatore giocatore;

    @BeforeEach
    void setUp() {
        giocatore = new Giocatore();
    }

    @Test
	void verificaBorsaInizialeEsiste() {
		assertNotNull(giocatore.getBorsa());
	}

    @Test
    void verificaBorsaInizialeVuota() {
		assertTrue(giocatore.getBorsa().isEmpty());
	}

    @Test
    void verificaCfuConservaStato() {
		giocatore.setCfu(1234);
        assertEquals(1234, giocatore.getCfu());
	}

}
