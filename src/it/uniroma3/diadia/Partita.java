package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	
    /**
     * 
     */
	public Partita(){
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
		this.finita    = false;
	}

	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaCorrente(stanzaCorrente);
	}

	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaCorrente();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		/*
		 * finita: il giocatore ha scelto di finire tramite riga di comando
		 * vinta:  il giocatore è nella stanza obiettivo
		 * persa:  il giocatore ha perso tutti i cfu
		 */
		return finita || this.vinta() || (this.giocatore.hasZeroCfu());
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Prendi attrezzo, ossia togli attrezzo alla stanza e metti attrezzo nel giocatore
	 */
	public boolean prendiAttrezzo(Attrezzo attrezzo) {
		boolean test = this.giocatore.addAttrezzo(attrezzo);
		if (!test) return false;
		this.getStanzaCorrente().removeAttrezzo(attrezzo);
		return true;
	}

	/**
	 * Posa attrezzo, ossia togli attrezzo dal giocatore e metti attrezzo nella stanza
	 */
	public boolean posaAttrezzo(Attrezzo attrezzo) {
		boolean test = this.getStanzaCorrente().addAttrezzo(attrezzo);
		if (!test) return false;
		this.giocatore.removeAttrezzo(attrezzo);
		return true;
	}

}
