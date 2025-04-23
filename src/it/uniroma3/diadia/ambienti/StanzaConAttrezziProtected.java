package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.ListaAttrezzi;

public class StanzaConAttrezziProtected extends Stanza {
    protected ListaAttrezzi listaAttrezzi;

    public StanzaConAttrezziProtected(String nome) {
        super(nome);
    }
}
