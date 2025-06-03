package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
    IO ioconsole;

    public FabbricaDiComandiFisarmonica(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
    
    @Override
    public Comando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;
        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next(); // prima parola: nome del comando
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
        scannerDiParole.close();
            
        if (nomeComando == null)
            comando = new ComandoNonValido();
        else if (nomeComando.equals("vai"))
            comando = new ComandoVai();
        else if (nomeComando.equals("prendi"))
            comando = new ComandoPrendi();
        else if (nomeComando.equals("posa"))
            comando = new ComandoPosa();
        else if (nomeComando.equals("aiuto"))
            comando = new ComandoAiuto();
        else if (nomeComando.equals("fine"))
            comando = new ComandoFine();
        else if (nomeComando.equals("guarda"))
            comando = new ComandoGuarda();
        else
            comando = new ComandoNonValido();
        comando.setParametro(parametro);
        comando.setIOConsole(ioconsole);
        return comando;
    }
}