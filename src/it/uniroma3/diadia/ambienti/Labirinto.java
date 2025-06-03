/**
 * 
 */
package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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
		this.addStanzaCorrenteConNome("Atrio");
		this.addAttrezzoConNome(  "osso", 2);
		this.addAttrezzoConNome("ossino", 1);
		this.addAttrezzoConNome(   "ppt", 4);

		/* crea stanze del labirinto iniziali */
		this.addStanzaVincenteConNome("Biblioteca");

		/* crea stanze del labirinto normali */
		
		this.addStanzaConNome("Laboratorio Campus");
		
		this.addStanzaConNome("Aula N10");

		this.addStanzaConNome("Aula N11");
		this.addAttrezzoConNome("lanterna",   9);
		this.addAttrezzoConNome(  "bidone",   9);
		this.addAttrezzoConNome( "armadio", 500);
    
		/* crea stanze del labirinto speciali: magiche */
		this.addStanzaMagicaConNome("LabIA");
		this.addAttrezzoConNome("chiave",  4);

		/* crea stanze del labirinto speciali: buie */
		this.addStanzaBuiaConNome("Magazzino");

		/* crea stanze del labirinto speciali: bloccate */
		this.addStanzaBloccataConNome("Presidenza");
		
		/* collega le stanze */
		this.addAdiacenzaConNome("Laboratorio Campus", "nord" , null);
		this.addAdiacenzaConNome("Laboratorio Campus", "sud"  , "labIA"      );
		this.addAdiacenzaConNome("Laboratorio Campus", "est"  , "atrio"      );
		this.addAdiacenzaConNome("Laboratorio Campus", "ovest", "aulaN11"    );

		this.addAdiacenzaConNome("Atrio"             , "nord" , "Biblioteca" );
		this.addAdiacenzaConNome("Atrio"             , "sud"  , "Magazzino"  );
		this.addAdiacenzaConNome("Atrio"             , "est"  , "Aula N11"   );
		this.addAdiacenzaConNome("Atrio"             , "ovest", "Laboratorio Campus");

		this.addAdiacenzaConNome("Aula N11"          , "nord" , null          );
		this.addAdiacenzaConNome("Aula N11"          , "sud"  , "Presidenza"  );
		this.addAdiacenzaConNome("Aula N11"          , "est"  , "Laboratorio Campus");
		this.addAdiacenzaConNome("Aula N11"          , "ovest", "Atrio"       );

		this.addAdiacenzaConNome("LabIA"             , "nord" , "Laboratorio Campus");
		this.addAdiacenzaConNome("LabIA"             , "sud"  , "Aula N10"    );
		this.addAdiacenzaConNome("LabIA"             , "est"  , "Magazzino"   );
		this.addAdiacenzaConNome("LabIA"             , "ovest", "Presidenza"  );

		this.addAdiacenzaConNome("Magazzino"         , "nord" , "Atrio"       );
		this.addAdiacenzaConNome("Magazzino"         , "sud"  , "Aula N10"    );
		this.addAdiacenzaConNome("Magazzino"         , "est"  , "Presidenza"  );
		this.addAdiacenzaConNome("Magazzino"         , "ovest", "LabIA"       );

		this.addAdiacenzaConNome("Presidenza"        , "nord" , "Aula N11"    );
		this.addAdiacenzaConNome("Presidenza"        , "sud"  , "Aula N10"    );
		this.addAdiacenzaConNome("Presidenza"        , "est"  , "LabIA"       );
		this.addAdiacenzaConNome("Presidenza"        , "ovest", "Magazzino"   );

		this.addAdiacenzaConNome("Aula N10"          , "nord" , "Magazzino"   );
		this.addAdiacenzaConNome("Aula N10"          , "sud"  , null          );
		this.addAdiacenzaConNome("Aula N10"          , "est"  , "Presidenza"  );
		this.addAdiacenzaConNome("Aula N10"          , "ovest", "LabIA"       );

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

	private Stanza addStanzaConNome_internal(String nome_stanza) {
		//se il nome della stanza è già nella lista, rifiuta l'inserimento e ritorna il preesistente
		if (this.stanze.containsKey(nome_stanza)) return this.stanze.get(nome_stanza);

		//se il nome della stanza non c'è, crea una nuova stanza, mettila in lista, ritornala
		Stanza nuova = new Stanza(nome_stanza);
		this.stanze.put(nome_stanza, nuova);
		this.stanzaUltimaAggiunta = nuova;
		return nuova;

	}

	public Labirinto addStanzaConNome(String nome_stanza) {
		addStanzaConNome_internal(nome_stanza);
		return this;
	}

	public Labirinto addStanzaCorrenteConNome(String nome_stanza) {
		return this.addStanzaCorrente(this.addStanzaConNome_internal(nome_stanza));
	}

	public Labirinto addStanzaInizialeConNome(String nome_stanza) {
		return this.addStanzaIniziale(this.addStanzaConNome_internal(nome_stanza));
	}

	public Labirinto addStanzaVincenteConNome(String nome_stanza) {
		return this.addStanzaVincente(this.addStanzaConNome_internal(nome_stanza));
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

	public Labirinto addAttrezzoConNome(String nome_attrezzo, int peso_attrezzo) {
		return this.addAttrezzo(new Attrezzo(nome_attrezzo, peso_attrezzo));
	}

	public Labirinto addAdiacenza(Stanza st_to, String direzione) {
		//aggiungi adiacenza all'ultima stanza aggiunta
		this.stanzaUltimaAggiunta.impostaStanzaAdiacente(direzione,  st_to);
		return this;
	}

	public Labirinto addAdiacenza(Stanza st_from, String direzione, Stanza st_to) {
		st_from.impostaStanzaAdiacente(direzione,  st_to);
		return this;
	}


	public Labirinto addAdiacenzaConNomeParamInvert(String nome_from, String nome_to, String direzione) {
		return this.addAdiacenzaConNome(nome_from, direzione, nome_to);
	}

	public Labirinto addAdiacenzaConNome(String nome_from, String direzione, String nome_to) {
        Stanza st_from = this.stanze.get(nome_from);
		Stanza st_to   = this.stanze.get(nome_to);

		if ((st_from == null) || (st_to == null)) {
			//la stanza non è nella lista delle stanze note
			//todo, lancia una eccezione
			return this;
		}

		return this.addAdiacenza(st_from, direzione, st_to);
	}

	public Map<String, Stanza> getMappaStanze() {
		return this.stanze;
	}

	public Labirinto addStanzaMagicaConNome(String nome_st) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaMagica(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaMagicaConNome(String nome_st, Integer soglia_magica) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaMagica(nome_st, soglia_magica);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBloccataConNome(String nome_st) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaBloccata(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBloccataConNome(String nome_st, String nome_attrezzo_anti_bloc, String direz_bloccata) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaBloccata(nome_st, nome_attrezzo_anti_bloc, direz_bloccata);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBuiaConNome(String nome_st) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaBuia(nome_st);
		return this.addStanza(st);
	}

	public Labirinto addStanzaBuiaConNome(String nome_st, String nome_attrezzo_anti_buio) {
		//non posso cambiare una stanza da speciale a normale, il metodo fallisce subito (TODO: lanciare eccez)
		if (this.stanze.containsKey(nome_st)) return this;
		Stanza st = new StanzaBuia(nome_st, nome_attrezzo_anti_buio);
		return this.addStanza(st);
	}

}
