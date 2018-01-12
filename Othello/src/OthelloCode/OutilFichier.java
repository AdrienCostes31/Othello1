/*
 * Classe permettant de g�rer les fichiers du jeu d'Othello
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
 * Cette classe contient des m�thodes pour g�rer les parties de l'application
 * " jeu d'Othello". Ces m�thodes permettent de :
 *     - enregistrer dans un fichier un tableau � 1 dimension contenant des 
 *       cha�nes de caract�res. Ce tableau est suppos� contenir des pseudo
 *     - lire le contenu d'un fichier suppos� contenir le tableau des pseudo
 *        et renvoyer celui-ci sous la forme d'un tableau �
 *       1 dimensions contenant des cha�nes de caract�res
 *     - enregistrer dans un fichier un tableau � 1 dimension contenant des 
 *       entiers. Ce tableau est suppos� contenir les meilleurs scores de joueurs
 *     - lire le contenu d'un fichier suppos� contenir le tableau des scores
 *       et renvoyer celui-ci sous la forme d'un tableau � 1 dimension
 *       contenant des entiers
 *     - enregistrer une partie dans un fichier 
 *     - charger une partie contenue dans un fichier
 * @author Michael Descamps
 * @version 1.0
 */
public class OutilFichier {
	 /**
     * Nom du fichier dans lequel est enregistr� le tableau � 1 dimension
     * contenant les pseudo.
     */
    private final static String NOM_FICHIER_JOUEUR = "fichier_joueur.bin";
    
    /**
     * Nom du fichier dans lequel est enregistr� le tableau � 1 dimension
     * contenant le meilleur scores des joueurs.
     */
    private final static String NOM_FICHIER_SCORE = "fichier_score.bin";
    

    
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_JOUEUR
     * le tableau contenant les pseudo des joueurs. Il s'agit d'un tableau
     * � 1 dimension contenant des valeurs de type String.
     * @param table  tableau � 1 dimensions contenant des String
     * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effectu�e
     */
    public static boolean enregistrerTabPseudoJoueur(String[] table) {
        boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
        
        // cr�ation et ouverture du fichier NOM_FICHIER_PAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_JOUEUR))) {
                       
            // on �crit l'objet argument dans le fichier
            fichier.writeObject(table);  
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'acc�s au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des pseudo.
     * Les pseudo sont suppos�es �tre pr�sentes dans le fichier de nom 
     * NOM_FICHIER_JOUEUR, sous la forme d'un tableau � 2 dimensions contenant
     * des valeurs de type String
     * @return un tableau � 1 dimensions contenant des cha�nes de caract�res
     *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
     */
    public static String[] restaurerTabPseudoJoueur()  {
        
        // objet tampon dans lequel est plac� l'objet lu dans le fichier  
        String[] tampon = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                                            new FileInputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_JOUEUR))) {           
            
            // lecture de l'objet contenu dans le fichier
            tampon = (String[]) fichier.readObject();
            
        } catch (ClassNotFoundException erreur) {
            
            // la donn�e pr�sente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
        } catch (IOException erreur) {
            
            // probl�me d'acc�s au fichier
        }
        return tampon;
    }
    
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_SCORE
     * le tableau � 1 dimension contenant les horaires de bus.
     * Ce tableau contient des entiers
     * @param table  tableau � 1 dimension contenant des entiers
     * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effectu�e
     */
    public static boolean enregistrerScore(int[] table) {
        boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
        
        // cr�ation et ouverture du fichier NOM_FICHIER_HORAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                                             new FileOutputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_SCORE))) {
                       
            // on �crit l'objet argument dans le fichier
            fichier.writeObject(table);  
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'acc�s au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des scores.
     * Les scores sont suppos�s �tre pr�sents dans le fichier de nom 
     * NOM_FICHIER_SCORE, sous la forme d'un tableau � 1 dimension contenant
     * des valeurs de type entier
     * @return un tableau � 1 dimension contenant des entiers
     *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
     */
    public static int[] restaurerScore()  {
        
        // objet tampon dans lequel est plac� l'objet lu dans le fichier  
        int[] tampon = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                    new FileInputStream("DonneesJoueurs"+File.separator+NOM_FICHIER_SCORE))) {           
        	
            // lecture de l'objet contenu dans le fichier
            tampon = (int[]) fichier.readObject();
            
        } catch (ClassNotFoundException erreur) {
            
            // la donn�e pr�sente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
        } catch (IOException erreur) {
            
            // probl�me d'acc�s au fichier
        }
        return tampon;
    }
    

    
    /**
     * Restauration d'une partie enregistr�e.
     * La partie est suppos�e �tre pr�sente dans le dossier Parties 
     * @param nomP nom de la partie a charger
     * @return p une partie 
     *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
     */
    public static Partie restaurerPartie(String nomP)  {
        
        // objet tampon dans lequel est plac� l'objet lu dans le fichier  
        Partie tampon = null;
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try { 
			ObjectInputStream fichier = new ObjectInputStream(
                                            new FileInputStream("Parties"+File.separator+nomP+".bin"));          
            // lecture de l'objet contenu dans le fichier
			 tampon =  (Partie) fichier.readObject();
			 fichier.close();
            
        } catch (ClassNotFoundException erreur) {     
            // la donn�e pr�sente dans le fichier n'est pas un objet  
        	erreur.printStackTrace();
        } catch (ClassCastException erreur) {
                
            // la donn�e lue dans le fichier n'est pas un objet � 2 dimensions  
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
     * @param nomP nom donn� a la partie 
     * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effecu�e
     */
    public static boolean enregistrerUnePartie(Partie p,String nomP) {
    	boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
    	

    	// cr�ation et ouverture du fichier NOM_FICHIER_PAIRE

    	ObjectOutputStream fichier;
    	//System.out.println("interfaces de partie = " + partie.getClass().getInterfaces());
    	try {
    		fichier = new ObjectOutputStream(
    				      new FileOutputStream("Parties"+File.separator+nomP+".bin"));
	    		// on �crit l'objet argument dans le fichier
    		fichier.writeObject(p); 
    		fichier.close();
    		System.out.println("La partie a �t� enregistr�e avec succ�s");
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
     * @return un bool�en �gal � vrai ssi la suppression a bien �t� effecu�e
     */
    public static boolean supprimerFichPartie(String nomP) {
    	boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
    	

    	// cr�ation et ouverture du fichier NOM_FICHIER_PAIRE

    	File fichier;
    	fichier = new File("Parties"+File.separator+nomP+".bin");
			// on �crit l'objet argument dans le fichier
		if(fichier.delete()){
			System.out.println("La partie a �t� supprim�e avec succ�s");
			return reussi;
		}else{
			System.out.println("La partie suppression a �chou�");
			reussi=false;
			return reussi;
		}
    }
    
}
	
