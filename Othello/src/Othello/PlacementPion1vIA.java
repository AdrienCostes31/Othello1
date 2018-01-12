/* 
*	Exemple de fenêtre gérée avec GridBagLayout  
*	(la fenêtre contient des zones de saisie et des étiquettes)  * FenetreZoneSaisieGBL.java 
 */ 
package Othello; 
 
import javax.swing.*;

import OthelloCode.Grille;

import java.awt.*; // pour GridBagLayout 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/** 
 * Fenêtre avec plusieurs zones de saisie et des étiquettes dont l'agencement 
 * est géré par le gestionnaire de mise en forme GridBagLayout 
 * @author Adrien
 */ 
public class PlacementPion1vIA extends Container implements ActionListener{ 
 
    /** Etiquette pour le nom */     
	private JLabel labelAbscisse; 
 
    /** Etiquette pour le prénom */    
	private JLabel labelOrdonnee; 
 
    /** Etiquette pour l'adresse */     
	private JLabel labelJoueur; 
 
    /** Zone de saisie pour le nom */     
	public static JTextField zoneAbscisse; 
 
    /** Zone de saisie pour le prénom */    
	public static JTextField zoneOrdonnee; 
	
	/** Etiquette pour le score*/
	private JLabel score; 
	
	/** Zone d'affichage du score*/
	public static JLabel scoreJoueur1;
	
	/**	Zone d'affichage du score*/
	public static JLabel scoreJoueur2;
 
    /** bouton ok */    
    public static JButton ok;
    
    /** bouton passer */
    public static JButton passer;
    
    /**bouton quitter*/
    public static JButton quitter;
 
    /** Abscisse du coin supérieur gauche des différents composants */     
    private static final int X[] = { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0};  
    
    /** Ordonnée du coin supérieur gauche des différents composants */     
    private static final int Y[] = { 0, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8};  
    
    /** Largeur des différents composants */ 
    private static final int LARGEUR[] = { 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2}; 
    
    /** Indique comment le composant remplit la zone qui lui est attribuée */ 
    private static final int REMPLIR[] = { GridBagConstraints.NONE,             
    		GridBagConstraints.HORIZONTAL, GridBagConstraints.NONE, 
            GridBagConstraints.HORIZONTAL, GridBagConstraints.NONE, 
            GridBagConstraints.BOTH, GridBagConstraints.BOTH,
            GridBagConstraints.NONE, GridBagConstraints.NONE,
            GridBagConstraints.BOTH, GridBagConstraints.BOTH}; 
 
    /** 
     * Constructeur par défaut 
     */ 
    public PlacementPion1vIA() {        
         
        // le positionnement des composants sera géré avec GrigBagLayout        
        setLayout(new GridBagLayout()); 
                 
        // On crée les composants atomiques         
        labelAbscisse = new JLabel("Abscisse");        
        labelOrdonnee = new JLabel("Ordonnee"); 
        labelJoueur = new JLabel("Joueur                 ");      //les espaces permettent d'avoir une taille correcte  
        zoneAbscisse = new JTextField();        
        zoneOrdonnee = new JTextField();       
        ok = new JButton("OK"); 
        score = new JLabel("Scores :");
        scoreJoueur1 = new JLabel(FenAccueil.getPseudo() + ": " + Fen1vIA.laGrille.compter('x'));
        scoreJoueur2 = new JLabel("ordi    : " + Fen1vIA.laGrille.compter('o'));
        passer = new JButton("PASSER");
        quitter = new JButton("QUITTER");
        /* 
         *	On place les composants dans un tableau de composants 
         *	afin de pouvoir utiliser une boucle pour les ajouter à la 
         *	fenêtre et les positionner 
         */ 
        JComponent composant[] = { labelAbscisse, zoneAbscisse, labelOrdonnee, zoneOrdonnee, 
        						   labelJoueur, ok, score, scoreJoueur1, scoreJoueur2, passer, quitter}; 
 
        /* 
         * On déclare et on crée un objet de type contrainte
         * Cette contrainte sera utilisée par le gestionnaire GridBagLayout
         * pour placer le composant comme souhaité 
         */         
        GridBagConstraints contrainte = new GridBagConstraints();  
        // On ajoute les composants et on les positionne        
        for (int i = 0; i < composant.length; i++) { 
 
            // préparation de la contrainte pour placer le composant i            
        	contrainte.gridx = X[i];                // position du bouton sur x, 
            contrainte.gridy = Y[i];                // et y 
            contrainte.gridwidth = LARGEUR[i];      // largeur du bouton                   
            contrainte.fill = REMPLIR[i];           // comment remplir la zone  
 
            /* 			
             * Insets permet de définir des espaces de séparation 
             * tout autour du composant, afin d'éviter qu'il ne soit
             * "collé" aux composants voisins (haut,gauche,bas,droite) 
             */ 
            contrainte.insets = new Insets(2,2,2,2);              
            /*
             * pour définir le point d'ancrage du composant dans son
             * espace disponible (en haut à gauche) 
             */ 
            contrainte.anchor = GridBagConstraints.FIRST_LINE_START ;              
            // on ajoute le composant avec sa contrainte             
            add(composant[i], contrainte); 
            
        }
        
       // ok.addActionListener(this); // on donne la possibilité au bouton ok de faire une action on Click
        setVisible(true);       // On rend la fenêtre visible 
    }
    
    /**
     * @return //TODO
     */
    public String position() {
		/*
		 * Le positionnement des pions sera indiqué grâce grâce 
		 * à une String. 
		 * Le premier caractère retournera la colonne 
		 * du placement du pion.
		 * Le deuxième caractère sera la ligne où le pion sera placé.
		 * Le troisième caractère sera la couleur du pion.  
		 */
		
		String abscisse = zoneAbscisse.getText();
		String ordonnee = zoneOrdonnee.getText().toUpperCase();
		
		return abscisse + ordonnee;		
	}
    
    /**
	 * @param chainePosition 
     * @return //TODO
	 */
	public static int positionGrille(String chainePosition) {
		
		char abscisse = chainePosition.charAt(0);
		char ordonnee = chainePosition.charAt(1);
		
		int abscisseI = abscisse - 48;
		int ordonneeI = ordonnee - 64;
		
		int position = ordonneeI*9 + abscisseI; 
		return position;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	} 
} 
