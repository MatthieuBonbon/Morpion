package gestionnaireJeu;

public class GestionnaireJeu {


    /**
     * Constructeur
     */
    public GestionnaireJeu() {
    }

    /**
     * Unique methode de la classe GestionnaireJeu. Retourne si le tableau passe en parametre contient une combinaison gagnante.
     *
     * @param tabMorpion le talbeau contenant le morpion que l'on doit analyser
     * @return si le tableau contient une combinaison gagnante ou non (booleen)
     */
    public boolean verifierGagnant(String[][] tabMorpion){
        boolean h1 = tabMorpion[0][0] != null && tabMorpion[0][0]==tabMorpion[0][1] && tabMorpion[0][0]==tabMorpion[0][2];
        boolean h2 = tabMorpion[1][0] != null && tabMorpion[1][0]==tabMorpion[1][1] && tabMorpion[1][0]==tabMorpion[1][2];
        boolean h3 = tabMorpion[2][0] != null && tabMorpion[2][0]==tabMorpion[2][1] && tabMorpion[2][0]==tabMorpion[2][2];

        boolean v1 = tabMorpion[0][0] != null && tabMorpion[0][0]==tabMorpion[1][0] && tabMorpion[0][0]==tabMorpion[2][0];
        boolean v2 = tabMorpion[0][1] != null && tabMorpion[0][1]==tabMorpion[1][1] && tabMorpion[0][1]==tabMorpion[2][1];
        boolean v3 = tabMorpion[0][2] != null && tabMorpion[0][2]==tabMorpion[1][2] && tabMorpion[0][2]==tabMorpion[2][2];

        boolean d1 = tabMorpion[0][0] != null && tabMorpion[0][0]==tabMorpion[1][1] && tabMorpion[0][0]==tabMorpion[2][2];
        boolean d2 = tabMorpion[0][2] != null && tabMorpion[0][2]==tabMorpion[1][1] && tabMorpion[0][2]==tabMorpion[2][0];

        if(h1 || h2 || h3 || v1 || v2 || v3 || d1 || d2){
            return true;
        }
        else{
            return false;
        }


    }
}
