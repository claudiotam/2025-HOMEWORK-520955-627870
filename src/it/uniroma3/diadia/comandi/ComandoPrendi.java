package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
    private String    nome_attrezzo;
    private IOConsole ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
		if (nome_attrezzo == null) {
			this.ioconsole.mostraMessaggio("Quale attrezzo vuoi prendere? Specifica un nome di attrezzo");
			return;
		}
		Attrezzo attrezzo = null;
		attrezzo = partita.getStanzaCorrente().getAttrezzo(nome_attrezzo);
		if (attrezzo == null) {
			this.ioconsole.mostraMessaggio("Attrezzo inesistente nella stanza");
		} else {
			partita.prendiAttrezzo(attrezzo);
		}
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
    public void setIOConsole(IOConsole ioconsole) {
      this.ioconsole = ioconsole;
    }
}
