package it.uniroma3.diadia;

public class IOSimulator implements IO {
    final static private int MESSAGGI_OUT_DIM_DEFAULT = 100;

    // in ordine temporale, array di messaggi in viaggio dall'utente al gioco
    private String[] messaggi_in;
    private int messaggi_in_read_cursor;

    // in ordine temporale, array di messaggi in viaggio dal gioco all'utente
    private String[] messaggi_out;
    private int messaggi_out_write_cursor;

    public IOSimulator(String[] messaggi_in) {
        this(messaggi_in, MESSAGGI_OUT_DIM_DEFAULT);
    }

    public IOSimulator(String[] messaggi_in, int messaggi_out_dim) {
        this.messaggi_out = new String[messaggi_out_dim];
        this.messaggi_in = messaggi_in;
        this.messaggi_in_read_cursor = 0;
    }

    public void mostraMessaggio(Object msg) {
        // System.out.println(msg);
        if (messaggi_out_write_cursor < messaggi_out.length) {
            messaggi_out[messaggi_out_write_cursor++] = msg.toString();
            System.out.println("messaggio out " + (messaggi_out_write_cursor-1) + " scritto");
        }
        else {
            //fine dello spazio disponibile nel buffer di scrittura, dovrei lanciare una eccezione ma non so come si fa
        }
    }

    public String leggiRiga() {
        // Scanner scannerDiLinee = new Scanner(System.in);
        // String riga = scannerDiLinee.nextLine();
        // return riga;
        if (messaggi_in_read_cursor < messaggi_in.length) {
            System.out.println("messaggio in  " + messaggi_in_read_cursor + " letto");
            return messaggi_in[messaggi_in_read_cursor++];
        }
        else {
            //fine dei dati nel buffer di lettura, dovrei lanciare una eccezione ma non so come si fa
            return "";
        }
    }

    public void stampaRegistroMessaggiIn() {
        int cur = 0;
        System.out.println("messaggi in già spediti:");
        while (cur < messaggi_in_read_cursor) {
            System.out.println(messaggi_in[cur++]);
        }

        System.out.println("messaggi in ancora non spediti:");
        while (cur < messaggi_in.length) {
            System.out.println(messaggi_in[cur++]);
        }
    }

    public void stampaRegistroMessaggiOut() {
        int cur = 0;
        System.out.println("messaggi out spediti:");
        while (cur < messaggi_out_write_cursor) {
            System.out.println(messaggi_out[cur++]);
        }
    }

    public String[] getMessaggiOut() {
        return messaggi_out;
    }

    public int getMessaggiOutCursor() {
        return messaggi_out_write_cursor;
    }

}
