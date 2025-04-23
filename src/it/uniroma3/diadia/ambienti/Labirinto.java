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
    	Attrezzo lanterna = new Attrezzo("lanterna",     9);
		Attrezzo osso     = new Attrezzo("osso",         2);
		Attrezzo ossino   = new Attrezzo("ossino",       1);
    	Attrezzo armadio  = new Attrezzo("armadio",    500);
    	Attrezzo bidone   = new Attrezzo("bidone",       9);
    	Attrezzo chiave   = new Attrezzo("chiave",       4);
    	Attrezzo ppt      = new Attrezzo("passepartout", 4);
		
		/* crea stanze del labirinto normali */
		Stanza bibliot = new Stanza("Biblioteca");

		Stanza laborat = new Stanza       ("Laboratorio Campus");
		Stanza atrio   = new Stanza       ("Atrio");
		Stanza aulaN11 = new Stanza       ("Aula N11");

		Stanza aulaN10 = new Stanza       ("Aula N10");
		
		/* crea stanze del labirinto speciali */
		Stanza labIA   = new StanzaMagica  ("LabIA");
		Stanza magaz   = new StanzaBuia    ("Magazzino");
		Stanza presi   = new StanzaBloccata("Presidenza");
		
		/* collega le stanze */
		laborat.impostaStanzaAdiacente("nord", null);
		laborat.impostaStanzaAdiacente("sud",   labIA  );
		laborat.impostaStanzaAdiacente("est",   atrio  );
		laborat.impostaStanzaAdiacente("ovest", aulaN11);
		
		atrio  .impostaStanzaAdiacente("nord",  bibliot);
		atrio  .impostaStanzaAdiacente("sud",   magaz  );
		atrio  .impostaStanzaAdiacente("est",   aulaN11);
		atrio  .impostaStanzaAdiacente("ovest", laborat);
		
		aulaN11.impostaStanzaAdiacente("nord", null);
		aulaN11.impostaStanzaAdiacente("sud",   presi  );
		aulaN11.impostaStanzaAdiacente("est",   laborat);
		aulaN11.impostaStanzaAdiacente("ovest", atrio  );
		
		labIA  .impostaStanzaAdiacente("nord",  laborat);
		labIA  .impostaStanzaAdiacente("sud",   aulaN10);
		labIA   .impostaStanzaAdiacente("est",  magaz  );
		labIA  .impostaStanzaAdiacente("ovest", presi  );
		
		magaz  .impostaStanzaAdiacente("nord",  atrio  );
		magaz  .impostaStanzaAdiacente("sud",   aulaN10);
		magaz  .impostaStanzaAdiacente("est",   presi  );
		magaz  .impostaStanzaAdiacente("ovest", labIA  );
		
		presi  .impostaStanzaAdiacente("nord",  aulaN11);
		presi  .impostaStanzaAdiacente("sud",   aulaN10);
		presi  .impostaStanzaAdiacente("est",   labIA  );
		presi  .impostaStanzaAdiacente("ovest", magaz  );
		
		aulaN10.impostaStanzaAdiacente("nord",  magaz  );
		aulaN10.impostaStanzaAdiacente("sud",   null);
		aulaN10.impostaStanzaAdiacente("est",   presi  );
		aulaN10.impostaStanzaAdiacente("ovest", labIA  );
		
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
		
        /* pone gli attrezzi nelle stanze */
		aulaN11.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(ossino);
		atrio.addAttrezzo(ppt);
		aulaN11.addAttrezzo(bidone);
		aulaN11.addAttrezzo(armadio);
		labIA.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
        this.stanzaCorrente = atrio;  
		this.stanzaVincente = bibliot;
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
