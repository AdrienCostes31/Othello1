/*
 *Fen1vIA.java								1 juin 2017
 *IUT de Rodez 
 */
package Othello;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import OthelloCode.Alerte;
import OthelloCode.Grille;
import OthelloCode.Ordi;
import OthelloCode.Partie;
import OthelloCode.Sauvegarde;

/**
 * @author Adrien
 *
 */
public class Fen1vIA extends JFrame implements ActionListener{	

	/**
	 *choix du niveau de l'ordinateur
	 */
	static int choix;

	/**
	 * 
	 *
	 * //TODO
	 */
	private static final long serialVersionUID = -1077483875731815630L;

	/**
	 * //TODO
	 */
	public static int abscisse;

	/**
	 * //TODO
	 */
	public static int ordonnee;

	/**Tableau de panel faisant office de case du damier*/
	public JPanel grilleFini[] = new JPanel[82];

	/**Tableau contenant les pions croix*/
	private JLabel[] croix = new JLabel[300]; 		//parce que nous sommes des spartiates

	/**Tableau contenant les pions rond*/
	private JLabel[] rond = new JLabel[300];		//désolé on a encore paniqué
	
	/**Image pion blanc*/
	private Icon pionBlanc = new ImageIcon("pionBlanc.png");
	
	/**Image pion noir*/
	private Icon pionNoir = new ImageIcon("pionNoir.png");

	/** //TODO*/
	private PlacementPion1vIA contient;

	/** //TODO*/
	private int indiceCroix = 0;

	/** //TODO*/
	private int indiceRond = 0;

	/** //TODO*/
	static int tour = 0;

	/**
	 * //TODO
	 */
	public static Grille laGrille = new Grille();

	/**
	 * //TODO
	 */
	private ArrayList<int[]> tableauCoord = new ArrayList<int[]>();

	/**
	 * //TODO
	 */
	private int[] tabCoord =new int[2];

	/**
	 * //TODO
	 */
	static Partie laPartie;

