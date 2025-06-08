package it.uniroma3.diadia.ambienti;

public enum Direzione {
    NORD, SUD, OVEST, EST;

    public static Direzione fromStringIgnoreCase(String nome_direzione) {
        for (Direzione direzione : Direzione.values()) {
            if (direzione.name().equalsIgnoreCase(nome_direzione)) {
                return direzione;
            }
        }
        return null; // Not found
    }
    
}
