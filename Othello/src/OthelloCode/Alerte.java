/*
 *Alerte.java								1 juin 2017
 *IUT de Rodez 
 */
package OthelloCode;

import javax.swing.JOptionPane;

/**
 * @author Adrien
 *
 */
public class Alerte extends JOptionPane {

	/**
	 * @param message 
	 * 
	 */
	public Alerte(String message) {
		showMessageDialog(null, message);
	}

}
