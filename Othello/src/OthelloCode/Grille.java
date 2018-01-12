/* Grille de jeu */
package OthelloCode;
import java.io.Serializable;
import java.util.*;

import Othello.Fen1vIA;
import Othello.FenJeu;


/** 
 * Classe qui g�re le plateau
 * @author antoine.bouysse
 * @version ihm
 */
public class Grille implements Serializable {

	/**  //TODO*/
	private int retourne;

	/** nombre de lignes */
	private int nbLig;

	/** nombre de colonnes */
	private int nbCol;


	/** tableau 2 dimensions pour former le plateau */
	public char[][] grille;

	/** tampon */
	static Scanner entree = new Scanner(System.in);

	/** Donn�es sur les directions */
	public final static int NORDOUEST = 0;
	/** Donn�es sur les directions */
	public final static int NORD = 1;
	/** Donn�es sur les directions */
	public final static int NORDEST = 2;
	/** Donn�es sur les directions */
	public final static int OUEST = 3;
	/** Donn�es sur les directions */
	public final static int EST = 4;
	/** Donn�es sur les directions */
	public final static int SUDOUEST = 5;
	/** Donn�es sur les directions */
	public final static int SUD = 6;
	/** Donn�es sur les directions */
	public final static int SUDEST = 7;

	/** 
	 * Constructeur de la grille de jeu
	 * par d�faut dimension de 9x9
	 */
	public Grille(){
		nbLig=9;
		nbCol=9;
		grille = new char[nbLig][nbCol];


		for(int i=0; i<nbLig; i++){
			for(int j=0; j<nbCol; j++){
				grille[i][j] = '.';
			}
		}
	}

	/** 
	 * Constructeur avec param�tres 
	 * @param n
	 * @param p
	 */
	public Grille(int n, int p){
		nbLig=n;
		nbCol=p;
		grille = new char[nbLig][nbCol];

		for(int i=0; i<nbLig; i++){
			for(int j=0; j<nbCol; j++){
				grille[i][j] = '.';
			}
		}
	}

	/**
	 * @return TODO
	 */
	public int getRetourne() {
		return retourne;
	}


	/** 
	 * Affiche le type de char aux 
	 * coordonn�es indiqu�s
	 * @param l
	 * @param c
	 * @return char
	 */
	public char getCase(int l, int c){
		return grille[l][c];
	}






	/**
	 * M�thode qui initialise la grille 
	 * au d�but d'une partie
	 * num�rote et place les 4 premiers pions
	 */
	public void initialiser(){
		char lettre = 'A';
		char numero = '0';
		// Quadrillage
		for(int i=1; i<nbLig; i++){
			grille[i][0] = lettre++;
		}
		for(int j=0; j<nbCol; j++){
			grille[0][j] = numero++;
		}

		// placement 1er pions noir et blanc

		grille[4][4] = grille[5][5] = 'o';
		grille[4][5] = grille[5][4] = 'x';

	}


	/**
	 * M�thode affichant la grille
	 */
	public void afficher(){
		System.out.println();

		for(int i=0; i<nbLig; i++){
			System.out.print("\t");
			for(int j=0; j<nbCol; j++){
				System.out.print(" | " + grille[i][j] );
			}

			System.out.println(" | ");
		}
		System.out.println();
	}


