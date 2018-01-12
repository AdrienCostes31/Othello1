/*
 * Joueur.java                                          13 mai 2017
 * IUT info1 2016-2017 groupe 2, pas de droits
 */
package OthelloCode;

import java.io.Serializable;

/** TODO commenter les responsabilités de cette classe
 * @author Antoine Bouysse
 *
 */
public abstract class Joueur implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3004796310729705420L;
	
	/** Score */
    private int score;
    
    /** 
     * Set le score du joueur
     * @param s 
     */
    public void setScore(int s){
        score= s;
    }
    
    /** affiche le score
     * @return score
     */
    public int getScore(){
        return score;
        
    }
}
