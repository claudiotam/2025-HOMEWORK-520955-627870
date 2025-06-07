package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends Personaggio {
    private static final String MSG_AZIONE_OK = "Sei un vero simpaticone, " +
            "con una mia magica azione, troverai un nuovo oggetto " +
            "per il tuo borsone! ";
    private static final String MSG_AZIONE_FALLITA = "Mi spiace, ma non ho piu' nulla... ";
    private static final String NOME_DEFAULT = "Ilmago";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Mago. Grazie e Arrivederci. ";
    private static final String NOME_ATTREZZO_DEFAULT = "bacchetta";

    /* 
     * il mago possiede un attrezzo segreto 
     * dopo l'interazione, il mago sposta l'attrezzo personale negli attrezzi della stanza
    */
    private Attrezzo attrezzo;

    public Mago(String nome, String presentazione, Attrezzo attrezzo) {
        super(nome, presentazione);
        attrezzo.getNome(); //throws if null
        this.attrezzo = attrezzo;
    }

    public Mago() {
        this(NOME_DEFAULT, PRESENTAZIONE_DEFAULT, new Attrezzo(NOME_ATTREZZO_DEFAULT, 1));
    }

    @Override
    public void agisci(Partita partita) {
        if (this.attrezzo != null) {
            partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
            this.attrezzo = null;
            this.setRisposta(MSG_AZIONE_OK);
        } else {
            this.setRisposta(MSG_AZIONE_FALLITA);
        }
    }
    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        this.setRisposta("Grazie! Dimezzo il prezzo all'oggetto chiamato " + attrezzo.getNome() +" e lo rilascio. ");
        attrezzo.dimezzaPeso();
        partita.posaAttrezzo(attrezzo);
    }
}