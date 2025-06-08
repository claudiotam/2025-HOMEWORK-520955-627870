package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.NPC;

public class ComandoSaluta implements Comando {
    private IO ioconsole;

    /**
     * esecuzione del comando	
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    @Override
    public void esegui(Partita partita) {
        NPC persona = partita.getStanzaCorrente().getPersonaggio();
        if (persona == null) {
            this.ioconsole.mostraMessaggio("Nessun salutabile nella stanza. ");
            return;
        }
        persona.preparaRispSaluto();
        this.ioconsole.mostraMessaggio("Il personaggio dice: " + persona.getRisposta());
    }

    /*
     * impostazione del parametro (???)
     */
    @Override
    public void setParametro(String parametro) {
    }

    /* 
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
      this.ioconsole = ioconsole;
    }
}