	/**
	 * 
	 */
	public Fen1vIA() {

		laGrille.initialiser();              //on initialise la grille

		Container contenu = getContentPane(); //partie contenu de la fenêtre

		/*Permettra de placer les boutons*/
		GridBagConstraints contrainte = new GridBagConstraints();

		contenu.setLayout(new GridBagLayout());

		/*On remplis le tableau X avec des croix*/
		for (int i = 0; i < croix.length; i++) {
			croix[i] = new JLabel();
			croix[i].setIcon(pionNoir);
		}

		/*On remplis le tableau O avec des Ronds*/
		for (int i = 0; i < rond.length; i++) {
			rond[i] = new JLabel();
			rond[i].setIcon(pionBlanc);
		}

		JPanel grille = new JPanel(new GridLayout(9,9)); 					//grille du jeu pas encore visible
		Border ligneGrille = BorderFactory.createLineBorder(Color.black,1); //Partie visible de la grille

		for (int i = 0; i < 81; i++) {

			grilleFini[i] = new JPanel();
			grilleFini[i].setBorder(ligneGrille);								 //on entour les cases du damier par des lignes pour les rendre visible
			grilleFini[i].setBackground(Color.GREEN);							 //fond vert des cases du damier
			grille.add(grilleFini[i]); 											 //ajout des cases sur le panel principal

		}

		grilleFini[81] = new JPanel();
		grilleFini[81].setBorder(ligneGrille);								 //on entour les cases du damier par des lignes pour les rendre visible
		grilleFini[81].setBackground(Color.WHITE);							 //fond vert des cases du damier
		grille.add(grilleFini[81]);
		contenu.add(grilleFini[81],contrainte);

		/*Placement des quatre premiers pions*/
		grilleFini[40].add(rond[indiceRond]);
		indiceRond++;
		grilleFini[41].add(croix[indiceCroix]);
		indiceCroix++;
		grilleFini[49].add(croix[indiceCroix]);
		indiceCroix++;
		grilleFini[50].add(rond[indiceRond]);	
		indiceRond++;

		/*Mise en place des numérotation*/
		for (int i = 0; i < 9; i++) {
			grilleFini[i].setBackground(Color.WHITE);
		}
		int num = 9;
		for (int i = 1; i < 9; i++) {
			grilleFini[i * num].setBackground(Color.WHITE);
		}

		/*Tableau qui contiendra la numérotation*/
		JLabel[] numerotation = new JLabel[16];

		/*Mise en place de la numérotation*/
		for (int i = 1; i < 9; i++) {
			String numerot = "    " + i;
			numerotation[i] = new JLabel(numerot);
			grilleFini[i].add(numerotation[i]);					
		}
		int ligne = 9;
		int indice = 8;
		int caractere = 65;
		char lettre;
		String lettreConvertie;
		while (ligne <= 72) {
			lettre = (char) caractere;
			lettreConvertie = "" + lettre;
			numerotation[indice] = new JLabel(lettreConvertie);
			grilleFini[ligne].add(numerotation[indice]);
			indice++;
			ligne += 9;
			caractere ++;
		}		

		contient = new PlacementPion1vIA();		
		add(contient);

		PlacementPion1vIA.ok.addActionListener(this);				 // on permet au bouton d'effectuer une action
		PlacementPion1vIA.passer.addActionListener(this);			 // on permet au bouton d'effectuer une action
		PlacementPion1vIA.quitter.addActionListener(this);			 // on permet au bouton d'effectuer une action

		setSize(700,700);											 //taille de la fenêtre 
		add(grille); 												 //ajout du damier à la fenêtre
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int position;
		int ligne = Sauvegarde.enregistrerPseudo(FenAccueil.getPseudo());
		if (e.getSource() == PlacementPion1vIA.passer) {
			tour++;
		} else if (e.getSource() == PlacementPion1vIA.quitter) {
			int option = JOptionPane.showConfirmDialog(null, "voulez-vous enregistrer la partie?", 
					"enregistrement", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				try {
					Sauvegarde.sauvegarderPartie(laPartie);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return;
		}else if (tour % 2 == 0 || tour == 0 && tour < 64) {
			
			if (laGrille.nbCoupPossibleIA(tour) == 0) {
				new Alerte("La Partie est terminée VOUS avez gagné!!!");
				int option = JOptionPane.showConfirmDialog(null, "voulez-vous enregistrer le score?", 
						"enregistrement", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					try {
						Sauvegarde.sauvegarderPartie(laPartie);
						Sauvegarde.enregistrerPseudo(FenAccueil.getPseudo());
						Sauvegarde.enregistrerMeilleurScore(ligne, laGrille.compter('x'));
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return;
			}

			position = PlacementPion1vIA.positionGrille(contient.position());
			abscisse = contient.position().charAt(0) - 48;
			ordonnee = contient.position().charAt(1) - 64;

			if (laGrille.coupPossibleIA(ordonnee, abscisse, 'x') > 0) {
				tableauCoord = laGrille.placerIA(ordonnee, abscisse, 'x');
				position = ordonnee * 9 + abscisse;
				grilleFini[position].add(croix[indiceCroix]);
				indiceCroix++;
			} else {
				Alerte intAlerte = new Alerte("Attention la position choisi est impossible"); 

				PlacementPion1vIA.zoneAbscisse.setText("");
				PlacementPion1vIA.zoneOrdonnee.setText("");
				tour--;
				return;
			}
			/*for automatique permettant d'attribuer au tableau d'int nommé coord
			 *les valeurs présentes dans l'ArrayList composée de tableau d'int 
			 *également 
			 */ 
			for (int[] coord : tableauCoord) {
				tabCoord = coord;
				position = tabCoord[0]*9 + tabCoord[1];
				grilleFini[position].removeAll();
				grilleFini[position].add(croix[indiceCroix]);
				indiceCroix++;
			}
			//laPartie.mode1v1();
			PlacementPion1vIA.scoreJoueur1.setText(FenAccueil.getPseudo()+ ": " + laGrille.compter('x'));
			PlacementPion1vIA.scoreJoueur2.setText("Ordi   : " + laGrille.compter('o'));
			PlacementPion1vIA.zoneAbscisse.setText("");
			PlacementPion1vIA.zoneOrdonnee.setText("");

			setVisible(true);
		}

		if (e.getSource() == PlacementPion1vIA.ok) {			

			tour++;

		}

		if (tour % 2 == 1 && tour < 64) {
			
			if (laGrille.nbCoupPossibleIA(tour) == 0) {
				new Alerte("La Partie est terminée VOUS avez perdu!!!");
				int option = JOptionPane.showConfirmDialog(null, "voulez-vous enregistrer le score?", 
						"enregistrement", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					try {
						Sauvegarde.sauvegarderPartie(laPartie);
						Sauvegarde.enregistrerPseudo(FenAccueil.getPseudo());
						Sauvegarde.enregistrerMeilleurScore(ligne, laGrille.compter('x'));
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return;
			}

			if (getChoix() == 2) {
				PlacementPion1vIA.zoneAbscisse.setText("" + laPartie.getIa().difficile(tour)[0]);
				PlacementPion1vIA.zoneOrdonnee.setText("" + laPartie.getIa().difficile(tour)[1]);
			} else {
				PlacementPion1vIA.zoneAbscisse.setText("" + laPartie.getIa().debutant(tour)[0]);
				PlacementPion1vIA.zoneOrdonnee.setText("" + laPartie.getIa().debutant(tour)[1]);
			}
			position = PlacementPion1vIA.positionGrille(contient.position());
			abscisse = contient.position().charAt(0) - 48;
			ordonnee = contient.position().charAt(1) - 48;

			if (abscisse == 0 && ordonnee == 0 ) {
				new Alerte("L'ordinateur ne peut pas jouer");
				tour++;
				return;
			}

			if (laGrille.coupPossibleIA(ordonnee, abscisse, 'o') > 0) {
				tableauCoord = laGrille.placerIA(ordonnee, abscisse, 'o');
				position = ordonnee * 9 + abscisse;
				grilleFini[position].add(rond[indiceRond]);
				indiceRond++;
			} else {
				Alerte intAlerte = new Alerte("Attention la position choisi est impossible"); 
				tour--;
				PlacementPion1vIA.zoneAbscisse.setText("");
				PlacementPion1vIA.zoneOrdonnee.setText("");
				return;
			}
			/*for automatique permettant d'attribuer au tableau d'int nommé coord
			 *les valeurs présentes dans l'ArrayList composée de tableau d'int 
			 *également 
			 */ 
			for (int[] coord : tableauCoord) {
				tabCoord = coord;
				position = tabCoord[0]*9 + tabCoord[1];
				grilleFini[position].removeAll();
				grilleFini[position].add(rond[indiceRond]);
				indiceRond++;
			}
			//laPartie.mode1v1();
			PlacementPion1vIA.scoreJoueur1.setText(FenAccueil.getPseudo() + ": " + laGrille.compter('x'));
			PlacementPion1vIA.scoreJoueur2.setText("Ordi   : " + laGrille.compter('o'));
			PlacementPion1vIA.zoneAbscisse.setText("");
			PlacementPion1vIA.zoneOrdonnee.setText("");
			indiceRond++;
			tour++;
			setVisible(true);
		} 
	}

	/**
	 * 
	 */
	public static void lancementPartie1vIA() {
		FenAccueil.intJeuvIA = new Fen1vIA();
		laPartie = new Partie(1);
	}

	/**
	 * @return //TODO
	 * 
	 */
	public static int demandeNiveau() {

		int option = JOptionPane.showConfirmDialog(null, "voulez-vous jouer contre"
				+" un ordinateur de haut niveau?", 
				"enregistrement", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			choix = 2;
		} else {
			choix = 1;
		}
		return choix;
	}

	/**
	 * @return //TODO
	 */
	public int getChoix() {
		return choix;
	}
}

