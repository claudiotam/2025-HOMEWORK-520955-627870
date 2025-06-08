package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class NPC_Fake extends NPC {
    public NPC_Fake(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public void agisci(Partita partita) {
        return;
    }
}