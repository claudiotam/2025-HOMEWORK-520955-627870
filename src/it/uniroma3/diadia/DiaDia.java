package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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
			"Comandi disponibili 'vai' 'aiuto' 'fine' 'prendi' 'posa'.";

	private Partita partita;
	private IO ioconsole;
	private FabbricaDiComandiFisarmonica factory;

	public DiaDia(IO ioconsole) {
		this.partita   = new Partita();
		this.ioconsole = ioconsole;
		this.factory   = new FabbricaDiComandiFisarmonica(ioconsole);
	}

	public void gioca() {
		String istruzione;
		
		this.ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.ioconsole.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		comandoDaEseguire = this.factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		partita.getGiocatore().togliUnCfu();
		stampa_stato_partita();
		
		if (this.partita.isFinita()) { 
			if (this.partita.vinta()) {
				this.ioconsole.mostraMessaggio("Hai vinto!");
			}
			if (this.partita.getGiocatore().hasZeroCfu()) {
				this.ioconsole.mostraMessaggio("Hai perso! Non hai manco un CFU!");
			}
			this.ioconsole.mostraMessaggio("Grazie di aver giocato!");
			return true;
		}
		return false;
	}

	private void stampa_stato_partita() {
		this.ioconsole.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		this.ioconsole.mostraMessaggio(this.partita.getGiocatore());
	}

	public static void main(String[] argc) {
		//String[] messaggi_in = new String[] {"vai est", "vai est", "fine", "fine", "fine" };
		//this.ioconsole = new IOSimulator(messaggi_in);
		IO ioconsole = new IOConsole();

		DiaDia gioco = new DiaDia(ioconsole);
		gioco.gioca();
	}

}