	/**
	 * M�thode qui place les pions
	 * @param l ligne 
	 * @param c colonne
	 * @param p type de pion 
	 * @return arrayList
	 */
	public ArrayList<int[]> placer(int l, int c, char p){

		// Array list
		ArrayList<int[]> array = new ArrayList<int[]>();

			FenJeu.laGrille.grille[l][c]=p;
			// Nord Ouest
			retourne = nbRetourner(l, c, p, NORDOUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l-i][c-i]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}
			// Nord
			retourne = nbRetourner(l, c, p, NORD);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l-i][c]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c;
				array.add(tabCoord);
			}                      
			// Nord Est
			retourne = nbRetourner(l, c, p, NORDEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l-i][c+i]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}                      
			// Ouest
			retourne = nbRetourner(l, c, p, OUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l][c-i]=p;
				tabCoord[0] = l;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}                      
			// Est
			retourne = nbRetourner(l, c, p, EST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l][c+i]=p;
				tabCoord[0] = l;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}                      
			// Sud Ouest
			retourne = nbRetourner(l, c, p, SUDOUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l+i][c-i]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}                      
			// Sud
			retourne = nbRetourner(l, c, p, SUD);
			for(int i=1; i<retourne+1; i++){   
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l+i][c]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c;
				array.add(tabCoord);                
			}                     
			// Sud Est
			retourne = nbRetourner(l, c, p, SUDEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				FenJeu.laGrille.grille[l+i][c+i]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}
			
		
		return array;
	}


	/**
	 * M�thode qui place les pions
	 * @param l ligne 
	 * @param c colonne
	 * @param p type de pion 
	 * @return arrayList
	 */
	public ArrayList<int[]> placerIA(int l, int c, char p){

		// Array list
		ArrayList<int[]> array = new ArrayList<int[]>();

			Fen1vIA.laGrille.grille[l][c]=p;
			// Nord Ouest
			retourne = nbRetournerIA(l, c, p, NORDOUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l-i][c-i]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}
			// Nord
			retourne = nbRetournerIA(l, c, p, NORD);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l-i][c]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c;
				array.add(tabCoord);
			}                      
			// Nord Est
			retourne = nbRetournerIA(l, c, p, NORDEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l-i][c+i]=p;
				tabCoord[0] = l-i;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}                      
			// Ouest
			retourne = nbRetournerIA(l, c, p, OUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l][c-i]=p;
				tabCoord[0] = l;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}                      
			// Est
			retourne = nbRetournerIA(l, c, p, EST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l][c+i]=p;
				tabCoord[0] = l;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}                      
			// Sud Ouest
			retourne = nbRetournerIA(l, c, p, SUDOUEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l+i][c-i]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c-i;
				array.add(tabCoord);
			}                      
			// Sud
			retourne = nbRetournerIA(l, c, p, SUD);
			for(int i=1; i<retourne+1; i++){   
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l+i][c]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c;
				array.add(tabCoord);                
			}                     
			// Sud Est
			retourne = nbRetournerIA(l, c, p, SUDEST);
			for(int i=1; i<retourne+1; i++){
				int[] tabCoord=new int[2];
				Fen1vIA.laGrille.grille[l+i][c+i]=p;
				tabCoord[0] = l+i;
				tabCoord[1] = c+i;
				array.add(tabCoord);
			}
			
		
		return array;
	}
	

	/** 
	 * Retourne le nombre de coup possible � jouer
	 * @param tour 
	 * @return nombre
	 */
	public int nbCoupPossible(int tour){
		int nb=0;
		char p;
		if (tour%2==0) p='o';
		else p='x';
		for(int i=0; i<nbLig; i++){
			for(int j=0; j<nbCol; j++){
				if(coupPossible(i,j,p)>0)  nb++;         
			}
		}
		return nb;

	}

	
	/** 
	 * Retourne le nombre de coup possible � jouer
	 * @param tour 
	 * @return nombre
	 */
	public int nbCoupPossibleIA(int tour){
		int nb=0;
		char p;
		if (tour%2==0) p='o';
		else p='x';
		for(int i=0; i<nbLig; i++){
			for(int j=0; j<nbCol; j++){
				if(coupPossibleIA(i,j,p)>0)  nb++;         
			}
		}
		return nb;

	}
	

	/** 
	 * V�rifie que les coordonn�es indiquer sont possibles � jouer
	 * @param l ligne
	 * @param c colonne
	 * @param p pion
	 * @return retourne
	 */
	public int coupPossible(int l, int c, char p){

		int  retourne=0;

		// on cherche si notre pion peut �tre placer � cet endroit
		try{

			if(FenJeu.laGrille.grille[l][c]== '.'){ 
				// Nord Ouest
				retourne+=nbRetourner(l, c, p,NORDOUEST);
				// Nord 
				retourne+=nbRetourner(l, c, p, NORD);
				// Nord Est
				retourne+=nbRetourner(l, c, p, NORDEST);
				// Ouest
				retourne+=nbRetourner(l, c, p, OUEST);
				// Est
				retourne+=nbRetourner(l, c, p, EST);
				// Sud Ouest
				retourne+=nbRetourner(l, c, p, SUDOUEST);
				// Sud
				retourne+=nbRetourner(l, c, p, SUD);
				// Sud Est
				retourne+=nbRetourner(l, c, p, SUDEST);


			}
		}catch(ArrayIndexOutOfBoundsException e){
			Alerte intErreur = new Alerte("pion hors du tableau");
		}        

		return retourne;

	}

	
	/** 
	 * V�rifie que les coordonn�es indiquer sont possibles � jouer
	 * @param l ligne
	 * @param c colonne
	 * @param p pion
	 * @return retourne
	 */
	public int coupPossibleIA(int l, int c, char p){

		int  retourne=0;

		// on cherche si notre pion peut �tre placer � cet endroit
		try{

			if(Fen1vIA.laGrille.grille[l][c]== '.'){ 
				// Nord Ouest
				retourne+=nbRetournerIA(l, c, p,NORDOUEST);
				// Nord 
				retourne+=nbRetournerIA(l, c, p, NORD);
				// Nord Est
				retourne+=nbRetournerIA(l, c, p, NORDEST);
				// Ouest
				retourne+=nbRetournerIA(l, c, p, OUEST);
				// Est
				retourne+=nbRetournerIA(l, c, p, EST);
				// Sud Ouest
				retourne+=nbRetournerIA(l, c, p, SUDOUEST);
				// Sud
				retourne+=nbRetournerIA(l, c, p, SUD);
				// Sud Est
				retourne+=nbRetournerIA(l, c, p, SUDEST);


			}
		}catch(ArrayIndexOutOfBoundsException e){
			Alerte intErreur = new Alerte("pion hors du tableau");
		}        

		return retourne;

	}


	/** 
	 * Retourne les pions adverses si le pion qui a �t� placer
	 * entoure des pions adverses
	 * @param l 
	 * @param c 
	 * @param p 
	 * @param direction 
	 * @return nombre de pion � retourner
	 */
	public int nbRetourner(int l, int c, char p, int direction){
		char p2;
		int nb=0;
		int incrI;
		int incrJ;
		switch(direction){
		case NORDOUEST :
			incrI = -1;
			incrJ = -1;
			break;
		case NORD : 
			incrI = -1;
			incrJ = 0;
			break;
		case NORDEST : 
			incrI = -1;
			incrJ = +1;
			break;
		case OUEST : 
			incrI = 0;
			incrJ = -1;
			break;
		case EST : 
			incrI = 0;
			incrJ = +1;
			break;
		case SUDOUEST : 
			incrI = +1;
			incrJ = -1;
			break;
		case SUD : 
			incrI = +1;
			incrJ = 0;
			break;
		case SUDEST : 
			incrI = +1;
			incrJ = +1;
			break;
		default:
			incrI = 0;
			incrJ = 0;
		}


		l+=incrI;
		c+=incrJ;
		try{
			p2 = FenJeu.laGrille.grille[l][c];
			while((p2!=p) && (p2!='.')){
				nb++; 
				l+=incrI;
				c+=incrJ;
				p2=FenJeu.laGrille.grille[l][c];
			}
			if(p2=='.') nb=0;
		} catch(ArrayIndexOutOfBoundsException e){ 
			/* On a regard� hors de la grille */
			nb = 0;
		}
		return nb;
	}


	/** 
	 * Retourne les pions adverses si le pion qui a �t� placer
	 * entoure des pions adverses
	 * @param l 
	 * @param c 
	 * @param p 
	 * @param direction 
	 * @return nombre de pion � retourner
	 */
	public int nbRetournerIA(int l, int c, char p, int direction){
		char p2;
		int nb=0;
		int incrI;
		int incrJ;
		switch(direction){
		case NORDOUEST :
			incrI = -1;
			incrJ = -1;
			break;
		case NORD : 
			incrI = -1;
			incrJ = 0;
			break;
		case NORDEST : 
			incrI = -1;
			incrJ = +1;
			break;
		case OUEST : 
			incrI = 0;
			incrJ = -1;
			break;
		case EST : 
			incrI = 0;
			incrJ = +1;
			break;
		case SUDOUEST : 
			incrI = +1;
			incrJ = -1;
			break;
		case SUD : 
			incrI = +1;
			incrJ = 0;
			break;
		case SUDEST : 
			incrI = +1;
			incrJ = +1;
			break;
		default:
			incrI = 0;
			incrJ = 0;
		}


		l+=incrI;
		c+=incrJ;
		try{
			p2 = Fen1vIA.laGrille.grille[l][c];
			while((p2!=p) && (p2!='.')){
				nb++; 
				l+=incrI;
				c+=incrJ;
				p2=Fen1vIA.laGrille.grille[l][c];
			}
			if(p2=='.') nb=0;
		} catch(ArrayIndexOutOfBoundsException e){ 
			/* On a regard� hors de la grille */
			nb = 0;
		}
		return nb;
	}
	
	/**
	 * M�thode qui compte 
	 * le nombre de pion d'une couleur
	 * @param c char x ou o
	 * @return num�ro
	 */
	public int compter(char c){
		int num=0;
		for(int i=0; i<nbLig; i++){
			for(int j=0; j<nbCol; j++){
				if(grille[i][j]== c){
					num++;
				}
			}
		}
		return num;
	}

	/**
	 * m�thode qui convertit le char de 
	 * la ligne en num�ro de 1 a 8
	 * @param l char
	 * @return numL
	 */
	public static int convertirLigne(char l){
		int numL=-1;
		switch(l){
		case 'A': case 'a': numL=1;
		break;
		case 'B': case 'b': numL=2;
		break;
		case 'C': case 'c': numL=3;
		break;
		case 'D': case 'd': numL=4;
		break;
		case 'E': case 'e': numL=5;
		break;
		case 'F': case 'f': numL=6;
		break;
		case 'G': case 'g': numL=7;
		break;
		case 'H': case 'h': numL=8;
		break;
		default:break;

		}
		return numL;
	}


	/** 
	 * m�thode demandant la ligne et 
	 * la colonne a l'utilisateur
	 * @return ligneInt
	 */
	public static int[] demandeCoord(){
		char ligneChar;
		int colonne,
		ligneInt;
		// demande au joueur les coordonn�es du pion a placer
		System.out.println("Rentrez la position de votre nouveau pion " );
		System.out.print("Ligne (de A � H) : ");
		ligneChar = entree.next().charAt(0);
		do{
			entree.nextLine();
			System.out.print("Colonne (entre 0 et 8) : ");
		}while(!entree.hasNextInt());
		colonne = entree.nextInt();
		ligneInt= Grille.convertirLigne(ligneChar);
		return new int[] {ligneInt,colonne};
	}


}