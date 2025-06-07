package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
    Scanner scannerDiLinee;

    public IOConsole() {
        this.scannerDiLinee = new Scanner(System.in);
    }

    @Override
    public void mostraMessaggio(Object msg) {
        System.out.println(msg);
    }

    @Override
    public String leggiRiga() {
        return scannerDiLinee.nextLine();
    }

    @Override
    public void IOchiudi() {
        this.scannerDiLinee.close();
    }
}