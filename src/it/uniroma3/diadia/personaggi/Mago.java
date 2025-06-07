package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends Personaggio {
    private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
            "con una mia magica azione, troverai un nuovo oggetto " +
            "per il tuo borsone! ";
    private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla... ";
    private static final String NOME_DEFAULT = "Ilmago";    
    private static final String PRESENTAZIONE_DEFAULT = "Io sono Mago Ilmago";    
    private static final String NOME_ATTREZZO_DEFAULT = "bacchetta";

    /* 
     * il mago possiede un attrezzo segreto 
     * dopo l'interazione, il mago sposta l'attrezzo personale negli attrezzi della stanza
    */
    private Attrezzo attrezzo;

    public Mago(String nome, String presentazione, Attrezzo attrezzo) {
        super(nome, presentazione);
        this.attrezzo = attrezzo;
    }

    public Mago() {
        super(NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
        this.attrezzo = new Attrezzo(NOME_ATTREZZO_DEFAULT, 1);
    }

    @Override
    public String agisci(Partita partita) {
        String msg;
        if (this.attrezzo != null) {
            partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
            this.attrezzo = null;
            msg = MESSAGGIO_DONO;
        } else {
            msg = MESSAGGIO_SCUSE;
        }
        return msg;
    }
}