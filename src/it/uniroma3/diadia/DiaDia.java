package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca.
 *
 * Questa e' la classe principale crea e istanzia tutte le altre.
 *
 * @author docente di POO
 *         (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = "" +
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n" +
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n" +
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n" +
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n" +
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n" +
			"Comandi disponibilii 'vai' 'aiuto' 'fine' 'prendi' 'posa'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole ioconsole;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione;
		ioconsole = new IOConsole();


		ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = ioconsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} 
		else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} 
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} 
		else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} 
		else {
			ioconsole.mostraMessaggio("Comando sconosciuto");
		}

		if (this.partita.isFinita()) {
			if (this.partita.vinta()) {
				ioconsole.mostraMessaggio("Hai vinto!");
			}
			else {
				ioconsole.mostraMessaggio("Hai perso!");
			}
			return true;
		}

		return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) {
			ioconsole.mostraMessaggio("Dove vuoi andare ? Specifica una direzione");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			ioconsole.mostraMessaggio("Direzione inesistente");
			return;
		}
		this.partita.setStanzaCorrente(prossimaStanza);
		stampa_stato_partita();
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		ioconsole.mostraMessaggio("");
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioconsole.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			ioconsole.mostraMessaggio("Quale attrezzo vuoi prendere? Specifica un nome di attrezzo");
			return;
		}
		Attrezzo attrezzo = null;
		attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			ioconsole.mostraMessaggio("Attrezzo inesistente nella stanza");
			return;
		}
		this.partita.prendiAttrezzo(attrezzo);
		stampa_stato_partita();
	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			ioconsole.mostraMessaggio("Quale attrezzo vuoi posare? Specifica un nome di attrezzo");
			return;
		}
		Attrezzo attrezzo = null;
		attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			ioconsole.mostraMessaggio("Attrezzo inesistente nella borsa");
			return;
		}
		this.partita.posaAttrezzo(attrezzo);
		stampa_stato_partita();
	}

	private void stampa_stato_partita() {
		int cfu = this.partita.getCfu();
		this.partita.setCfu(cfu-1);
		ioconsole.mostraMessaggio(partita.getStanzaCorrente());
		ioconsole.mostraMessaggio(partita.getGiocatore().getBorsa());
		ioconsole.mostraMessaggio("cfu: " + cfu);
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}