package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
    private String nome_attrezzo;
    private IO ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        if (nome_attrezzo == null) {
            this.ioconsole.mostraMessaggio("Quale attrezzo vuoi posare? Specifica un nome di attrezzo");
            return;
        }
        Attrezzo attrezzo = partita.getGiocatore().getAttrezzo(nome_attrezzo);
        if (attrezzo == null) {
            this.ioconsole.mostraMessaggio("Attrezzo inesistente nella borsa");
            return;
        } 
        partita.posaAttrezzo(attrezzo);
    }

    /*
     * impostazione del parametro (cioè il nome_attrezzo)
     */
    @Override
    public void setParametro(String nome_attrezzo) {
        this.nome_attrezzo = nome_attrezzo;
    }

    /*
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}
