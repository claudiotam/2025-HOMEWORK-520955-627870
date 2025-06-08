package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends NPC {
    private static final String MSG_AZIONE_OK = "Sei stato teletrasportato in un'altra stanza! ";
    private static final String MSG_AZIONE_FALLITA = "Mi spiace, ti ho già teletrasportato una volta... ";
    private static final String NOME_DEFAULT = "Lastrega";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Strega. Grazie e Arrivederci. ";

    /* 
     * la strega possiede un riferimento a <stanza> segreto 
     * dopo l'interazione, la strega sposta l'attrezzo personale negli attrezzi della stanza
    */
    private Stanza stanza_destinazione;

    public Strega(String nome, String presentazione, Stanza stanza) {
        super(nome, presentazione);
        stanza.getNome(); //throws if stanza is null
        this.stanza_destinazione = stanza;
    }

    public Strega() {
        this(NOME_DEFAULT, PRESENTAZIONE_DEFAULT, new Stanza("stanza fuori labirinto"));
    }

    @Override
    public void agisci(Partita partita) {
        if (this.stanza_destinazione != null) {
            partita.setStanzaCorrente(this.stanza_destinazione);
            this.stanza_destinazione = null;
            this.setRisposta(MSG_AZIONE_OK);
        } else {
            this.setRisposta(MSG_AZIONE_FALLITA);
        }
    }

    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        this.setRisposta("Grazie! Questo regalo chiamato " + attrezzo.getNome() + " mi piace. Rido! AHAHAH!. ");
        partita.getGiocatore().removeAttrezzo(attrezzo);
    }
}