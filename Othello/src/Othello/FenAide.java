/*
 *FenAide.java								17 mai 2017
 *IUT de Rodez 
 */
package Othello;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import OthelloCode.Alerte;
import OthelloCode.Partie;

/**
 * @author Adrien
 *
 */
public class FenAide extends JFrame{
	
	/**Paragraphe d'aide pour l'utilisateur*/
	private static final String PHRASE_AIDE = Partie.Aide();
	
	/**
	 * 
	 */
	public FenAide() {
		Alerte malibu = new Alerte(PHRASE_AIDE);	//on a paniqué
	}
}
