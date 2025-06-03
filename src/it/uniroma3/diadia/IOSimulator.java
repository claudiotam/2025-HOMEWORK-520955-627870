package it.uniroma3.diadia;

import java.util.ArrayList;

public class IOSimulator implements IO {
    // in ordine temporale, array di messaggi in viaggio dall'utente al gioco
    private ArrayList<String> messaggi_in;
    private int messaggi_in_read_cursor;

    // in ordine temporale, array di messaggi in viaggio dal gioco all'utente
    private ArrayList<String> messaggi_out;

    public IOSimulator(ArrayList<String> messaggi_in) {
        this.messaggi_out = new ArrayList<String> ();
        this.messaggi_in = messaggi_in;
        this.messaggi_in_read_cursor = 0;
    }

    public void mostraMessaggio(Object msg) {
        // System.out.println(msg);
        this.messaggi_out.add(msg.toString());
    }

    public String leggiRiga() {
        // Scanner scannerDiLinee = new Scanner(System.in);
        // String riga = scannerDiLinee.nextLine();
        // return riga;
        if (this.messaggi_in_read_cursor < messaggi_in.size()) {
            System.out.println("messaggio in  " + messaggi_in_read_cursor + " letto");
            return this.messaggi_in.get(messaggi_in_read_cursor++);
        }
        else {
            //todo: fine precoce dei dati nel buffer di lettura, lanciare una eccezione
            return "";
        }
    }

    public void stampaRegistroMessaggiIn() {
        int cur = 0;
        System.out.println("messaggi in già spediti:");
        while (cur < this.messaggi_in_read_cursor) {
            System.out.println(this.messaggi_in.get(cur));
        }

        System.out.println("messaggi in ancora non spediti:");
        while (cur < this.messaggi_in.size()) {
            System.out.println(this.messaggi_in.get(cur));
        }
    }

    public void stampaRegistroMessaggiOut() {
        System.out.println("messaggi out spediti:");
        for (String msg : this.messaggi_out) {
            System.out.println(msg);
        }
    }

    public ArrayList<String> getMessaggiOut() {
        return this.messaggi_out;
    }

}
