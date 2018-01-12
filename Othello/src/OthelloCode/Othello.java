/* TestGrille*/

package OthelloCode;

import java.util.Scanner;

/** Main
 * @author antoine.bouysse
 *
 */


public class Othello {
	

    /** 
     * main
     */
	private static Scanner entree = new Scanner( System.in);

   
    
    /** 
     * Demande le mode de jeu à l'utilisateur
     * @return  mode
     * 
     */
    public static int demandeMode(){
        int m=0; // mode
        // Choix possible de mode
        System.out.println("\n\n\t1   :   Player vs Computer " 
                           + "\n\t2   :   Player1 vs Player2 ");
        do{            
            
            System.out.print("\nVeuillez choisir un mode de jeu (1 ou 2) ? : ");           
            m = entree.hasNextInt() ? entree.nextInt() : -1 ;
            entree.nextLine();
            
            if (m <1 || m > 2){
                System.out.println("Veuillez choisir un mode de jeu existant !!");
            }
            
        }while(m <1 || m > 2 );  
        
        return m;
        
    }
    /**
     * @param args
     */
    public static void main(String[] args){

		Partie p = null;
        
        int option,
            m;
       
        
        do{
        	 // Affichage menu
        	option=Partie.Menu();
        	if(option==1){
            
            // Demande Mode de jeu
            m=demandeMode();
            
            
            if(m==1){
                 p = new Partie(m);  // création Partie contre IA
                 p.initialiserPartieContreOrdi( );
                 p.mode1vIA();
                 System.out.println(option);

            } else if(m==2){
                 p =new Partie();    // création Partie 1v1
                 
                // lancement mode 1v1
                p.mode1v1();
            } else {
                System.out.println("Mode de jeu inexistant");
            }
            
	        // Charger une partie   
	        } else if(option == 2){
	            p=Sauvegarde.chargerPartie();
	            if (p==null){
	            	m=3;	            	
	            }else{
	            	p.reprendrePartie();
	            }
	        // Aide
	        }else if (option==3){
        	System.out.println("Classement des joeurs:\n");
        	Sauvegarde.afficherClassement();
        	System.out.println("Pour revenir au menu principal,appuyer sur entrée.");
        	entree.nextLine();
        } else {
            Partie.Aide();
        }
        }while(option!=1 && option!=2);
        
   
    	
		int col=Sauvegarde.enregistrerPseudo(p.j1.getPseudo());
		Sauvegarde.enregistrerMeilleurScore(col,p.j1.getScore());
	
		try {
			Sauvegarde.sauvegarderPartie(p);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}