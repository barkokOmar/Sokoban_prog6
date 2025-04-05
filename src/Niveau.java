import java.awt.Point;

public class Niveau {

    String nom;
    char [][] grille;
    int lignes;
    int colonnes; // Taille de la colonne la plus longue du niveau
    Point positionJoueur;

    
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
        Point p;
        for (int i = 0; i < grille.length; i++) {
            p = ContainsPlayer(new String(grille[i]), i);
            if (null != p) {
                this.positionJoueur = new Point(p);
            }
        }
        setNbColonnes();
    }

    public Niveau(String [] level_lines) {
        this.grille = new char[level_lines.length][];
        int i = 0;
        Point p;
        for (String line : level_lines) {
            this.grille[i] = line.toCharArray();
            p = ContainsPlayer(line, i);
            if (null != p) {
                this.positionJoueur = new Point(p);
            }
            i++;
        }
        this.nom = "empty_name";
        this.lignes = grille.length;
        setNbColonnes();
    }

    private void setNbColonnes() {
		this.colonnes = 0;
		for (char[] line : grille) {
			if (line.length > this.colonnes)
				this.colonnes = line.length;
		}
    }


    public void deplaceCaisse(Point caseDeplacement, Point caseCaisse) {
        if (!estCaseValide(caseCaisse) || !estCaseValide(caseDeplacement)) {
            System.out.println("Indices d'acces a une case du niveau invalides !");
            return;
        }
        if (aCaisse(caseCaisse) || aMur(caseCaisse)) {
            System.out.println("Le deplacement de la caisse est impossible !");
            return;
        }
        if (!aCaisse(caseDeplacement) && !aCaisseSurBut(caseDeplacement)) {
            System.out.println("La case de deplacement n'est pas occupee par une caisse !");
            return;
        }
        if (aBut(caseCaisse)) {
            ajouteCaisseSurBut(caseCaisse.x, caseCaisse.y);
        } else {
            ajouteCaisse(caseCaisse.x, caseCaisse.y);
        }
        // La case de deplacement redevient vide (ou un but)
        if (aCaisseSurBut(caseDeplacement)) {
            ajouteBut(caseDeplacement.x, caseDeplacement.y);
        } else {
            videCase(caseDeplacement.x, caseDeplacement.y);
        }
    }

    public void deplaceJoueur(Point caseDeplacement) {
        if (!estCaseValide(caseDeplacement)) {
            System.out.println("Erreur deplaceJoueur: indices invalides !");
            return;
        }
        if (aMur(caseDeplacement)) {
            System.out.println("Erreur deplaceJoueur: Le joueur ne peut pas rentrer dans un mur!");
            return;
        }
        if (aCaisse(caseDeplacement) || aCaisseSurBut(caseDeplacement)) {
            System.out.println("Erreur deplaceJoueur: La case de deplacement est occupe par une caisse (faut la pousser...)!");
            return;
        }
        if (!estVide(caseDeplacement) && !aBut(caseDeplacement)) {
            System.out.println("Erreur deplaceJoueur: position de deplacement est occupee par un obstacle ?!");
            return;
        }

        if (aBut(caseDeplacement)) {
            ajoutePousseurSurBut(caseDeplacement.x, caseDeplacement.y);
        } else {
            ajoutePousseur(caseDeplacement.x, caseDeplacement.y);
        }
        // La case de deplacement redevient vide (ou un but)
        if (aPousseurSurBut(positionJoueur)) {
            ajouteBut(positionJoueur.x, positionJoueur.y);
        } else {
            videCase(positionJoueur.x, positionJoueur.y);
        }
        fixePositionJoueur(caseDeplacement);
    }
    public void deplaceJoueur(String direction) {
        if ("haut" == direction) {
            deplaceJoueur(new Point(positionJoueur.x-1, positionJoueur.y));
        } else if ("bas" == direction) {
            deplaceJoueur(new Point(positionJoueur.x+1, positionJoueur.y));
        } else if ("gauche" == direction) {
            deplaceJoueur(new Point(positionJoueur.x, positionJoueur.y-1));
        } else if ("droite" == direction) {
            deplaceJoueur(new Point(positionJoueur.x, positionJoueur.y+1));
        } else {
            System.out.println("Erreur deplaceJoueur: direction de deplacement invalide!");
        }
    }
            
    
    public void fixeNom(String s) {
        this.nom = s;
    }         
    public void fixePositionJoueur(int i, int j) {
        this.positionJoueur = new Point(i, j);
    }
    public void fixePositionJoueur(Point p) {
        this.positionJoueur = new Point(p);
    }

    private void putElement(int i, int j, char c) {
        Point caseDeplacement = new Point(i, j);
        if (!estCaseValide(caseDeplacement)) {
            throw new RuntimeException("Erreur putElement: indices invalides !");
        }
        this.grille[i][j] = c;
    }

    void videCase(int i, int j) {
        putElement(i, j, ' ');
    }    
    void ajouteMur(int i, int j) {
        putElement(i, j, '#');
    }   
    void ajoutePousseur(int i, int j) {
        putElement(i, j, '@');
    }
    void ajouteCaisse(int i, int j) {
        putElement(i, j,'$');
    }
    void ajouteBut(int i, int j) {
        putElement(i, j, '.');
    }
    void ajouteCaisseSurBut(int i, int j) {
        putElement(i, j, '*');
    }
    void ajoutePousseurSurBut(int i, int j) {
        putElement(i, j, '+');
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
    Point positionJoueur() {
        return this.positionJoueur;
    }                   

    boolean estCaseValide(Point caseGrille) {
        int l = caseGrille.x;
        int c = caseGrille.y;
        return (l>=0 && c>=0) && (l<grille.length && c<grille[l].length);
    }

    /* ContainsPlayer: checks if a line contains the player
     * @param line: a line of the level
     * @return: the position of the player in the line if it contains the player, null otherwise
     * @note: the player is represented by the character '@'
     */
    public Point ContainsPlayer(String line, int i) {
        int index = line.indexOf('@');
        if (-1 != index)
            return new Point(i, index);
        return null;
    }


    char getElement(Point caseGrille) {
        int l = caseGrille.x;
        int c = caseGrille.y;
        if (!estCaseValide(caseGrille)) {
            //throw new RuntimeException("Indices d'acces a une case du niveau invalides !");
            System.out.println("Indices d'acces a une case du niveau invalides !");
        }
        return grille[l][c];
    }
    boolean estVide(Point caseGrille) {
        return ' ' == getElement(caseGrille);
    }  
    boolean aMur(Point caseGrille) {
        return '#' == getElement(caseGrille);
    }
    boolean aBut(Point caseGrille) {
        return '.' == getElement(caseGrille);
    }
    boolean aPousseur(Point caseGrille) {
        return '@' == getElement(caseGrille);
    }
    boolean aCaisse(Point caseGrille) {
        return '$' == getElement(caseGrille);
    }  
    boolean aCaisseSurBut(Point caseGrille) {
        return '*' == getElement(caseGrille);
    }
    boolean aPousseurSurBut(Point caseGrille) {
        return '+' == getElement(caseGrille);
    }

}

