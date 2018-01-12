/*
 *FenJeu.java								17 mai 2017
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
import OthelloCode.Partie;
import OthelloCode.Sauvegarde;

/**
 * @author Adrien
 *
 */
public class FenJeu extends JFrame implements ActionListener{	

	/**
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
	private PlacementPion contient;

	/** //TODO*/
	private int indiceCroix = 0;

	/** //TODO*/
	private int indiceRond = 0;

	/** //TODO*/
	private int tour = 0;

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
	private static Partie laPartie;

	/**
	 * 
	 */
	public FenJeu() {

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
		grilleFini[81].setBackground(Color.BLACK);							 //couleur du joueur 1
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

		//grilleFini[position].add(test);

		contient = new PlacementPion();		
		add(contient);

		//TODO récupérer la position du pion et y mettre un O ou un X
		/*Vérification du placement grâce au code de antoine*/

		PlacementPion.ok.addActionListener(this);

		setSize(700,700);											 //taille de la fenêtre 
		add(grille); 												 //ajout du damier à la fenêtre
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int position;

		if (e.getSource() == PlacementPion.ok) {
			
			position = PlacementPion.positionGrille(contient.position());
			abscisse = contient.position().charAt(0) - 48;
			ordonnee = contient.position().charAt(1) - 64;
			
			if((laGrille.compter('x') + laGrille.compter('o')) == 64){
				Alerte danger = new Alerte("La partie est terminée !");	//on panique étape 3
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
			}			
			
			if (tour % 2 == 0 || tour == 0) {
				
				tour++;
				if (laGrille.coupPossible(ordonnee, abscisse, 'x') > 0) {
					tableauCoord = laGrille.placer(ordonnee, abscisse, 'x');
					position = ordonnee * 9 + abscisse;
					grilleFini[position].add(croix[indiceCroix]);
					indiceCroix++;
				} else {
					Alerte intAlerte = new Alerte("Attention la position choisi est impossible"); 
					tour--;
					PlacementPion.zoneAbscisse.setText("");
					PlacementPion.zoneOrdonnee.setText("");
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
				PlacementPion.scoreJoueur1.setText("" + laGrille.compter('x'));
				PlacementPion.scoreJoueur2.setText("" + laGrille.compter('o'));
				PlacementPion.zoneAbscisse.setText("");
				PlacementPion.zoneOrdonnee.setText("");
				grilleFini[81].setBackground(Color.WHITE);				//couleur du joueur 2
				setVisible(true);
			} else {
				
				tour++;
				if (laGrille.coupPossible(ordonnee, abscisse, 'o') > 0) {
					tableauCoord = laGrille.placer(ordonnee, abscisse, 'o');
					position = ordonnee * 9 + abscisse;
					grilleFini[position].add(rond[indiceRond]);
					indiceRond++;
				} else {
					Alerte intAlerte = new Alerte("Attention la position choisi est impossible"); 
					tour--;
					PlacementPion.zoneAbscisse.setText("");
					PlacementPion.zoneOrdonnee.setText("");
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
				PlacementPion.scoreJoueur1.setText("" + laGrille.compter('x'));
				PlacementPion.scoreJoueur2.setText("" + laGrille.compter('o'));
				PlacementPion.zoneAbscisse.setText("");
				PlacementPion.zoneOrdonnee.setText("");
				indiceRond++;
				grilleFini[81].setBackground(Color.BLACK);						//couleur du joueur 1
				setVisible(true);
			}
		}		
	}

	/**
	 * 
	 */
	public static void lancementPartie1v1() {
		FenAccueil.intJeu = new FenJeu();
	}
}
