package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
    /**
     * esecuzione del comando
     */
    public void esegui(Partita partita);

    /**
     * set parametro del comando
     */
    public void setParametro(String parametro);

    /**
     * set console del comando
     */
    public void setIOConsole(IO ioconsole);
}
