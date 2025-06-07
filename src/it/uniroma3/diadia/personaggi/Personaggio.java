package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class Personaggio {
    private String  nome;
    private String  presentazione;
    private boolean haSalutato;
    private String  risposta;

    public Personaggio(String nome, String presentazione) {
        this.nome = nome;
        this.presentazione = presentazione;
        this.haSalutato = false;
    }

    abstract public void agisci(Partita partita);

    public String getRisposta() {
        return this.risposta;
    }
    protected void setRisposta(String s) {
        this.risposta = s;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean haSalutato() {
        return this.haSalutato;
    }

    public void preparaRispSaluto() {
        if (!haSalutato) this.risposta = "Ciao, il mio nome è " + this.getNome() + ". " + this.presentazione;
        else             this.risposta = "Ci siamo già salutati una volta. Non ti saluterò più. ";
        this.haSalutato = true;
    }

    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        this.risposta = "Ti ringrazio dell'attrezzo " + attrezzo.getNome() + "! Lo custodirò. ";
        partita.getGiocatore().removeAttrezzo(attrezzo);
    }

    @Override
    public String toString() {
        return "<oggetto Personaggio " + this.nome + ">";
    }
}