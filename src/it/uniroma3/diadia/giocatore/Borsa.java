/**
 * 
 */
package it.uniroma3.diadia.giocatore;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ListaAttrezzi;

/**
 * classe che gestisce la borsa del giocatore, contenente attrezzi
 */
public class Borsa {
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private int pesoMax;
    private ListaAttrezzi listaAttrezzi;

    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }

    public Borsa(int pesoMax) {
        this.pesoMax = pesoMax;
        this.listaAttrezzi = new ListaAttrezzi();
    }

    /*
     * forwarding dei metodi add-has-get-remove
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.listaAttrezzi.getPeso() + attrezzo.getPeso() > this.pesoMax) {
            return false;
        }
        return this.listaAttrezzi.addAttrezzo(attrezzo);
    }

    public boolean addAttrezzoConNome(String nome_attrezzo, int peso_attrezzo) {
        return this.addAttrezzo(new Attrezzo(nome_attrezzo, peso_attrezzo));
    }
    
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.listaAttrezzi.hasAttrezzo(nomeAttrezzo);
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return this.listaAttrezzi.getAttrezzoConNome(nomeAttrezzo);
    }

    public boolean removeAttrezzo(Attrezzo attrezzo_da_rimuovere) {
        return this.listaAttrezzi.removeAttrezzo(attrezzo_da_rimuovere);
    }

    public int getPeso() {
        return this.listaAttrezzi.getPeso();
    }

    public boolean isEmpty() {
        return this.listaAttrezzi.isEmpty();
    }

    public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
        return this.listaAttrezzi.getContenutoRaggruppatoPerPeso();
    }    
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Peso borsa (" + this.getPeso() + "kg/" + this.pesoMax + "kg) ");
        if (this.isEmpty()) s.append("la borsa è vuota ");
        else {
            s.append("Contenuto borsa: ");
            s.append(this.listaAttrezzi);
        }
        return s.toString();
    }

    public String getDescrizioneDettagliata() {
        StringBuilder s = new StringBuilder();
        s.append("Peso borsa (" + this.getPeso() + "kg/" + this.pesoMax + "kg) ");
        if (this.isEmpty()) s.append("la borsa è vuota ");
        else {
            s.append("Contenuto borsa: ");
            s.append(this.listaAttrezzi.toStringContenutoDettagliato());
        }
        return s.toString();
    }

}