
public class Niveau {

    String nom;
    char [][] grille;

    public Niveau() {
        // niveau vide
        this.grille = new char [0][];
        this.nom = "empty_level";
    }
	
    public Niveau(int nombreDeLignes) {
		grille = new char[nombreDeLignes][]; 
    }

    public Niveau(char [][] grille) {
        this.grille = grille;
        nom = "empty_name";
    }

    public Niveau(String [] level_lines) {
        grille = new char[level_lines.length][];
        int i = 0;
        for (String line : level_lines) {
            grille[i] = line.toCharArray();
            i++;
        }
        nom = "empty_name";
    }


    void fixeNom(String s) {
        nom = s;
    }         

    void videCase(int i, int j) {
        grille[i][j] = ' ';
    }    
    
    void ajouteMur(int i, int j) {
        grille[i][j] = '#';
    }   
    void ajoutePousseur(int i, int j) {
        grille[i][j] = '@';
    }
    void ajouteCaisse(int i, int j) {
        grille[i][j] = '$';
    }
    void ajouteBut(int i, int j) {
        grille[i][j] = '.';
    }        

    int lignes() {
		return grille.length;
    }

	// Renvoie la taille de la colonnes la plus longue
    int colonnes() {
		int retVal = 0;
		for (char[] line : grille) {
			if (line.length > retVal)
				retVal = line.length;
		}
		return retVal;
    }

    String nom() {
        return nom; 
    }                   

    boolean estVide(int l, int c) {
        return ' ' == grille[l][c]; 
    }  
    boolean aMur(int l, int c) {
        return '#' == grille[l][c]; 
    }
    boolean aBut(int l, int c) {
        return '.' == grille[l][c];
    }
    boolean aPousseur(int l, int c) {
		return '@' == grille[l][c];
    }
    boolean aCaisse(int l, int c) {
		return '$' == grille[l][c];
    }  

}

