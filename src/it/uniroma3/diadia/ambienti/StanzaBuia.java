package it.uniroma3.diadia.ambienti;

/*La «stanza buia»: se nella stanza non è presente un
 * attrezzo con un nome particolare (ad esempio
 * "lanterna") il metodo getDescrizione() di una
 * stanza buia ritorna la stringa "qui c'è un buio pesto"
 */
public class StanzaBuia extends Stanza {
    final static private String NOME_ATTREZZO_ANTI_BUIO_DEFAULT = "lanterna";
    private String nome_attrezzo_anti_buio;

    public StanzaBuia(String nome) {
        this(nome, NOME_ATTREZZO_ANTI_BUIO_DEFAULT);
    }

    public StanzaBuia(String nome, String nome_attrezzo_anti_buio) {
        super(nome);
        this.nome_attrezzo_anti_buio = nome_attrezzo_anti_buio;
    }

    @Override
    public String getDescrizione() {
        if (this.hasAttrezzoConNome(nome_attrezzo_anti_buio)) {
            return super.getDescrizione();
        }
        else {
            return "qui c'è un buio pesto (forse serve una lanterna?)";
        }
    }
}
