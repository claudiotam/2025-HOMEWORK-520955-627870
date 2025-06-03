package it.uniroma3.diadia.attrezzi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ListaAttrezzi {
    private HashSet<Attrezzo> attrezzi;
    
    public ListaAttrezzi() {
        attrezzi = new HashSet<Attrezzo> ();
    }

	/**
	 * 
	 */
    public int getPeso() {
        int peso = 0;
        for (Attrezzo attrezzo : this.attrezzi)
            peso += attrezzo.getPeso();
        return peso;
    }

	/**
	 * Mette un attrezzo nella lista.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella lista.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        //todo: convert return false to throw error
        if (attrezzo == null) return false;
        this.attrezzi.add(attrezzo);
        return true;
    }

    public boolean addAttrezzoConNome(String nome_attrezzo, int peso_attrezzo) {
        this.attrezzi.add(new Attrezzo(nome_attrezzo, peso_attrezzo));
        return true;
    }

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.getAttrezzoConNome(nomeAttrezzo) != null;
    }
    
	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 *         null se l'attrezzo non e' presente.
	 */
    public Attrezzo getAttrezzoConNome(String nomeAttrezzo) {
        for (Attrezzo attrezzo : this.attrezzi) {
            if (attrezzo.getNome().equalsIgnoreCase(nomeAttrezzo)) {
                return attrezzo;
            }
        }
        return null;
    }

    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<Attrezzo> (this.attrezzi);
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
        return this.attrezzi.remove(attrezzo_da_rimuovere);
    }

	/**
	 * ritorna vero se la lista è vuota
	 */
    public boolean isEmpty() {
        return this.attrezzi.isEmpty();
    }

    public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
        Map<Integer,Set<Attrezzo>> result = new TreeMap<Integer,Set<Attrezzo>> ();
        for (Attrezzo attrezzo : this.attrezzi) {
            int peso = attrezzo.getPeso();
            //TODO: convertire HashSet to TreeSet oppure List, specificare l'ordinamento giusto per Attrezzo
            if (! result.containsKey(peso)) result.put(peso, new HashSet<Attrezzo> ());
            result.get(peso).add(attrezzo);
        }
        return result;
    }

    private List<Attrezzo> getContenutoOrdinatoPerPeso() {
        List<Attrezzo> attrezzo_sorted = new ArrayList<Attrezzo> (this.attrezzi);
        attrezzo_sorted.sort((Attrezzo a1, Attrezzo a2) -> (a1.getPeso() - a2.getPeso()));
        return attrezzo_sorted;
    }

    private List<Attrezzo> getContenutoOrdinatoPerNome() {
        List<Attrezzo> attrezzo_sorted = new ArrayList<Attrezzo> (this.attrezzi);
        attrezzo_sorted.sort((Attrezzo a1, Attrezzo a2) -> (a1.getNome().compareTo(a2.getNome())));
        return attrezzo_sorted;
    }

    private List<Attrezzo> getContenutoRaw() {
        return new ArrayList<Attrezzo> (this.attrezzi);
    }

	/**
	 * stampa la lista attrezzi, un attrezzo alla volta, separati da spazi
	 */
    public String toString() {
        StringBuilder s = new StringBuilder();
        //ordinamento per memoria
        for (Attrezzo attrezzo : getContenutoRaw()) {
            s.append(attrezzo.toString() + " ");
        }
        return s.toString();
    }

    public String toStringContenutoDettagliato() {
        StringBuilder s = new StringBuilder();
        //ordinamento per memoria
        s.append("[ord. memoria] ");
        for (Attrezzo attrezzo : getContenutoRaw()) {
            s.append(attrezzo.toString() + " ");
        }
        s.append("[ord. nome] ");
        for (Attrezzo attrezzo : getContenutoOrdinatoPerNome()) {
            s.append(attrezzo.toString() + " ");
        }
        s.append("[ord. peso] ");
        for (Attrezzo attrezzo : getContenutoOrdinatoPerPeso()) {
            s.append(attrezzo.toString() + " ");
        }
        return s.toString();
    }

}
