package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ListaAttrezzi;

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
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private ListaAttrezzi listaAttrezzi;

	private Stanza[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;

	private String[] direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.listaAttrezzi = new ListaAttrezzi(NUMERO_MASSIMO_ATTREZZI);
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
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

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.listaAttrezzi.hasAttrezzo(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.listaAttrezzi.getAttrezzo(nomeAttrezzo);
	}

	public boolean removeAttrezzo(Attrezzo attrezzo_da_rimuovere) {
		return this.listaAttrezzi.removeAttrezzo(attrezzo_da_rimuovere);
	}

    /*
     * ritorna un array di stringhe che rappresentano le direzioni possibili
     */
	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String getDescrizione() {
		StringBuilder s = new StringBuilder();
		s.append("sei in: " + this.nome);
		s.append("\nUscite: ");
		for (String direzione : this.direzioni) {
			if (direzione != null) {
				s.append(" " + direzione);
			}
		}
		if (this.listaAttrezzi.isEmpty()) s.append("\nNessun attrezzo nella stanza ");
		else {
			s.append("\nAttrezzi nella stanza: ");
			s.append(this.listaAttrezzi);
		}
		return s.toString();
	}

	/**
	 * stampa l'oggetto stanza in modo carino
	 */
	public String toString() {
		return "<oggetto Stanza " + this.nome + ">";		
	}
	
}