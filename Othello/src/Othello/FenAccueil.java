/*
 *FenAccueil.java								7 mai 2017
 *IUT de Rodez 
 */
package Othello;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import OthelloCode.Alerte;
import OthelloCode.Partie;

/**
 * @author Adrien
 *
 */

public class FenAccueil extends JFrame implements ActionListener {
	
	/**
	 * //TODO 
	 */
	static String pseudo;
	
	/**
	 * //TODO
	 */
	private static final long serialVersionUID = 1L;

	/** //TODO*/
	private static FenAccueil intAccueil;
	
	/** //TODO*/
	static FenAide intAide; 
	
	/** La fenêtre de jeu 1v1 */
	static FenJeu intJeu;
	
	/**La fenêtre de score*/
	static FenScore intScore;
	
	/**La fenêtre de désolation*/
	static Alerte intDeso;
		
	/**Constante égale au nombre de boutons de la fenêtre*/
	private static final int NBBOUTONS = 5;
	
	/**Abscisses des boutons de la fenêtre*/
	private static final int X[]= {0,2,1,1,1};
	/**Ordonnées des boutons de la fenêtre*/
	private static final int Y[]= {0,0,1,2,3};
	
	/**Largeur des boutons*/
	private static final int LARGEUR[] = {1,1,1,1,1};
	/**Hauteur des boutons*/
	private static final int HAUTEUR[] = {1,1,1,1,1};
	
	/**Poids des différentes parties de la fenêtre en abscisses*/
	private static final int POIDS_X[] = {25,25,40,40,40};
	/**Poids des différentes parties de la fenêtre en ordonnées*/
	private static final int POIDS_Y[] = {15,15,25,25,25};
	
	/**Tableau visant à contenir les boutons bientôt créé*/
	private static JButton bouton[] = new JButton[NBBOUTONS];
	
	/**Tableau contenant les phrases des boutons*/
	private static String boutonPhrase[] = {"Score","Aide", 
											"Jouer contre l'ordinateur",
											"Jouer en 1VS1", 
											"Reprendre une partie"};

	/**
	 * La fenêtre de partie contre l'ordinateur
	 */
	public static Fen1vIA intJeuvIA; 
	
	/**
	 *méthode permettant de créer le fenêtre du menu principal
	 */
	public FenAccueil() {
		
		Container contenu = getContentPane(); //partie contenu de la fenêtre
		
		/*Titre de la fenêtre*/
		setTitle("Bienvenue sur Othello JOJO");
		
		/*Permettra de placer les boutons*/
		GridBagConstraints contrainte = new GridBagConstraints();
		
		/*La fermeture de la fenêtre entraîne l'arrêt du programme*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contenu.setLayout(new GridBagLayout());
		setSize(700,700);// taille de la fenêtre
		
		/*Les boutons remplisse toute la place disponible*/
		contrainte.fill = GridBagConstraints.BOTH; 
		
		for (int i = 0; i < bouton.length; i++) {
			contrainte.gridx = X[i];
			contrainte.gridy = Y[i];
			contrainte.gridwidth = LARGEUR[i];
			contrainte.gridheight = HAUTEUR[i];
			contrainte.weightx = POIDS_X[i];
			contrainte.weighty = POIDS_Y[i];
			contrainte.insets = new Insets(20,20,20,20);
			
			/*création du bouton et ajout au contenu de la fenêtre*/
			bouton[i] = new JButton(boutonPhrase[i]);
			contenu.add(bouton[i], contrainte);			
		}
		setVisible(true); // rend la fenêtre visible
		
		/*ajout de la fonction permet*/
		for (int i = 0; i < bouton.length; i++) {
			bouton[i].addActionListener(this);
		}
	}
	
	/**
	 * //TODO
	 */
	public static void lancementFenAccueil() {
		FenAccueil intAccueil = new FenAccueil();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		/*On attribue aux boutons l'action qu'ils devront pouvoir faire*/
		if (ev.getSource() == bouton[0]) {
			intScore = new FenScore();
		} else if (ev.getSource() == bouton[1]){
			intAide = new FenAide();
		} else if (ev.getSource() == bouton[2]) {             
			// lancement d'une partie contre l'ordinateur
			pseudo = JOptionPane.showInputDialog("Entrez votre pseudo");
			Fen1vIA.demandeNiveau();
			Fen1vIA.lancementPartie1vIA();
		} else if (ev.getSource() == bouton[3]) {
			FenJeu.lancementPartie1v1();
	    } else if (ev.getSource() == bouton[4]) {
	    	intDeso = new Alerte("Nous n'avons pas eu le temps d'équiper le jeu de cette fonction");
	    }
	}
	
	/**
	 * @return //TODO
	 */
	public static String getPseudo() {
		return pseudo;
	}
}