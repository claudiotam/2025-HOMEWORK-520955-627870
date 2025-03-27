package it.uniroma3.diadia.giocatore;

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

    public Borsa getBorsa() {
        return this.borsa;
    }

    public int getCfu() {
        return this.cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }
}
