/*
 *MainJeu.java								9 mai 2017
 *IUT de Rodez 
 */
package Othello;

/**
 * Ce programme permet d'accéder à l'interface de l'accueil
 * du jeu. Puis via l'interface de l'accueil l'utilisateur 
 * pourra choisir le mode de jeu, le score ou l'aide. 
 * @author Adrien Costes
 */
public class MainJeu {

	/**
	 * Dans ce programme, il y aura la création d'une fenêtre
	 * accueil. Sur cette même fenêtre sera présent 5 boutons: 
	 * 	- un bouton pour consulter les scores enregistrer qui seront
	 * 	  affiché grâce à une méthode présente dans une autre classe.
	 * 	- un bouton pour consulter l'aide qui sera affiché grâce à une 
	 * 	  méthode présente dans une autre classe.
	 * 	- trois boutons permettant à l'utilisateur de choisir s'il veut 
	 * 	  jouer contre une autre personne, l'ordinateur ou s'il veut reprendre
	 * 	  une partie en cours.
	 * Si l'utilisateur choisi de jouer contre l'ordinateur, il aura la 
	 * possibilité de choisir la difficulté avec laquelle jouera l'ordinateur.
	 * @param args unused
	 */
	public static void main(String[] args) {
		
		/*Partie de l'interface de l'accueil du jeu*/
		FenAccueil.lancementFenAccueil();
	}
}
