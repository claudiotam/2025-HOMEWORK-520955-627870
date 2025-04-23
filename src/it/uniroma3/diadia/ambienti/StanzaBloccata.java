package it.uniroma3.diadia.ambienti;

/*
 * La «stanza bloccata»: una delle direzioni della
 * stanza non può essere seguita a meno che nella
 * stanza non sia presente un oggetto con un nome
 * particolare (ad esempio "passepartout" o "piedediporco")
 */
public class StanzaBloccata extends Stanza {
    final static private String NOME_ATTREZZO_ANTI_BLOCCO_DEFAULT = "passepartout";
    final static private String DIREZIONE_BLOCCATA_DEFAULT        = "est";
    private String nome_attrezzo_anti_blocco;
    private String direzione_bloccata;

    public StanzaBloccata(String nome) {
        this(nome, NOME_ATTREZZO_ANTI_BLOCCO_DEFAULT, DIREZIONE_BLOCCATA_DEFAULT);
    }

    public StanzaBloccata(String nome, String nome_attrezzo_anti_blocco, String direzione_bloccata) {
        super(nome);
        this.nome_attrezzo_anti_blocco = nome_attrezzo_anti_blocco;
        this.direzione_bloccata = direzione_bloccata;
    }

    @Override
    public Stanza getStanzaAdiacente(String direzione) {
        if (!direzione.equalsIgnoreCase(this.direzione_bloccata) || this.hasAttrezzo(nome_attrezzo_anti_blocco)) {
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
