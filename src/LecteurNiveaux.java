import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Point;
import java.io.InputStream;


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
            System.out.println("Fin fichier niveaux atteinte.");
            endReading();
            return null;
        }

        ArrayList<String> levelParagraphe = new ArrayList<>();
        ArrayList<String> comments = new ArrayList<>();

        String line;
        while (my_scanner.hasNextLine()) {
            line = my_scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            if (IsComment(line))
                comments.add(line);
            else
                levelParagraphe.add(line);
        }

        // Decoupe le paragraphe en lignes independantes 
        String [] levelLines = new String [levelParagraphe.size()];
		levelLines = levelParagraphe.toArray(levelLines);
        String [] commentLines = new String [comments.size()];
		commentLines = comments.toArray(commentLines);

        // Exraire le dernier commentaire
        String lastComment;
        if (commentLines.length > 0)
            lastComment = commentLines[commentLines.length - 1].substring(1);
        else
            lastComment = "level_name_not_specified";

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

