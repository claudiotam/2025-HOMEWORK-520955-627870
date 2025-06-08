package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.NPC;

public class ComandoInteragisci implements Comando {
    private static final String MESSAGGIO_CON_CHI = "Nessun Personaggio nella stanza. Cerca una "+
    "stanza con personaggi. " + 
    "[original: Con chi dovrei interagire?...]";
    private IO ioconsole;

    /**
     * esecuzione del comando
     * Cerca di andare in una direzione. Se c'e' una stanza ci entra
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    @Override
    public void esegui(Partita partita) {
        NPC personaggio = partita.getStanzaCorrente().getPersonaggio();
        if (personaggio == null) {
            this.ioconsole.mostraMessaggio(MESSAGGIO_CON_CHI);
            return;
        }
        personaggio.agisci(partita);
        this.ioconsole.mostraMessaggio(personaggio.getRisposta());
    }

    /*
     * impostazione del parametro (inutile, ma richiesto dalla interface)
     */
    @Override
    public void setParametro(String parametro) {}

    /*
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}