package main;

import controleur.Controleur;

public class Main {


    private static Controleur controleur = new Controleur();

    /**
     * Main. Methode a partir de laquelle le projet est lance.
     * @param args
     */
    public static void main(String[] args) {
        controleur.controle();
    }
}
