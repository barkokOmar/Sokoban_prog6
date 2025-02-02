import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;


class RedacteurNiveau {

    PrintStream my_printer;

    public RedacteurNiveau(OutputStream outputStream) {
        my_printer = new PrintStream(outputStream);
    }

    public void ecrisNiveau(Niveau level) {
        for (char [] line : level.grille) {
            my_printer.println(line);
            my_printer.println();
        }
        my_printer.print(';');
        my_printer.println(level.nom);
    }

    public void EndPrinting() {
        my_printer.close();
    }
}

class LecteurNiveaux {
    
    Scanner my_scanner;
    String line;

    public LecteurNiveaux() {
        my_scanner = new Scanner(System.in);
    }

    public LecteurNiveaux(InputStream inputStream) {
        my_scanner = new Scanner(inputStream);
    }
    
    public Niveau lisProchainNiveau() {
        if (!my_scanner.hasNextLine()) { // Fin du flux atteinte
            endReading();
            return null;
        }

        StringBuilder levelParagraphe = new StringBuilder();
        StringBuilder comments = new StringBuilder();
        String line;

        // Lecture d'un paragraphe (suite de lignes/text se terminant par une ligne vide) designe un niveau
        while (my_scanner.hasNextLine()) {
            line = my_scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            if (IsComment(line))
                comments.append(line);
            else
                levelParagraphe.append(line);
        }


        // Decoupe le paragraphe en lignes independantes 
        String [] levelLines = levelParagraphe.toString().split("\\R");
        String [] commentLines = comments.toString().split("\\R");

        // Exraire le dernier commentaire
        String lastComment;
        if (commentLines.length > 0)
            lastComment = commentLines[commentLines.length - 1];
        else
            lastComment = "level_name_not_specified";

        // Supprinme le ';'
        lastComment = lastComment.substring(1);

        Niveau level = new Niveau(levelLines);
        level.fixeNom(lastComment);

        return level;
    }
    
    public boolean EstLigneVide(String str) {
        return str.trim().isEmpty();
    }

    public boolean IsComment(String line) {
        return ';' == line.charAt(0);
    }

    public void endReading() {
        my_scanner.close();
    }
}

public class Niveau {

    String nom;
    char [][] grille;

    public Niveau() {
        // niveau vide
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
        return 0; 
    }
    int colonnes() {
        return 0; 
    }

    String nom() {
        return nom; 
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

