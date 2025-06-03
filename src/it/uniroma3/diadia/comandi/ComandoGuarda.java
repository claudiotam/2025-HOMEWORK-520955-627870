package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

    private IO ioconsole;
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        // non fare nulla, lo sguardo viene dato automaticamente dopo ogni comando
        this.ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizioneDettagliata());
        this.ioconsole.mostraMessaggio(partita.getGiocatore().getDescrizioneDettagliata());
        this.ioconsole.mostraMessaggio("---");

    }

    /*
    * impostazione del parametro (inutile, ma richiesto dalla interface)
    */
    @Override
    public void setParametro(String parametro) {}

    /*
    * impostazione della console (inutile, ma richiesto dalla interface)
    */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }

}
