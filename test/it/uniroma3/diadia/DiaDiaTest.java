package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiaDiaTest {
    @BeforeEach
    public void setup() {
    }

    @Test
    public void testPartitaMonocomando() {
        //prepara dei messaggi da inviare
        ArrayList<String> messaggi_in = new ArrayList<String> ();
        messaggi_in.add("fine");
		
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
		DiaDia gioco = new DiaDia(iosimulator);

        //lancia
        gioco.gioca();

        //scarica i messaggi
        ArrayList<String> messaggi_out = iosimulator.getMessaggiOut();

        //verifica che sono stati prodotti 4 messaggi
        assertEquals(messaggi_out.size(), 4);

        //verifica che l'ultimo messaggio prodotto è corretto
        assertEquals(messaggi_out.getLast(), "Grazie di aver giocato!");
    }

    @Test
    public void testPartitaEsaurimentoCfu() {
        //prepara dei messaggi da inviare
        ArrayList<String> messaggi_in = new ArrayList<String> ();
		
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
		DiaDia gioco = new DiaDia(iosimulator);

        //lancia
        gioco.gioca();

        //scarica i messaggi
        ArrayList<String> messaggi_out = iosimulator.getMessaggiOut();

        //verifica che sono stati prodotti 39 messaggi
        assertEquals(messaggi_out.size(), 39);

        //verifica che il penultimo messaggio prodotto è corretto
        assertEquals(messaggi_out.get(messaggi_out.size()-2), "Hai perso! Non hai manco un CFU!");
    }
}
