
public class Niveau {

    String nom;
    char [][] grille;
    int lignes;
    int colonnes; // Taille de la colonne la plus longue du niveau

    public Niveau() {
        // niveau vide
        this.grille = new char [0][];
        this.nom = "empty_level";
    }
	
    public Niveau(int nombreDeLignes) {
		this.grille = new char[nombreDeLignes][]; 
        this.lignes = nombreDeLignes;
    }

    public Niveau(char [][] grille) {
        this.grille = grille;
        this.nom = "empty_name";
        this.lignes = grille.length;
        setNbColonnes();
    }

    public Niveau(String [] level_lines) {
        this.grille = new char[level_lines.length][];
        int i = 0;
        for (String line : level_lines) {
            this.grille[i] = line.toCharArray();
            i++;
        }
        this.nom = "empty_name";
        this.lignes = grille.length;
        setNbColonnes();
    }

    protected void setNbColonnes() {
		this.colonnes = 0;
		for (char[] line : grille) {
			if (line.length > this.colonnes)
				this.colonnes = line.length;
		}
    }

    void fixeNom(String s) {
        this.nom = s;
    }         

    void videCase(int i, int j) {
        this.grille[i][j] = ' ';
    }    
    void ajouteMur(int i, int j) {
        this.grille[i][j] = '#';
    }   
    void ajoutePousseur(int i, int j) {
        this.grille[i][j] = '@';
    }
    void ajouteCaisse(int i, int j) {
        this.grille[i][j] = '$';
    }
    void ajouteBut(int i, int j) {
        this.grille[i][j] = '.';
    }        

    int lignes() {
		return this.lignes;
    }
    int colonnes() {
        return this.colonnes;
    }
    String nom() {
        return this.nom; 
    }                   

    char getElement(int l, int c) {
        if (l >= grille.length || c >= grille[l].length) {
            throw new RuntimeException("Indices d'acces a une case du niveau invalides !");
        }
        return grille[l][c];
    }
    boolean estVide(int l, int c) {
        return ' ' == getElement(l, c);
    }  
    boolean aMur(int l, int c) {
        return '#' == getElement(l, c);
    }
    boolean aBut(int l, int c) {
        return '.' == getElement(l, c);
    }
    boolean aPousseur(int l, int c) {
        return '@' == getElement(l, c);
    }
    boolean aCaisse(int l, int c) {
        return '$' == getElement(l, c);
    }  
    boolean aCaisseSurBut(int l, int c) {
        return '*' == getElement(l, c);
    }
    boolean aPousseurSurBut(int l, int c) {
        return '+' == getElement( l, c);
    }

}

