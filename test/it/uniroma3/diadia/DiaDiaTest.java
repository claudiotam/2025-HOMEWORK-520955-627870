package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiaDiaTest {
    @BeforeEach
    public void setup() {
    }

    @Test
    public void testPartitaMonocomando() {
        //prepara dei messaggi da inviare
        String[] messaggi_in = new String[] {"fine"};
		
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
		DiaDia gioco = new DiaDia(iosimulator);

        //lancia
        gioco.gioca();

        //scarica i messaggi
        String[] messaggi_out = iosimulator.getMessaggiOut();
        int messaggi_out_write_cursor = iosimulator.getMessaggiOutCursor();

        //verifica che sono stati prodotti 4 messaggi
        assertEquals(messaggi_out_write_cursor, 4);

        //verifica che l'ultimo messaggio prodotto è corretto
        assertEquals(messaggi_out[messaggi_out_write_cursor-1], "Grazie di aver giocato!");
    }

    @Test
    public void testPartitaEsaurimentoCfu() {
        //prepara dei messaggi da inviare
        String[] messaggi_in = new String[] {};
		
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
		DiaDia gioco = new DiaDia(iosimulator);

        //lancia
        gioco.gioca();

        //scarica i messaggi
        String[] messaggi_out = iosimulator.getMessaggiOut();
        int messaggi_out_write_cursor = iosimulator.getMessaggiOutCursor();

        //verifica che sono stati prodotti 39 messaggi
        assertEquals(messaggi_out_write_cursor, 39);

        //verifica che il penultimo messaggio prodotto è corretto
        assertEquals(messaggi_out[messaggi_out_write_cursor-2], "Hai perso! Non hai manco un CFU!");
    }
}
