package it.uniroma3.diadia.attrezzi;

import java.util.Objects;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Inverte il nome identificatore dell'attrezzo (chiave->evaihc)
	 * @return il nome identificatore dell'attrezzo invertito
	 */
	public void invertiNomeAumentaPeso() {
		this.nome = new StringBuilder(this.nome).reverse().toString();
		this.peso *= 2;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	public void dimezzaPeso() {
		this.peso = ( this.peso + 1 ) / 2;
	}
	
	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	/*
	 * fai il possibile per bloccare la creazione di due attrezzi con lo stesso nome
	 */
	@Override
	public boolean equals(Object comp) {
		if (this == comp) return true;
		if (!(comp instanceof Attrezzo)) return false;
		Attrezzo that = (Attrezzo) comp;
		return this.getNome().equalsIgnoreCase(that.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getNome().toLowerCase());
	}
}