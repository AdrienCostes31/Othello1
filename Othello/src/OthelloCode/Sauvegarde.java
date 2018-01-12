/**Cette classe g�re les enregistrements des joueurs 
 * 
 */
package OthelloCode;

import java.io.File;
import java.util.Scanner;

/**
 * Cette classe permet de g�rer les sauvegardes des parties.
 * Elle comprend des m�thodes pour:
 * - enregistrer un pseudo
 * - enregistrer le meilleur score d'un joueur
 * - v�rifier qu'un fichier est bien pr�sent dans le dossier "Parties"
 * - sauvegarder une partie
 * - charger une partie
 * - afficher le classement des joueurs
 * @author Michael Descamps
 *
 */
public class Sauvegarde {



    /** scanner */
    static Scanner entree = new Scanner (System.in);
    
    /**
     * Cette m�thode permet d'enregistrer un pseudo d'un joueur au tableau des 
     * pseudos si il n'est pas deja pr�sent
     * @param pseudo
     * @return col position du pseudo dans le tableau
     */
    public static int enregistrerPseudo(String pseudo){
        String[] tableJ= OutilFichier.restaurerTabPseudoJoueur(); // tableau contenant tout les pseudos 
        int col=0;
		
        for (col=0; tableJ[col]!=null && col< tableJ.length;col++){
            if (tableJ[col].equals(pseudo)){
				return col;
            }	
        }
		
		tableJ[col]=pseudo;
        OutilFichier.enregistrerTabPseudoJoueur(tableJ);

        return col;} // position du pseudo dans le tableau

    /**
     * Cette m�thode permet d'enregistrer un score a un joueurs qui est dans le tableau des 
     * pseudos.
     * @param col colonne ou se trouve le pseudo du joueur 
     * @param scoreJ tableau des meilleurs scores  
     */

    public static void enregistrerMeilleurScore(int col,int scoreJ){
        int[] tableSc= OutilFichier.restaurerScore();	
        if (tableSc[col]<scoreJ){
            tableSc[col]=scoreJ;
        }
        OutilFichier.enregistrerScore(tableSc);
    }

    /**
     * Cette m�thode permet de sauvegarder une partie
     * @param p la partie a sauvegard�
     * @throws InterruptedException 
     */

    public static void sauvegarderPartie(Partie p) throws InterruptedException{

        String choix=null,
                nomP=null;
        System.out.println("Voulez vous enregistrez la partie? (o ou n)");
        choix = entree.next();
        if (choix.equals("o")){
            System.out.println("Entrer le nom de la partie a enregistr�");
            nomP=entree.next();	// Nom de la partie � enrgistrer		
            if(verifFichEx(nomP+".bin")){
                System.out.println("Voulez vous �craser la sauvegarde de "+nomP+" pr�c�dente (o ou n)");
                choix = entree.next();
                if (choix.equals("o")){
                    OutilFichier.enregistrerUnePartie(p, nomP);
                }
            }else{
                OutilFichier.enregistrerUnePartie(p, nomP);
            }
        }
    }




    /**
     * M�thode permettant de v�rifier si un fichier est pr�sent dasn le dossier partie
     * ou non.
     * @param nomF nom du fichier
     * @return true fichier pr�sent else fichier non pr�sent
     */
    private static boolean verifFichEx(String nomF) {
        boolean deja_present=false;
        File monRepertoire=new File("Parties");
        String[] f = monRepertoire.list();
        for (int i=0;i<f.length;i++){
            if (nomF.equals(f[i])){
                deja_present=true;
                return deja_present;
            }
        }
        return deja_present;
    }
    /**
     * M�thode permettant de charger une partie
     * @return la partie a charg�e
     * 			null si aucune partie a charg�
     */
    public static Partie chargerPartie(){
        boolean valide=false;
        String nomP= null;
        System.out.println("Quelle partie voulez-vous charger ? ");
        File monRepertoire=new File("Parties");// consultation du dossier "Parties"
        String[] f = monRepertoire.list();
        if (f.length==0){
            System.out.println("Aucune partie sauvegard�e");
        }else{
            for (int i=0;i<f.length;i++){
                System.out.println(f[i].substring(0,f[i].length()-4));
            }
            do{
                nomP=entree.next();
                if (verifFichEx(nomP+".bin")){
                    valide=true;
                }else{
                    System.out.println("Entrer un nom de partie valide");
                } 
            }while(!valide);
            return (OutilFichier.restaurerPartie(nomP));
        }
        return null;
    }

    /**
     * M�thode permettant d'afficher le classement
     * @return //TODO
     */
    public static String afficherClassement(){
    	String message = "";
        int[] tableSc= OutilFichier.restaurerScore();
        String[] tableJ= OutilFichier.restaurerTabPseudoJoueur(); 
        int aInsererl1;
        String aInsererl2;   // �l�ment � ins�rer � une �tape du tri par INSERTION
        int place;         
        // tri par insertion
        for (int etape =0; tableJ[etape]!= null && etape < tableJ.length; etape++) {
            aInsererl1 = tableSc[etape];
            aInsererl2 = tableJ[etape];	
            for (place = etape; place > 0 && tableSc[place -1] < aInsererl1 ; place--) {
                tableSc[place] = tableSc[place -1];
                tableJ[place] = tableJ[place -1];// d�calage de 1 rang vers la fin
            } 
            tableSc[place] = aInsererl1;
            tableJ[place] = aInsererl2;
        }

        for (int i=0;tableJ[i]!=null && i < 10;i++){
            if (i==0){
                message += (i+1)+"er "+tableJ[i]+" "+tableSc[i] + "\n";
            }else{
                message += (i+1)+"�me "+tableJ[i]+" "+tableSc[i] + "\n";
            }
        }
        return message;
    }
    
    /**
     * Permet de supprimer une partie enregistr�e
     * @throws InterruptedException 
     * 
     */
    
    public static void supprimerPartie() throws InterruptedException{
    	 boolean valide=false;
         String nomP= null;
         System.out.println("Quelle partie voulez-vous supprimer ? ( A pour annuler )");
         File monRepertoire=new File("Parties");// consultation du dossier "Parties"
         String[] f = monRepertoire.list();
         if (f.length==0){
             System.out.println("Aucune partie sauvegard�e");
         }else{
             for (int i=0;i<f.length;i++){
                 System.out.println(f[i].substring(0,f[i].length()-4));
             }
             do{
                 nomP=entree.next();
                 if (verifFichEx(nomP+".bin")){
                     valide=true;
                 }else if(nomP.equals("A")){
                	 return;
                 }else{
                     System.out.println("Entrer un nom de partie valide");
                 } 
             }while(!valide);
             OutilFichier.supprimerFichPartie(nomP);
         }
    }
}








