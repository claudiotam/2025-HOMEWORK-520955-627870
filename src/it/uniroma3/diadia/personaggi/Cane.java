package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.CiboPreferitoCane;

public class Cane extends NPC {
    private static final String MSG_AZIONE_OK = "Ti mordo! Hai perso un CFU. ";
    private static final String MSG_AZIONE_FALLITA = "Mi spiace, ti ho già morso una volta... ";
    private static final String NOME_DEFAULT = "Ilcane";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Cane. Grazie e Arrivederci. ";

    /* 
     * il cane possiede un riferimento a <cibo preferito> segreto 
     * interazione: morde, toglie un cfu
     * regalo: se cibo preferito, distrugge, altrimenti morte e toglie un cfu
    */
    private boolean ti_ho_gia_morso;

    public Cane(String nome, String presentazione, boolean ti_ho_gia_morso) {
        super(nome, presentazione);
        this.ti_ho_gia_morso = ti_ho_gia_morso;
    }

    public Cane() {
        this(NOME_DEFAULT, PRESENTAZIONE_DEFAULT, false);
    }

    @Override
    public void agisci(Partita partita) {
        if (!ti_ho_gia_morso) {
            partita.getGiocatore().togliUnCfu();
            this.ti_ho_gia_morso = true;
            this.setRisposta(MSG_AZIONE_OK);
        } else {
            this.setRisposta(MSG_AZIONE_FALLITA);
        }
    }

    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        if (attrezzo instanceof CiboPreferitoCane) {
            this.setRisposta("Grazie! Il cibo mi piace. Ti regalo un CFU. ");
            partita.getGiocatore().mettiUnCfu();
            partita.getGiocatore().removeAttrezzo(attrezzo);
        }
        else {
            this.setRisposta("Questo cibo fa schifo! Ti mordo! Hai perso un CFU. ");
            partita.getGiocatore().togliUnCfu();
            partita.posaAttrezzo(attrezzo);
        }

    }
}