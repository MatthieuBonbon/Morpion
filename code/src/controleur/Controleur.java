package controleur;

import gestionnaireJeu.GestionnaireJeu;
import vue.Vue;

import java.util.concurrent.TimeUnit;

public class Controleur {

    private Vue vue = new Vue();
    private GestionnaireJeu gestionnaireJeu = new GestionnaireJeu();
    private String[][] tabMorpion = new String[3][3];


    /**
     * Constructeur
     */
    public Controleur() {
    }

    /**
     * Methode principale de la classe s'occupant de la gestion de la partie
     */
    public void controle(){
        vue.commencerJeu();
        commencerPartie(joueurQuiCommence());

    }

    /**
     * Methode permettant de connaitre et de retourner le joueur qui commence la partie
     * @return le joueur qui commence la partie
     */
    private boolean joueurQuiCommence(){
        while(vue.getxCommence() == -1){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return vue.getxCommence() != 0;
    }

    /**
     * Methode permettant de commencer la partie en initialisant le tableau du morpion et qui s'occupe egalement de faire jouer chaque tours.<br>
     *     Un tour se passe de la maniere suivante : <ul>
     *         <li> on affiche le menu contenant la grille aux joueurs </li>
     *         <li> on attend que le joueur decide de joueur sur une case </li>
     *         <li> on verifie si le joueur qui vient de jouer a gagne en verifiant si le tableau fraichement modifie contient une combinaison gagnante </li>
     *         <li> si le joueur a gagne, on demande a la vue d'afficher le menu de fin de jeu  </li>
     *         <li> si le joueur n'a pas gagne, on continue  la partie </li>
     *         <li> si on a atteint les 9 tours sans que l'un des deux joueurs gagne, on declare que la partie est perdue et on demande a la vue d'afficher le menu de fin de jeu </li>
     *     </ul>
     */
    private void commencerPartie(boolean xCommence){


        // initialisation du tableau.
        tabMorpion = new String[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null}
        };


        System.out.println("Joueur qui commence : " + xCommence);
        //vue.menuJeu(tabMorpion,"X");

        int nbTour = 0;
        while(nbTour != 9){

            // afficher le jeu
            if(nbTour % 2 == 0){
                if(xCommence){
                    vue.menuJeu(tabMorpion,"X");
                }
                else{
                    vue.menuJeu(tabMorpion,"O");
                }
            }
            else{
                if(xCommence){
                    vue.menuJeu(tabMorpion,"O");
                }
                else{
                    vue.menuJeu(tabMorpion,"X");
                }
            }




            // Faire jouer la personne
            while(isTabVueEqualTabCont(vue.getTabMorpion())){
                // Le joueur n'a pas encore joué car le jeu n'a pas été modifié

                System.out.println("Le joueur n'a pas joué");

                // delay
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Le joueur a joué");


            copy2dArray(vue.getTabMorpion());
            // on copie les données du tableau modifié par l'utilisateur sur le tabMorpion

            // Vérifier si la personne a gagné
            if(nbTour>=4){
                boolean isWon = gestionnaireJeu.verifierGagnant(tabMorpion);
                System.out.println("à gagné  : " + isWon);

                if (isWon) {
                    // si oui -> la personne est gagnante
                    if (nbTour % 2 == 0) {
                        if (xCommence) {
                            vue.menuFin(tabMorpion, "X a gagné");
                        } else {
                            vue.menuFin(tabMorpion, "O a gagné");
                        }
                    } else {
                        if (xCommence) {
                            vue.menuFin(tabMorpion, "O a gagné");
                        } else {
                            vue.menuFin(tabMorpion, "X a gagné");
                        }
                    }
                    break;
                }
            }
            // sinon on continue





            System.out.println("tour (avant incrémentation) = " + nbTour);
            nbTour++;
        }

        if(nbTour!=9){
            System.out.println("while breaked" + "\n" + " => tour : " + nbTour);
        }
        else{
            vue.menuFin(tabMorpion, "Egalité : aucun joueur n'a gagné");

        }

        // return false <- tout le monde a perdu


    }

    /**
     *  Methode retournant si le tableau du morpion issu de la classe Controleur et celui issu de la classe Vue contiennent les memes valeurs pour tout index
     * @param tabVue le tableau issu de la classe Vue
     * @return si les deux tableaux contiennent les memes valeurs pour tout index
     */
    private boolean isTabVueEqualTabCont(String[][] tabVue){
        // on ne s'occupe pas ici de vérifier / mesurer la taille des tableaux car on
        // sait déjà qu'ils font tout les 2 des dimensions de 3 x 3.

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.println(tabVue[i][j] +" == " + tabMorpion[i][j]);
                if(tabVue[i][j]!=tabMorpion[i][j]){
                    System.out.println(tabVue[i][j] +" =/= " + tabMorpion[i][j]);
                    return false;
                }
            }
        }


        return true;
    }

    /**
     * Methode copiant les valeurs d'un tableau a deux entrees dans un autre tableau a deux entrees.
     * @param tabInit le tableau qui va etre copie; qui va servir de modele pour l'autre tableau
     */
    private void copy2dArray(String[][] tabInit){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tabMorpion[i][j]=tabInit[i][j];
            }
        }

    }



}
