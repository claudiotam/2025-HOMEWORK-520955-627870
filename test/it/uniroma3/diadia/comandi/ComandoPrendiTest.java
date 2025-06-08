package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
    Partita partita;
    Comando com_prendi;

    @BeforeEach
    public void setup() {
        //crea partita con inclusi labirinto, giocatore, borsa (il comando posa lavora su una partita)
        this.partita      = new Partita();
        
        //crea una console (il comando prendi ha bisogno di una console per mostrare gli errori)
        IO ioconsole      = new IOConsole();

        //crea un posabile
        Attrezzo attrezzo = new Attrezzo("attrezzo test", 5);
        this.partita.getStanzaCorrente().addAttrezzo(attrezzo);

        //crea un comando di prenditura
        this.com_prendi   = new ComandoPrendi();
        this.com_prendi.setIOConsole(ioconsole);
        this.com_prendi.setParametro("attrezzo test");

    }

    @Test
    public void testPosaUnAttrezzo() {
        this.com_prendi.esegui(partita);
        assertTrue(partita.getGiocatore().hasAttrezzo("attrezzo test"), "non sono riuscito a prendere l'attrezzo dalla stanza");
    }
}
