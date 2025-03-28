package it.uniroma3.diadia.attrezzi;

public class ListaAttrezzi {
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    private int numeroMassimoAttrezzi;

    public ListaAttrezzi(int numeroMassimoAttrezzi) {
        attrezzi = new Attrezzo[numeroMassimoAttrezzi];
        this.numeroMassimoAttrezzi = numeroMassimoAttrezzi;
        this.numeroAttrezzi = 0;
    }

	/**
	 * 
	 */
    public int getPeso() {
        int peso = 0;
        for (int i = 0; i < this.numeroAttrezzi; i++)
            peso += this.attrezzi[i].getPeso();
        return peso;
    }

	/**
	 * Mette un attrezzo nella lista.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella lista.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (numeroAttrezzi >= numeroMassimoAttrezzi) {
            return false;
        }
        this.attrezzi[numeroAttrezzi++] = attrezzo;
        return true;
    }

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.getAttrezzo(nomeAttrezzo) != null;
    }
    
	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 *         null se l'attrezzo non e' presente.
	 */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
                return this.attrezzi[i];
            }
        }
        return null;
    }

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * Metodo cancellato poichè mai utilizzato
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
    //public Attrezzo[] getAttrezzi() {}

	/**
	 * Rimuove un attrezzo dalla lista (ricerca in base al riferimento).
	 * rimuovi TUTTE le occorrenze, anche multiple
	 * ritorna true se hai rimosso almeno un attrezzo
	 * 
	 * @param Attrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
    public boolean removeAttrezzo(Attrezzo attrezzo_da_rimuovere) {
        int read = 0;
        int write = 0;
        int attrezzi_old_tot = this.numeroAttrezzi;
        boolean deleted = false;
        while (read < attrezzi_old_tot) {
            Attrezzo at = this.attrezzi[read++];
            if (at == attrezzo_da_rimuovere) continue;
            this.attrezzi[write++] = at;
        }
        this.numeroAttrezzi = write;
        /*
         * cancelliamo gli attrezzi non più indicizzabili (non è chiaro
         * se gli indici validi sono dati dai null o dai numeroAttrezzi,
         * manteniamo entrambi i modi attivi e funzionanti)
         */
        while (write < attrezzi_old_tot) {
            this.attrezzi[write++] = null;
            deleted = true;
        }
        return deleted;
    }

	/**
	 * ritorna vero se la lista è vuota
	 */
    public boolean isEmpty() {
        return numeroAttrezzi == 0;
    }

	/**
	 * stampa la lista attrezzi, un attrezzo alla volta, separati da spazi
	 */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numeroAttrezzi; i++) {
            s.append(attrezzi[i].toString() + " ");
        }
        return s.toString();
    }

}
