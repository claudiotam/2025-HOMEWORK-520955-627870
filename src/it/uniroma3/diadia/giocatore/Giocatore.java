package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 */
public class Giocatore {

    static final private int CFU_INIZIALI = 12;

    private Borsa borsa;
    private int cfu;


    public Giocatore() {
        this.borsa = new Borsa();
        this.cfu = CFU_INIZIALI;

    }

    /*
     * forwarding dei metodi add-has-get-remove
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo) ;
    }
    
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.borsa.hasAttrezzo(nomeAttrezzo);
    }
    
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
    	return this.borsa.getAttrezzo(nomeAttrezzo);
    }
    
    public boolean removeAttrezzo(Attrezzo attrezzo) {
        return this.borsa.removeAttrezzo(attrezzo) ;
    }
    
    public void togliUnCfu() {
    	this.cfu--;
    }
    
    public boolean hasZeroCfu() {
    	return this.cfu <= 0;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Giocatore con " + this.cfu + " cfu");
        s.append("\n");
        s.append(borsa);
        return s.toString();
    }
}
