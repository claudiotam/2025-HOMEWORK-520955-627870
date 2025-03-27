package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test della classe labirinto
 */

public class LabirintoTest {
    private Labirinto labirinto;

    @BeforeEach
    void setUp() {
        labirinto = new Labirinto();
        labirinto.init();
    }

    @Test
    void LaStanzaInizialeEAtrio() {
        assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
    }

    @Test
    void LaStanzaVincenteEBiblioteca() {
        assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
    }
    @Test
    void VincenteNordIniziale() {
        assertEquals(labirinto.getStanzaVincente(), labirinto.getStanzaCorrente().getStanzaAdiacente("nord"));
    }
}
