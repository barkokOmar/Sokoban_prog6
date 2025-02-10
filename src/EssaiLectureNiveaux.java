import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List; 
import java.util.ArrayList; 

class Main {
    public static void main(String [] args) {
        InputStream inputStream;
        OutputStream outputStream = System.out;

        if (args.length < 1) {
            throw new RuntimeException("Il faut donner un fichier avec les niveaux !!!");
        }

        try {
            inputStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException  e) {
            throw new RuntimeException("Erreur durant ouverture du fichier "+ args[0]);
        }

        Niveau level;
        List<Niveau> listeDesNiveaux = new ArrayList<>();

        LecteurNiveaux lecteur = new LecteurNiveaux(inputStream);
        RedacteurNiveau redacteur = new RedacteurNiveau(outputStream);

        while (null != (level = lecteur.lisProchainNiveau())) {
            listeDesNiveaux.add(level);
            redacteur.ecrisNiveau(level);
        }

        redacteur.EndPrinting();
        lecteur.endReading();
    }
}


