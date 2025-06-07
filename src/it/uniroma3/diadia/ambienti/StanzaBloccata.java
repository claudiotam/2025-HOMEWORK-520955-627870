package it.uniroma3.diadia.ambienti;

/*
 * La «stanza bloccata»: una delle direzioni della
 * stanza non può essere seguita a meno che nella
 * stanza non sia presente un oggetto con un nome
 * particolare (ad esempio "passepartout" o "piedediporco")
 */
public class StanzaBloccata extends Stanza {
    final static private String NOME_ATTREZZO_ANTI_BLOCCO_DEFAULT = "passepartout";
    final static private Direzione DIREZIONE_BLOCCATA_DEFAULT        = Direzione.EST;
    private String    nome_attrezzo_anti_blocco;
    private Direzione direzione_bloccata;

    public StanzaBloccata(String nome) {
        this(nome, NOME_ATTREZZO_ANTI_BLOCCO_DEFAULT, DIREZIONE_BLOCCATA_DEFAULT);
    }

    public StanzaBloccata(String nome, String nome_attrezzo_anti_blocco, Direzione direzione_bloccata) {
        super(nome);
        this.nome_attrezzo_anti_blocco = nome_attrezzo_anti_blocco;
        this.direzione_bloccata = direzione_bloccata;
    }

    @Override
    public Stanza getStanzaAdiacente(Direzione direzione) {
        if (direzione != this.direzione_bloccata || this.hasAttrezzoConNome(nome_attrezzo_anti_blocco)) {
            return super.getStanzaAdiacente(direzione);
        }
        else {
            return this;
        }
	}

    @Override
    public String getDescrizione() {
        return super.getDescrizione() + "\n" + 
        "Questa stanza potrebbe avere qualche direzione bloccata, ma sbloccabile da qualche attrezzo (forse il passepartout?)";
    }
}
