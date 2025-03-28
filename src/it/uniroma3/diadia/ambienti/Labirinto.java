/**
 * 
 */
package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * gestisci il labirinto di stanze
 */
public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	public Labirinto() {
		this.crea_stanze();
	}
	
	/*
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public void crea_stanze() {
		
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna", 9);
		Attrezzo osso = new Attrezzo("osso", 2);
		Attrezzo ossino = new Attrezzo("ossino", 1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		/*
		 * FIX cla 25/03/2025
		 * schema di collegamento
		 *              bibli
		 *                |
		 *  <-  lab  -  atrio  -  N11 ->
		 *                |
		 *           \-  N10  -/
		 */
        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(ossino);

		// il gioco comincia nell'atrio
        this.stanzaCorrente = atrio;  
		this.stanzaVincente = biblioteca;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
