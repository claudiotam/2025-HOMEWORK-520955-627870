package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public abstract class Personaggio {
    private String nome;
    private String presentazione;
    private boolean haSalutato;

    public Personaggio(String nome, String presentazione) {
        this.nome = nome;
        this.presentazione = presentazione;
        this.haSalutato = false;
    }

    abstract public String agisci(Partita partita);

    public String getNome() {
        return this.nome;
    }

    public boolean haSalutato() {
        return this.haSalutato;
    }

    public String saluta() {
        String risposta = "Ciao, io sono " + this.getNome() + ". ";
        if (!haSalutato) risposta += this.presentazione;
        else risposta += this.presentazione;
        this.haSalutato = true;
        return risposta;
    }

    @Override
    public String toString() {
        return "<oggetto Personaggio " + this.nome + ">";
    }
}