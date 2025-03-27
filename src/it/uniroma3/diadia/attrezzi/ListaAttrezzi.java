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

    public int getPeso() {
        int peso = 0;
        for (int i = 0; i < this.numeroAttrezzi; i++)
            peso += this.attrezzi[i].getPeso();
        return peso;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (numeroAttrezzi >= numeroMassimoAttrezzi) {
            return false;
        }
        this.attrezzi[numeroAttrezzi++] = attrezzo;
        return true;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
                return this.attrezzi[i];
            }
        }
        return null;
    }

    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

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

    public boolean isEmpty() {
        return numeroAttrezzi == 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numeroAttrezzi; i++) {
            s.append(attrezzi[i].toString() + " ");
        }
        return s.toString();
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
                return true;
            }
        }
        return false;
    }
}
