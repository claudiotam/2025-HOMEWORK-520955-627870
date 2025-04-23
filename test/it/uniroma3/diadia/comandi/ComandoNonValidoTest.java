package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class ComandoNonValidoTest {
    @Test
    public void crea_un_comando_non_valido() {
        assertNotNull(new ComandoNonValido(), "non riesco a creare l'oggetto ComandoNonValido");
    }
}
