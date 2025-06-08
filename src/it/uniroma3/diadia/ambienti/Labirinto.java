/**
 * 
 */
package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.CiboPreferitoCane;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * gestisci il labirinto di stanze
 */
public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> stanze;
	private Stanza stanzaUltimaAggiunta;

	public Labirinto() {
		this.stanze = new HashMap<String, Stanza> ();
	}
	
	/*
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public void aggiungi_stanze_default() {
		
		/* crea stanze del labirinto iniziali */
		this.addStanzaCorrente("Atrio");
		this.addAttrezzo("osso"  , 2);
		this.addAttrezzo("ossino", 1);
		this.addAttrezzo("ppt"   , 4);

		/* crea stanze del labirinto iniziali */
		this.addStanzaVincente("Biblioteca");

		/* crea stanze del labirinto normali */
		this.addStanza("Laboratorio Campus");
		
		this.addStanza("Aula N10");

		this.addStanza("Aula N11");
		this.addAttrezzo("lanterna",   9);
		this.addAttrezzo(  "bidone",   9);
		this.addAttrezzo( "armadio", 500);
		this.addAttrezzo       (new CiboPreferitoCane());
		this.addCane();
		this.addMago();
    
		/* crea stanze del labirinto speciali: magiche */
		this.addStanzaMagica("LabIA");
		this.addAttrezzo("chiave",  4);

		/* crea stanze del labirinto speciali: buie */
		this.addStanzaBuia("Magazzino");

		/* crea stanze del labirinto speciali: bloccate */
		this.addStanzaBloccata("Presidenza");
		
		/* collega le stanze */
		this.addAdiacenza("Laboratorio Campus", Direzione.NORD , null         );
		this.addAdiacenza("Laboratorio Campus", Direzione.SUD  , "LabIA"      );
		this.addAdiacenza("Laboratorio Campus", Direzione.EST  , "Atrio"      );
		this.addAdiacenza("Laboratorio Campus", Direzione.OVEST, "Aula N11"   );

		this.addAdiacenza("Atrio"             , Direzione.NORD , "Biblioteca" );
		this.addAdiacenza("Atrio"             , Direzione.SUD  , "Magazzino"  );
		this.addAdiacenza("Atrio"             , Direzione.EST  , "Aula N11"   );
		this.addAdiacenza("Atrio"             , Direzione.OVEST, "Laboratorio Campus");

		this.addAdiacenza("Aula N11"          , Direzione.NORD , null          );
		this.addAdiacenza("Aula N11"          , Direzione.SUD  , "Presidenza"  );
		this.addAdiacenza("Aula N11"          , Direzione.EST  , "Laboratorio Campus");
		this.addAdiacenza("Aula N11"          , Direzione.OVEST, "Atrio"       );

		this.addAdiacenza("LabIA"             , Direzione.NORD , "Laboratorio Campus");
		this.addAdiacenza("LabIA"             , Direzione.SUD  , "Aula N10"    );
		this.addAdiacenza("LabIA"             , Direzione.EST  , "Magazzino"   );
		this.addAdiacenza("LabIA"             , Direzione.OVEST, "Presidenza"  );

		this.addAdiacenza("Magazzino"         , Direzione.NORD , "Atrio"       );
		this.addAdiacenza("Magazzino"         , Direzione.SUD  , "Aula N10"    );
		this.addAdiacenza("Magazzino"         , Direzione.EST  , "Presidenza"  );
		this.addAdiacenza("Magazzino"         , Direzione.OVEST, "LabIA"       );

		this.addAdiacenza("Presidenza"        , Direzione.NORD , "Aula N11"    );
		this.addAdiacenza("Presidenza"        , Direzione.SUD  , "Aula N10"    );
		this.addAdiacenza("Presidenza"        , Direzione.EST  , "LabIA"       );
		this.addAdiacenza("Presidenza"        , Direzione.OVEST, "Magazzino"   );

		this.addAdiacenza("Aula N10"          , Direzione.NORD , "Magazzino"   );
		this.addAdiacenza("Aula N10"          , Direzione.SUD  , null          );
		this.addAdiacenza("Aula N10"          , Direzione.EST  , "Presidenza"  );
		this.addAdiacenza("Aula N10"          , Direzione.OVEST, "LabIA"       );

		/*
		 * schema di collegamento
		 *                bibli
		 *                  |
		 *  <- laborat -  atrio  -  N11  ->
		 *        |         |        |
		 *  <-  labIA  -  magaz  - presi ->
		 *        |         |        |
		 *        \    -   N10   -   /
		 */
		
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

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	//questi metodi costruiscono un labirinto pezzo dopo pezzo
    //il labirinto viene custodito dentro la classe, e non esce finché non si chiama getLabirinto

    
	public Labirinto getLabirinto() {
		return this;
	}

	public Labirinto addStanza(Stanza st) {
		this.stanze.put(st.getNome(), st);
		this.stanzaUltimaAggiunta = st;
		return this;
	}

	public Labirinto addStanzaCorrente(Stanza st) {
		this.stanzaCorrente = st;
		return this.addStanza(st);
	}

	public Labirinto addStanzaIniziale(Stanza st) {
		this.stanzaIniziale = st;
		return this.addStanza(st);
	}

	public Labirinto addStanzaVincente(Stanza st) {
		this.stanzaVincente = st;
		return this.addStanza(st);
	}

	public Labirinto addStanzaCorrente(String nome_stanza) {
		return this.addStanzaCorrente(this.nomeStanza2ObjectStanza(nome_stanza));
	}

	public Labirinto addStanzaIniziale(String nome_stanza) {
		return this.addStanzaIniziale(this.nomeStanza2ObjectStanza(nome_stanza));
	}

	public Labirinto addStanzaVincente(String nome_stanza) {
		return this.addStanzaVincente(this.nomeStanza2ObjectStanza(nome_stanza));
	}

	private Stanza nomeStanza2ObjectStanza(String nome_stanza) {
		//se il nome della stanza è già nella lista, rifiuta l'inserimento e ritorna il preesistente
		if (this.stanze.containsKey(nome_stanza)) return this.stanze.get(nome_stanza);

		//se il nome della stanza non c'è, crea una nuova stanza, mettila in lista, ritornala
		Stanza nuova = new Stanza(nome_stanza);
		this.stanze.put(nome_stanza, nuova);
		this.stanzaUltimaAggiunta = nuova;
		return nuova;

	}

	public Labirinto addStanza(String nome_stanza) {
		nomeStanza2ObjectStanza(nome_stanza);
		return this;
	}

	public Labirinto addAttrezzo(Attrezzo at) {
		//aggiungi un attrezzo all'ultima stanza aggiunta
		return this.addAttrezzo(this.stanzaUltimaAggiunta, at);
	}

	public Labirinto addAttrezzo(Stanza st, Attrezzo at) {
		//aggiungi attrezzo at alla stanza st
		//il chiamante potrebbe tranquillamente chiamarselo da solo <Stanza>.addAttrezzo
		st.addAttrezzo(at);
		return this;
	}

	public Labirinto addAttrezzo(String nome_attrezzo, int peso_attrezzo) {
		return this.addAttrezzo(new Attrezzo(nome_attrezzo, peso_attrezzo));
	}

	public Labirinto addAdiacenza(Stanza st_to, Direzione direzione) {
		//aggiungi adiacenza all'ultima stanza aggiunta
		this.stanzaUltimaAggiunta.impostaStanzaAdiacente(direzione,  st_to);
		return this;
	}

	public Labirinto addAdiacenzaParamInvert(String nome_from, String nome_to, String nome_direzione) {
		return this.addAdiacenza(nome_from, Direzione.valueOf(nome_direzione.toUpperCase()), nome_to);
	}

	public Labirinto addAdiacenza(Stanza st_from, Direzione direzione, Stanza st_to) {
		st_from.impostaStanzaAdiacente(direzione,  st_to);
		return this;
	}

	public Labirinto addAdiacenza(Stanza st_from, String nome_direzione, Stanza st_to) {
		return this.addAdiacenza(st_from, Direzione.valueOf(nome_direzione), st_to);
	}

	public Labirinto addAdiacenza(String nome_from, Direzione direzione, String nome_to) {
		if (nome_to == null) return this;
		this.checkNomeStanzaOccupato(nome_from);
		this.checkNomeStanzaOccupato(nome_to);
		return this.addAdiacenza(this.stanze.get(nome_from), direzione, this.stanze.get(nome_to));
	}

	public Labirinto addAdiacenza(String nome_from, String nome_direzione, String nome_to) {
		return this.addAdiacenza(nome_from, Direzione.valueOf(nome_direzione), nome_to);
	}

	public Map<String, Stanza> getMappaStanze() {
		return this.stanze;
	}

	public void checkNomeStanzaLibero(String nome_st) {
		if (this.stanze.containsKey(nome_st)) {
			throw new IllegalArgumentException("il nome per la stanza ("+nome_st+") è occupato, ma dovrebbe essere libero");
		}
	}
	
	public void checkNomeStanzaOccupato(String nome_st) {
		if (!this.stanze.containsKey(nome_st)) {
			throw new IllegalArgumentException("il nome per la stanza ("+nome_st+") è libero, ma dovrebbe essere occupato");
		}
	}
	
	public Labirinto addStanzaMagica(String nome_st) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaMagica(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaMagica(String nome_st, Integer soglia_magica) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaMagica(nome_st, soglia_magica);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBloccata(String nome_st) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaBloccata(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBloccata(String nome_st, String nome_attrezzo_anti_bloc, String nome_direz_bloccata) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaBloccata(nome_st, nome_attrezzo_anti_bloc, Direzione.valueOf(nome_direz_bloccata.toUpperCase()));
		return this.addStanza(st);
	}

	public Labirinto addStanzaBuia(String nome_st) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaBuia(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBuia(String nome_st, String nome_attrezzo_anti_buio) {
		checkNomeStanzaLibero(nome_st);
		Stanza st = new StanzaBuia(nome_st, nome_attrezzo_anti_buio);
		return this.addStanza(st);
	}

	//metodi relativi a mago, strega, cane
	public Labirinto addMago() {
		//aggiungi un mago all'ultima stanza aggiunta
		return this.addMago(this.stanzaUltimaAggiunta);
	}

	public Labirinto addMago(Stanza st) {
		//aggiungi mago at alla stanza st
		//il chiamante potrebbe tranquillamente chiamarselo da solo <Stanza>.addMago
		st.setPersonaggio(new Mago());
		return this;
	}

	public Labirinto addStrega() {
		//aggiungi un mago all'ultima stanza aggiunta
		return this.addStrega(this.stanzaUltimaAggiunta);
	}

	public Labirinto addStrega(Stanza st) {
		//aggiungi mago at alla stanza st
		//il chiamante potrebbe tranquillamente chiamarselo da solo <Stanza>.addMago
		st.setPersonaggio(new Strega());
		return this;
	}

	public Labirinto addCane() {
		//aggiungi un mago all'ultima stanza aggiunta
		return this.addCane(this.stanzaUltimaAggiunta);
	}

	public Labirinto addCane(Stanza st) {
		//aggiungi mago at alla stanza st
		//il chiamante potrebbe tranquillamente chiamarselo da solo <Stanza>.addMago
		st.setPersonaggio(new Cane());
		return this;
	}

}
