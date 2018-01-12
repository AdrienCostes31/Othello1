/*
 *FenScore.java								2 juin 2017
 *IUT de Rodez 
 */
package Othello;

import OthelloCode.Alerte;
import OthelloCode.Sauvegarde;
import javax.swing.JOptionPane;

/**
 * @author Adrien
 *
 */
public class FenScore extends JOptionPane{
	
	/** Score*/
	String message = Sauvegarde.afficherClassement().toString();

	/**
	 * @param message 
	 * 
	 */
	public FenScore() {
		Alerte panique = new Alerte(message);
	}

}
