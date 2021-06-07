package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Vue {

    private JFrame frame = new JFrame("Morpion");
    private int xCommence = -1;
    private String[][] tabMorpion;

    /**
     * Constructeur
     */
    public Vue() {
    }

    /**
     * Methode permettant de commencer le jeu en initialisant la fenetre et en affichant le premier menu via les methodes qu'elle appelle.
     */
    public void commencerJeu(){
        initFenetre();
        menuCommencer();
    }

    /**
     * Methode permettant de configurer la fenetre.
     */
    private void initFenetre(){

        // la fenêtre doit se fermer quand on clique sur la croix rouge
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // on modifie la taille de la fenêtre
        frame.setSize(1200,600);
        // on centre la fenêtre
        frame.setLocationRelativeTo(null);
        // on rend la fenêtre visible
        frame.setVisible(true);
        // on bloque la taille de la fenêtre
        frame.setResizable(false);



        // Si la personne ferme la fenêtre, le programme s'arrête
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                System.out.println("Arrêt du programme");
                System.exit(0);
            }
        });

    }

    /**
     * Permet d'afficher le menu de choix de la personne qui va commencer : "X" ou "O"
     */
    private void menuCommencer(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(2,1));

        // texte "Commencer"
        JLabel label = new JLabel("Commencer");
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(0);
        label.setFont(new Font("Ink Free", Font.BOLD, 34));
        panel.add(label);

        // boutons "X commence" et "O commence"
        JPanel panelBouton = new JPanel();
        panelBouton.setBackground(Color.WHITE);

        JButton boutonX = new JButton("X commence");

        boutonX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCommence = 1;
            }
        });

        JButton boutonO = new JButton("O commence");

        boutonO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xCommence = 0;
            }
        });

        panelBouton.add(boutonX);
        panelBouton.add(boutonO);

        panel.add(panelBouton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);

    }

    /**
     * Methode permettant d'afficher le jeu avec, a gauche, la grille du morpion et a droite le nom du joueur qui doit jouer
     * @param tabMorpion Le tableau contenant le morpion qui doit etre affiche dans la grille
     * @param joueur Le nom du joueur qui doit joueur lors de ce tour
     */
    public void menuJeu(String[][] tabMorpion, String joueur){


        /*
        permet de modifier le tabMorpion de la vue une fois que
        l'utilisateur a cliqué sur un bouton présent sur la grid
        et permet également de récupérer dans le controleur le tabMorpion modifié
        */
        copy2dArray(tabMorpion);

        // panel principal
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(1,2));


        // panel de la grille représentant l'affichage d'un morpion
        JPanel panelGrid = new JPanel();
        panelGrid.setBackground(Color.WHITE);
        panelGrid.setLayout(new GridLayout(3,3));




        panelGrid = remplirGridMorpion(tabMorpion, joueur);


        /* panel du texte à droite du morpion
           indiquant les diverses informations
         */
        JPanel panelText = new JPanel();
        panelText.setBackground(Color.WHITE);
        panelText.setLayout(new GridLayout(2,1));


        JLabel labelTour = new JLabel("Au tour de " + joueur);
        labelTour.setFont(new Font("Ink Free", Font.BOLD, 34));
        labelTour.setHorizontalAlignment(0);
        labelTour.setVerticalAlignment(3);
        panelText.add(labelTour);


        panel.add(panelGrid);
        panel.add(panelText);



        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        System.out.println("process créa menu jeu terminé");
    }


    /**
     * Methode permettant d'afficher le menu de fin de jeu avec le morpion a gauche et le nom du gagnant a droite.
     * @param tabMorpion Le tableau contenant le morpion qui doit etre affiche dans la grille
     * @param nomGagnant Le nom du joueur gagnant
     */
    public void menuFin(String[][] tabMorpion, String nomGagnant){
        // panel principal
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(1,2));



        // panel de la grille représentant l'affichage d'un morpion
        JPanel panelGrid = new JPanel();
        panelGrid.setBackground(Color.WHITE);
        panelGrid.setLayout(new GridLayout(3,3));

        panelGrid = remplirGridMorpion(tabMorpion,null);




        /* panel du texte à droite du morpion
           indiquant les diverses informations
         */
        JPanel panelText = new JPanel();
        panelText.setBackground(Color.WHITE);
        panelText.setLayout(new GridLayout(2,1));


        JLabel labelTour = new JLabel(nomGagnant);
        labelTour.setFont(new Font("Ink Free", Font.BOLD, 34));
        labelTour.setHorizontalAlignment(0);
        labelTour.setVerticalAlignment(3);
        panelText.add(labelTour);


        panel.add(panelGrid);
        panel.add(panelText);


        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        System.out.println("process créa menu fin terminé");
    }


    /**
     * Methode remplissant la grille a partir du tableau contenant le morpion et en fonction de ce dernier. <br>
     * Si le tableau ne contient pas de valeur dans une case, on met un bouton ou le joueur pourra cliquer pour choisir cette case pour son coup. <br>
     * si le tableau contient une valeur dans une case, on met un label contenant le symbole present dans la case : "X" ou "O" . <br>
     * <br>
     * Optimisation possible : en imbriquant deux boucles "for" <br> <br>
     * @param tabMorpion Le tableau contenant le morpion que l'on doit afficher
     * @param joueur Le joueur dont le symbole sera ajoute au tableau lorsqu'il cliquera sur un bouton
     * @return le JPanel contenant la grille du morpion remplie par le tableau passe en parametre
     */
    private JPanel remplirGridMorpion (String[][] tabMorpion, String joueur) {
        JPanel panelGrid = new JPanel();
        panelGrid.setBackground(Color.WHITE);
        panelGrid.setLayout(new GridLayout(3,3));


        //[0][0]
        if(tabMorpion[0][0]==null){
            panelGrid.add(parameterizedButton(0,0, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[0][0]));
        }


        //[0][1]
        if(tabMorpion[0][1]==null){
            panelGrid.add(parameterizedButton(0,1, joueur));
        }
        else{
            panelGrid.add(parameterizedLabel(tabMorpion[0][1]));
        }



        //[0][2]
        if(tabMorpion[0][2]==null){
            panelGrid.add(parameterizedButton(0,2, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[0][2]));
        }


        //[1][0]
        if(tabMorpion[1][0]==null){
            panelGrid.add(parameterizedButton(1, 0, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[1][0]));
        }


        //[1][1]
        if(tabMorpion[1][1]==null){
            panelGrid.add(parameterizedButton(1,1, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[1][1]));
        }


        //[1][2]
        if(tabMorpion[1][2]==null){
            panelGrid.add(parameterizedButton(1,2, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[1][2]));
        }


        //[2][0]
        if(tabMorpion[2][0]==null){
            panelGrid.add(parameterizedButton(2,0, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[2][0]));
        }


        //[2][1]
        if(tabMorpion[2][1]==null){
            panelGrid.add(parameterizedButton(2,1, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[2][1]));
        }


        //[2][2]
        if(tabMorpion[2][2]==null){
            panelGrid.add(parameterizedButton(2,2, joueur));
        }
        else {
            panelGrid.add(parameterizedLabel(tabMorpion[2][2]));
        }



        return panelGrid;
    }


    /**
     * Methode retournant le label contenant la valeur contenue dans une case.
     * @param texte la valeur contenue dans le label
     * @return le label contenant la valeur contenue dans une case
     */
    private JLabel parameterizedLabel(String texte){
        JLabel label = new JLabel(texte);
        label.setVerticalAlignment(0);
        label.setHorizontalAlignment(0);
        label.setFont(new Font("Ink Free", Font.PLAIN, 200));
        label.setBorder(new LineBorder(Color.BLACK));

        return label;
    }

    /**
     * Methode retournant un bouton.
     * @param row la ligne du tableau contenant la case qui va etre modifie si le joueur clique sur le bouton
     * @param column la colonne du tableau contenant la case qui va etre modifie si le joueur clique sur le bouton
     * @param val la valeur que va prendre le tableau a l'index [row][column]
     * @return le bouton
     */
    private JButton parameterizedButton(int row, int column, String val){
        JButton button = new JButton();
        button.setBackground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabMorpion[row][column] = val;
                System.out.println(" le joueur a joué -> tabMorpion["+row+"]["+column+"] = " + tabMorpion[row][column]);

            }
        });

        return button;
    }

    /**
     * Methode copiant les valeurs d'un tableau a deux entrees dans un autre tableau a deux entrees.
     * @param tabInit le tableau qui va etre copie; qui va servir de modele pour l'autre tableau
     */
    private void copy2dArray(String[][] tabInit){
        tabMorpion = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tabMorpion[i][j]=tabInit[i][j];

            }
        }

    }

    /**
     * Getter retournant le tableau contenant du morpion
     * @return le tableau contenant du morpion
     */
    public String[][] getTabMorpion() {
        return tabMorpion;
    }

    /**
     * Getter retournant le joueur qui commence : "X" ou "O"
     * @return le joueur qui commence : "X" ou "O"
     */
    public int getxCommence() {
        return xCommence;
    }
}
