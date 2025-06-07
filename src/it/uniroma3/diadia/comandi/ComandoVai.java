package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
    private Direzione direzione;
    private IO ioconsole;

    /**
     * esecuzione del comando	
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        if (this.direzione == null) {
            this.ioconsole.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
            return;
        }
        Stanza prossimaStanza = null;
        prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
        if (prossimaStanza == null) {
            this.ioconsole.mostraMessaggio("Direzione inesistente");
        }
        else {
            partita.setStanzaCorrente(prossimaStanza);
        }
    }

    /*
     * impostazione del parametro (cioè la direzione)
     */
    @Override
    public void setParametro(String parametro) {
        this.direzione = Direzione.valueOf(parametro.toUpperCase());
    }

    /* 
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
      this.ioconsole = ioconsole;
    }
}