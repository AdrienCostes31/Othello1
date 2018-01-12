/*
 * Classe permettant de gérer les fichiers du jeu d'Othello
 */
package OthelloCode;
import java.io.File;
//import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * Cette classe contient des méthodes pour gérer les parties de l'application
 * " jeu d'Othello". Ces méthodes permettent de :
 *     - enregistrer dans un fichier un tableau à 1 dimension contenant des 
 *       chaînes de caractères. Ce tableau est supposé contenir des pseudo
 *     - lire le contenu d'un fichier supposé contenir le tableau des pseudo
 *        et renvoyer celui-ci sous la forme d'un tableau à
 *       1 dimensions contenant des chaînes de caractères
 *     - enregistrer dans un fichier un tableau à 1 dimension contenant des 
 *       entiers. Ce tableau est supposé contenir les meilleurs scores de joueurs
 *     - lire le contenu d'un fichier supposé contenir le tableau des scores
 *       et renvoyer celui-ci sous la forme d'un tableau à 1 dimension
 *       contenant des entiers
 *     - enregistrer une partie dans un fichier 
 *     - charger une partie contenue dans un fichier
 * @author Michael Descamps
 * @version 1.0
 */
public class OutilFichier {
	 /**
     * Nom du fichier dans lequel est enregistré le tableau à 1 dimension
     * contenant les pseudo.
     */
    private final static String NOM_FICHIER_JOUEUR = "fichier_joueur.bin";
    
    /**
     * Nom du fichier dans lequel est enregistré le tableau à 1 dimension
     * contenant le meilleur scores des joueurs.
     */
    private final static String NOM_FICHIER_SCORE = "fichier_score.bin";
    

    
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_JOUEUR
     * le tableau contenant les pseudo des joueurs. Il s'agit d'un tableau
     * à 1 dimension contenant des valeurs de type String.
     * @param table  tableau à 1 dimensions contenant des String
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effectuée
     */
    public static boolean enregistrerTabPseudoJoueur(String[] table) {
        boolean reussi = true;      // vrai ssi l'enregistrement a réussi
        
        // création et ouverture du fichier NOM_FICHIER_PAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_JOUEUR))) {
                       
            // on écrit l'objet argument dans le fichier
            fichier.writeObject(table);  
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'accès au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des pseudo.
     * Les pseudo sont supposées être présentes dans le fichier de nom 
     * NOM_FICHIER_JOUEUR, sous la forme d'un tableau à 2 dimensions contenant
     * des valeurs de type String
     * @return un tableau à 1 dimensions contenant des chaînes de caractères
     *         ou bien la valeur null si un problème empêche l'accès au fichier
     */
    public static String[] restaurerTabPseudoJoueur()  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
        String[] tampon = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                                            new FileInputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_JOUEUR))) {           
            
            // lecture de l'objet contenu dans le fichier
            tampon = (String[]) fichier.readObject();
            
        } catch (ClassNotFoundException erreur) {
            
            // la donnée présente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions    
        } catch (IOException erreur) {
            
            // problème d'accès au fichier
        }
        return tampon;
    }
    
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_SCORE
     * le tableau à 1 dimension contenant les horaires de bus.
     * Ce tableau contient des entiers
     * @param table  tableau à 1 dimension contenant des entiers
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effectuée
     */
    public static boolean enregistrerScore(int[] table) {
        boolean reussi = true;      // vrai ssi l'enregistrement a réussi
        
        // création et ouverture du fichier NOM_FICHIER_HORAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                                             new FileOutputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_SCORE))) {
                       
            // on écrit l'objet argument dans le fichier
            fichier.writeObject(table);  
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'accès au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des scores.
     * Les scores sont supposés être présents dans le fichier de nom 
     * NOM_FICHIER_SCORE, sous la forme d'un tableau à 1 dimension contenant
     * des valeurs de type entier
     * @return un tableau à 1 dimension contenant des entiers
     *         ou bien la valeur null si un problème empêche l'accès au fichier
     */
    public static int[] restaurerScore()  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
        int[] tampon = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                    new FileInputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_SCORE))) {           
        	
            // lecture de l'objet contenu dans le fichier
            tampon = (int[]) fichier.readObject();
            
        } catch (ClassNotFoundException erreur) {
            
            // la donnée présente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions    
        } catch (IOException erreur) {
            
            // problème d'accès au fichier
        }
        return tampon;
    }
    

    
    /**
     * Restauration d'une partie enregistrée.
     * La partie est supposée être présente dans le dossier Parties 
     * @param nomP nom de la partie a charger
     * @return p une partie 
     *         ou bien la valeur null si un problème empêche l'accès au fichier
     */
    public static Partie restaurerPartie(String nomP)  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
        Partie tampon = null;
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try { 
			ObjectInputStream fichier = new ObjectInputStream(
                                            new FileInputStream("Parties"+File.separator+nomP+".bin"));          
            // lecture de l'objet contenu dans le fichier
			 tampon =  (Partie) fichier.readObject();
			 fichier.close();
            
        } catch (ClassNotFoundException erreur) {     
            // la donnée présente dans le fichier n'est pas un objet  
        	erreur.printStackTrace();
        } catch (ClassCastException erreur) {
                
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions  
        	erreur.printStackTrace();
        } catch (FileNotFoundException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} catch (IOException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
        return tampon;
    }
    
    
    

    
    /**
     * Enregistre dans un fichier une partie 
     * @param p une partie
     * @param nomP nom donné a la partie 
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effecuée
     */
    public static boolean enregistrerUnePartie(Partie p,String nomP) {
    	boolean reussi = true;      // vrai ssi l'enregistrement a réussi
    	

    	// création et ouverture du fichier NOM_FICHIER_PAIRE

    	ObjectOutputStream fichier;
    	//System.out.println("interfaces de partie = " + partie.getClass().getInterfaces());
    	try {
    		fichier = new ObjectOutputStream(
    				      new FileOutputStream("Parties"+File.separator+nomP+".bin"));
	    		// on écrit l'objet argument dans le fichier
    		fichier.writeObject(p); 
    		fichier.close();
    		System.out.println("La partie a été enregistrée avec succès");
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		reussi = false;
    	}

        return reussi;        
    }
    
    /**
     * Supprime le fichier d'une partie
     * @param nomP nom de la partie a supprimer 
     * @return un booléen égal à vrai ssi la suppression a bien été effecuée
     */
    public static boolean supprimerFichPartie(String nomP) {
    	boolean reussi = true;      // vrai ssi l'enregistrement a réussi
    	

    	// création et ouverture du fichier NOM_FICHIER_PAIRE

    	File fichier;
    	fichier = new File("Parties"+File.separator+nomP+".bin");
			// on écrit l'objet argument dans le fichier
		if(fichier.delete()){
			System.out.println("La partie a été supprimée avec succès");
			return reussi;
		}else{
			System.out.println("La partie suppression a échoué");
			reussi=false;
			return reussi;
		}
    }
    
}
	
