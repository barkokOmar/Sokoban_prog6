import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

class LecteurNiveaux {
    
    InputStream flux;
    Niveau niveauCourant;


    public LecteurNiveaux(InputStream flux) {
        this.flux = flux;
    }

    
    char [][] lisProchainNiveau () {
        return new char [][] {{'#'}, {'#'}};
    }
}

class Niveau {

    char [][] grille;
    String nom;
    
    // CONSTRUCTORS
    public Niveau(char [][] grille) {
        this.grille = grille;
    }


    // METHODS

    void fixeNom(String s) {
        return;
    }         

    void videCase(int i, int j) {
        return; 
    }    
    
    void ajouteMur(int i, int j) {
        return; 
    }   
    void ajoutePousseur(int i, int j) {
        return;    
    }
    void ajouteCaisse(int i, int j) {
        return;
    }
    void ajouteBut(int i, int j) {
        return; 
    }        

    int lignes() {
        return 0; 
    }
    int colonnes() {
        return 0; 
    }

    String nom() {
        return "Niveau.nom pas encore implémentée"; 
    }                   

    boolean estVide(int l, int c) {
        return true; 
    }  

    boolean aMur(int l, int c) {
        return true; 
    }
    boolean aBut(int l, int c) {
        return true;
    }
    boolean aPousseur(int l, int c) {
        return true; 
    }
    boolean aCaisse(int l, int c) {
        return true; 
    }  

}

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        //String ligne = null;
        int myInteger;
        boolean isValidInteger = true;

        my_scanner = new Scanner(System.in);
        System.out.println("Saisissez un entier");

        while (isValidInteger) {
            try {
                myInteger = my_scanner.nextInt();
                isValidInteger = false;
                System.out.println("Vous avez saisi l'entier : " + myInteger);
            } catch (InputMismatchException e) {
                System.err.println("Il faut saisir un entier !");
                my_scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("Aucune ligne saisie !");
                break;
            } 
        }

        my_scanner.close();
        
        // test des fonctionnalités implémentées

        Niveau level = new Niveau();
        System.out.println("Affichage grille du niveau :");
        for (int i = 0; i < level.grille.length; i++) {
            for (int j = 0; j < level.grille[i].length; j++) {
                System.out.println(level.grille[i][j]+" ");
            }
        }


    }
}

