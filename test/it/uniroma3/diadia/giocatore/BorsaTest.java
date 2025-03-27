package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 */
class BorsaTest {
    Borsa borsa;

    @BeforeEach
    void setUp() {
        borsa = new Borsa();
    }

    @Test
    void PesoMassimoEDieci() {
        assertEquals(10, borsa.getPesoMax());
    }

    @Test
    void LaBorsaIniziaVuota() {
        assertTrue(borsa.isEmpty());
    }

    @Test
    void LaBorsaContieneCose() {
        borsa.addAttrezzo(new Attrezzo("libro", 1));
        assertFalse(borsa.isEmpty());
    }

    @Test
    void LaBorsaLimitaIlPeso() {
        borsa.addAttrezzo(new Attrezzo("armadio", 3500));
        assertTrue(borsa.isEmpty());
    }

}
