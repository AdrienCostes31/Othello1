/*
 * Partie.java                                          6 mai 2017
 * IUT info1 2016-2017 groupe 2, pas de droits
 */
package OthelloCode;

import java.io.Serializable;
import java.util.Scanner;

import Othello.Fen1vIA;
import Othello.FenJeu;


/** 
 * Classe qui gère la partie
 * @author Antoine Bouysse && Michael Descamps
 *
 */
public class Partie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476367932640158193L;

	/** grille associé à la partie */
	private  Grille grille;

	/** Mode de la partie (ex: 1v1)*/
	private int mode;

	/** Joueur 1*/
	public Humain j1;

	/** Joueur 2*/
	private  Humain j2;

	/** IA*/
	private  Ordi ia;

	/** tampon */
	public static Scanner entree = new Scanner(System.in);







	/** 
	 * Constructeur 1 v 1
	 */
	public Partie(){

		grille= FenJeu.laGrille;

		mode=2;
		// création du joueur 1
		j1 = new Humain();
		// création du joueur 
		j2 = new Humain();

	}

	/** 
	 * Constructeur 1 v IA
	 * @param m mode
	 */
	public Partie(int m){

		grille= Fen1vIA.laGrille;
		mode=m;
		// création du joueur 1
		//j1 = new Humain();
		// création de l'IA
		ia = new Ordi(Fen1vIA.laGrille);

	}





	/** 
	 * Interface Menu principale
	 * @return option choisi par user
	 */
	public static int Menu(){
		int option=0;
		System.out.println("\n");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n\n\t\t     Othello");
		System.out.println("\n\t\t Menu Principale ");
		System.out.println("\n\n\t1  :  Nouvelle Partie ");
		System.out.println("\t2  :  Charger une partie ");
		System.out.println("\t3  :  Classement ");
		System.out.println("\t4  :  Aide ");

		do{            
			System.out.print("\n\t--->   ");           
			option= entree.hasNextInt() ? entree.nextInt() : -1 ;
			entree.nextLine();

			if (option <1 || option > 4 ){
				System.out.println("Veuillez choisir une option existante !!");
			}

		}while(option <1 || option > 4 );

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		return option;

	}





	/** 
	 * Demande à l'utilisateur le niveau de 
	 * difficulté de l'IA
	 * @return niveau
	 */
	public static int demanderNiveauIA(){
		int niveau=0;
		System.out.println("\nChoisissez le niveau de l'IA:");
		System.out.println("1 -> Facile \n2-> Difficile");
		do{            

			System.out.print("\nVotre choix : ");           
			niveau = entree.hasNextInt() ? entree.nextInt() : -1 ;
			entree.nextLine();

			if (niveau <1 || niveau > 2 ){
				System.out.println("Veuillez choisir un mode de jeu existant !!");
			}

		}while(niveau <1 || niveau > 2 );
		return niveau;
	}


	/**
	 * 
	 * @param p
	 */
	public void initialiserPartieContreOrdi(){
		int niveau=-1;
		// Demande du niveau de l'IA
		niveau = Ordi.demanderNiveauIA();

		//Set le niveau de l'IA
		ia.setNiveau(niveau);
	}


	/** 
	 * Mode de jeu 1v1
	 * @param g Grille
	 */
	public void mode1v1(){

		int[] coord = new int[2];

		// Variables
		char pion;        // type de pion

		int ligneInt,      // coordonnée ligne
		tour=0,          // tour 
		colonne;       // coordonnée colonne 

		// Début du jeu 1vs1 tant que la grille n'est pas pleine
		while(FenJeu.laGrille.compter('.')>0){
			
			if(tour%2==0){
				pion='o';
			}else {
				pion='x';
			}

			if(FenJeu.laGrille.nbCoupPossible(tour)==0){ 
				System.out.println("Vous ne pouvez pas jouer! Joueur suivant");
			} else{
				// demande au joueur les coordonnÃ©s du pion a placer

				do {

					coord[0] = FenJeu.ordonnee;
					coord[1] = FenJeu.abscisse;
					ligneInt=coord[0];
					colonne=coord[1];
					
					if (ligneInt==-1){
						return;
					}

				}while(FenJeu.laGrille.coupPossible(ligneInt,colonne,pion)==0);   // redemander lors d'une erreur 

			}

			tour++;        

		}

		if(FenJeu.laGrille.compter('o')> FenJeu.laGrille.compter('x')){
			System.out.println("\n\tLe joueur 1 (o) a gagné !!!");
			System.out.println("\n\tLe joueur 2 (x) est nul !!!");


		}
	}


	/** 
	 * Mode de jeu contre ordi
	 */
	public void mode1vIA(){

		// Variables
		char pion;        // type de pion
		int[] coord2={0,0};     
		int numx=0,          // nombre de pion x
				numo=0,          // nombre de pion o
				ligneInt,      // coordonnée ligne
				tour=0,          // tour 
				// niveau de l'IA
				colonne;       // coordonnée colonne 

		// Début du jeu 1vsIA tant que la grille n'est pas pleine
		while(Fen1vIA.laGrille.compter('.')>0){
			
			if(tour%2==0){ 
				pion='o';
			}else {
				pion='x';
			}

			// Compte le nombre de pion de chaque couleur

			numo= Fen1vIA.laGrille.compter('o');
			numx =Fen1vIA.laGrille.compter('x');
			j1.setScore(numo); 
			ia.setScore(numx);                
			System.out.println("\nNombre de o : " + numo + "\n");
			System.out.println("Nombre de x : " + numx + "\n");
			
			if(tour%2==0){
				if(Fen1vIA.laGrille.nbCoupPossible(tour)==0){ 
					System.out.println("Vous ne pouvez pas jouer! Joueur suivant");
				} else{
					
					do {

						int coord1[] = Grille.demandeCoord();
						ligneInt=coord1[0];
						colonne=coord1[1];
						if (ligneInt==-1){
							return;
						}

					}while(Fen1vIA.laGrille.coupPossibleIA(ligneInt,colonne,pion)==0);   // redemander lors d'une erreur


				}
			}else{
				try {
					System.out.println("L'ordinateur réfléchit...");
					Thread.sleep(2000); // 2 secondes 
				} catch (InterruptedException ex) {
					// handle error
				}
				// tour de l'IA

				if(Fen1vIA.laGrille.nbCoupPossibleIA(tour)!=0){ 
					// tour de l'IA
					if (ia.getNiveau()==1){
						coord2 = ia.debutant(tour);
					} else if(ia.getNiveau()==2){
						coord2 = ia.difficile(tour);
					}

					Fen1vIA.laGrille.placerIA(coord2[0],coord2[1],pion);
				}
			}

			tour++;        

			System.out.println();   // saut de lignes

		}

		grille.afficher(); 
		j1.setScore(numo);  
		if(Fen1vIA.laGrille.compter('o')>Fen1vIA.laGrille.compter('x')){
			System.out.println("\n\t"+ j1.getPseudo() +" (o) a gagné avec " +
					+ j1.getScore() +" pion !!!");
			System.out.println("\n\tL'ordinateur n'a rien pu faire face à vous" +
					" il n'a que "+ numx +" pion !!!");

		}


	}


	/**
	 * 
	 */
	public void reprendrePartie (){
		if (mode==1){
			mode1vIA();

		}else{
			mode1v1();
		}
	}

	/** 
	 * Méthode qui affiche les règles du jeu
	 * @return //TODO
	 * 
	 */
	public static String Aide(){
		return("1: Vous lancez une nouvelle partie en mode 1 joueur ou en mode 1 contre 1.\n"
				+ "2: Charger une partie ayant été sauvegardée précédemment.\n"
				+ "3: Afficher le classement des meilleurs joueurs."
				+ "\t\tComment jouer à l'Othello !!"
				+ "\n\tL'objectif est d'avoir le plus de pions à la fin de la partie."
				+ "\n\n\tA chaque tour, vous placez un pion de votre couleur sur un espace vide.\n Vous ne pouvez placer un pion qu'aux endroits où vous capturez au moins l'un de ceux de votre adversaire."
				+ "\n\n\tVous capturez un ou plusieurs pions de votre adversaire \n si ils se trouvent entre l'un de vos pions et celui que vous posez sur le plateau."
				+ "\n\n\tVous pouvez capturer dans les huit directions à la fois \n- vers le haut, vers bas, à droite, à gauche, dans les quatre diagonales."
				+ "\nLe jeu est fini lorsque toutes les cases sont remplies - ou lorsque \naucun des deux joueurs ne peut poser de pion. \n\nSi un joueur ne peut pas placer un pion, mais les autres le peuvent, il doit alors sauter son tour.");

	}
	
	/**
	 * @return ia le joueur ordinateur
	 */
	public Ordi getIa() {
		return ia;
	}
}










