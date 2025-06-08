package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.NPC;

public class ComandoRegala implements Comando {
    private static final String MESSAGGIO_CON_CHI = "Nessun Personaggio a cui regalare "+
    "in questa stanza. Cerca una stanza con personaggi. ";
    private String nome_attrezzo;
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
        if (nome_attrezzo == null) {
            this.ioconsole.mostraMessaggio("Quale attrezzo vuoi regalare? Specifica un nome di attrezzo");
            return;
        }
        Attrezzo attrezzo = partita.getGiocatore().getAttrezzo(nome_attrezzo);
        if (attrezzo == null) {
            this.ioconsole.mostraMessaggio("Attrezzo inesistente nella borsa");
            return;
        } 
        
        personaggio.riceviRegalo(partita, attrezzo);
        this.ioconsole.mostraMessaggio(personaggio.getRisposta());
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