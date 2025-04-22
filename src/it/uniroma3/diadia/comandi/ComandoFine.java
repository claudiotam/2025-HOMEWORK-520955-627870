package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        partita.setFinita();
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
    public void setIOConsole(IOConsole ioconsole) {}
}
