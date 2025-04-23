package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
    private IO ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        this.ioconsole.mostraMessaggio("Hai inserito un comando non valido");
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
