package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/*
 * Una stanza magica ha delle particolarità, che la
* rendono diversa dalla stanza ordinaria:
* – dopo N volte che in tale stanza viene posato (aggiunto) un qualsiasi attrezzo da parte del giocatore, la stanza inizierà a comportarsi «magicamente» in modo irreversibile
* – comportamento magico: la stanza "inverte" il nome di ogni attrezzo posato (chiave->evaihc) e ne raddoppia il peso. 
* - 
*/
public class StanzaMagica extends Stanza {
    final static private int POSATURE_RESIDUE_DEFAULT = 3;
    //teniamo traccia solo delle posature residue prima di attivare la magia
    private int posatureResidue;

    //per attivare o eseguire la magia non serve conoscere i nomi degli attrezzi
    //protected Attrezzo[] attrezzi;

    //per attivare o eseguire la magia non serve conoscere la quantità di attrezzi
    //protected numeroAttrezzi;      

    public StanzaMagica(String nome) {
        this(nome, POSATURE_RESIDUE_DEFAULT);
    }

    public StanzaMagica(String nome, int posatureResidue) {
        super(nome);
        this.posatureResidue = posatureResidue;
    }

    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        //nota, la stanza ha vincoli di capienza di peso/quantita/altro; prima di invertire devo testare i vincoli
        boolean test = super.addAttrezzo(attrezzo);
        if (!test) return false;
        //vincoli superati, inverto
        if (this.posatureResidue == 0) {
            this.modificaAttrezzo(attrezzo);
        }
        else {
            this.posatureResidue --;
        }
        return true;
    }

    private void modificaAttrezzo(Attrezzo attrezzo) {
        attrezzo.invertiNomeAumentaPeso();
    }
}
