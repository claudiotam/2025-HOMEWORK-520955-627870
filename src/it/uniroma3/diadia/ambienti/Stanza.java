package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ListaAttrezzi;
import it.uniroma3.diadia.personaggi.Personaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	private String nome;
	private ListaAttrezzi listaAttrezzi;
	private Map<Direzione, Stanza> direz2stanza;

	private Personaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.listaAttrezzi = new ListaAttrezzi();
		this.direz2stanza = new HashMap<Direzione, Stanza>();
	}

	public void setPersonaggio(Personaggio personaggio) {
		this.personaggio = personaggio;
	}

	public Personaggio getPersonaggio() {
		return this.personaggio;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		// blocca la creazione di più di quattro direzioni (perché? boh? nei test è così)
		if (this.direz2stanza.size() >= NUMERO_MASSIMO_DIREZIONI) return;

		this.direz2stanza.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.direz2stanza.get(direzione);
	}

	public Stanza getStanzaAdiacenteConNome(String nome_direzione) {
		return this.getStanzaAdiacente(Direzione.valueOf(nome_direzione.toUpperCase()));
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

    /*
     * forwarding dei metodi add-has-get-remove
     */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.listaAttrezzi.addAttrezzo(attrezzo);
	}

	public boolean hasAttrezzoConNome(String nomeAttrezzo) {
		return this.listaAttrezzi.hasAttrezzo(nomeAttrezzo);
	}

	public Attrezzo getAttrezzoConNome(String nomeAttrezzo) {
		return this.listaAttrezzi.getAttrezzoConNome(nomeAttrezzo);
	}

	public boolean removeAttrezzo(Attrezzo attrezzo_da_rimuovere) {
		return this.listaAttrezzi.removeAttrezzo(attrezzo_da_rimuovere);
	}

	public List<Attrezzo> getAttrezzi() {
		return this.listaAttrezzi.getAttrezzi();
	}

    /*
     * ritorna una lista di stringhe che rappresentano le direzioni possibili
     */
	public List<Direzione> getDirezioni() {
		return new ArrayList<Direzione> (this.direz2stanza.keySet());
	}
	public List<String> getDirezioniComeNome() {
		ArrayList<String> ret = new ArrayList<String> ();
		for (Direzione direzione : getDirezioni()) {
			ret.add(direzione.name().toLowerCase());
		}
		return ret;
	}

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.direz2stanza;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String getDescrizione() {
		StringBuilder s = new StringBuilder();

		//nome stanza
		s.append("sei in: " );
		s.append(this.nome);

		//lista uscite
		s.append("\nUscite: ");
		if (this.getDirezioni().isEmpty()) s.append("<nessuna> ");
		else for (Direzione direzione : this.getDirezioni()) {
				s.append(direzione.name().toLowerCase() + " ");
		}

		//lista attrezzi
		s.append("\nAttrezzi nella stanza: ");
		if (this.listaAttrezzi.isEmpty()) s.append("<nessuno> ");
		else                              s.append(this.listaAttrezzi);
		
		//lista personaggi
		s.append("\nPersonaggi nella stanza: ");
		if (this.personaggio == null) s.append("<nessuno> ");
		else                          s.append(this.personaggio.getNome());

		//fin
		return s.toString();
	}

	public String getDescrizioneDettagliata() {
		StringBuilder s = new StringBuilder();
		if (this.listaAttrezzi.isEmpty()) s.append("\nNessun attrezzo nella stanza ");
		else {
			s.append("\nAttrezzi nella stanza: ");
			s.append(this.listaAttrezzi.toStringContenutoDettagliato());
		}
		return s.toString();
	}

	/**
	 * stampa l'oggetto stanza in modo carino
	 */
	public String toString() {
		return "<oggetto Stanza " + this.nome + ">";		
	}
	
	/*
	 * fai il possibile per bloccare la creazione di due stanze con lo stesso nome
	 */
	@Override
	public boolean equals(Object comp) {
		if (this == comp) return true;
		if (!(comp instanceof Stanza)) return false;
		Stanza that = (Stanza) comp;
		return this.getNome().equalsIgnoreCase(that.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getNome().toLowerCase());
	}
}