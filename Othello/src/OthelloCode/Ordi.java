/*
 * Ordi.java                                          13 mai 2017
 * IUT info1 2016-2017 groupe 2, pas de droits
 */
package OthelloCode;

import java.io.Serializable;
import java.util.Scanner;

import Othello.Fen1vIA;

/** TODO commenter les responsabilités de cette classe
 * @author Antoine Bouysse
 *
 */
public class Ordi implements Serializable {

	/**
	 * numéro de version pour sérialisation
	 */
	private static final long serialVersionUID = -7039545447292946724L;

	/** Niveau de l'IA */
	private int niveau;

	/**
	 * //TODO
	 */
	private static Scanner entree= new Scanner (System.in);

	/** Grille de jeu */
	private Grille grille;

	/** Score */
	private int score;

	/** tableau de numérotation des cases */
	public  int [][] tabPoint=new int [][]{
		{500 ,-150,30 ,10 ,10,30,-150,500},
		{-150,-250,0  ,0  ,0,0,-250,-150},
		{30  ,0   ,1  ,2  ,2,1,0,30},
		{10  ,0   ,2  ,16 ,16,2,0,10},
		{10  ,0   ,2  ,16 ,16,2,0,10},
		{30  ,0   ,1  ,2  ,2,1,0,30},
		{-150,-250,0  ,0  ,0,0,-250,-150},
		{500 ,-150,30 ,10,10,30,-150,500}
	};      



	/** Constructeur IA
	 * @param g 
	 */
	public Ordi(Grille g){
		niveau = 0;
		grille = g;
		score =0;
	}


	/** 
	 * niveau débutant de l'IA
	 * @param tour 
	 * @return int[] coordonnées placement
	 */
	public int[] debutant(int tour){
		int l=0,
			c=0,
			max=0;
		char p;
		if (tour%2==1 && tour%2 != 0) p='o';
		else p='x';

		if( Fen1vIA.laGrille.nbCoupPossibleIA(tour)>0){
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					if(Fen1vIA.laGrille.coupPossibleIA(i,j,p) > max){
						System.out.println(p);
						max=Fen1vIA.laGrille.coupPossibleIA(i,j,p);
						l=i;
						c=j;
					}
				}
			}
		}

		return new int[] {c,l};
	}

	/** 
	 * niveau difficile de l'IA
	 * @param tour 
	 * @return int[] coordonnées placement
	 */
	public int[] difficile(int tour){
		// niveau 2
		int l=0,
				c=0,
				point=0,    // importance de la position
				max=-251;      // point d'importance max  grille.coupPossible(i,j,p)+

		char p;
		if (tour%2==1 && tour != 0) p='o';
		else p='x';

		if( Fen1vIA.laGrille.nbCoupPossibleIA(tour)>0){

			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){

					if (Fen1vIA.laGrille.coupPossibleIA(i,j,p) > 0){
						point =  Fen1vIA.laGrille.coupPossibleIA(i,j,p) + tabPoint[i-1][j-1]  ;
						if (point > max ){
							max = point;
							l=i;
							c=j;
						}
					} 
				}
			}
			return new int[] {c,l};
		}else {
			return new int[] {0,0};
		}


	}


	/** 
	 * Set le score du joueur
	 * @param s 
	 */
	public void setScore(int s){
		score= s;
	}


	/**
	 * @return niveau
	 */
	public int getNiveau() {
		return niveau;
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
	 * Demande le niveau au joueur
	 * @param n niveau
	 */
	public void setNiveau(int n){
		niveau=n;

	}



}
// tour de l'IA


