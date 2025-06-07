package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class PersonaggioFake extends Personaggio {
    public PersonaggioFake(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public void agisci(Partita partita) {
        return;
    }
}