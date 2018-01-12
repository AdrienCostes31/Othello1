/*
 * Humain.java                                          13 mai 2017
 * IUT info1 2016-2017 groupe 2, pas de droits
 */
package OthelloCode;
import java.io.Serializable;
import java.util.Scanner;


/** TODO commenter les responsabilités de cette classe
 * @author Antoine Bouysse
 *
 */
public class Humain implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5052995015586764330L;

	/** Pseudo */
    private String pseudo;
    
    /** Score */
    private int score;
    
    /** tampon */
    public static Scanner entree = new Scanner(System.in);
    
    /** 
     * Constructeur
     */
    public Humain(){
        pseudo= saisiePseudo();
        score = 0;
    }
    
    /**
	 * @return //TODO
	 */
	private String saisiePseudo() {
		System.out.println("Entrer votre pseudo");
		pseudo= entree.next();
		return pseudo;
	}

	/** 
     * Demande le pseudo au joueur
	 * @param pseudo 
     */
    public void setPseudo(String pseudo){
        System.out.print("\nRentrez votre pseudo :");
        pseudo= entree.nextLine();
       
    }
    
    /** 
     * Set le score du joueur
     * @param s 
     */
    public void setScore(int s){
        score= s;
    }
    
    /** affiche le pseudo
     * @return pseudo
     */
    public String getPseudo(){
        return pseudo ;
        
    }
    
    /** affiche le score
     * @return score
     */
    public int getScore(){
        return score;
        
    }

 
}
