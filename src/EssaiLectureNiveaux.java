import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List; 
import java.util.ArrayList; 
import Global.Configuration;

class EssaiLectureNiveaux {
    public static void main(String [] args) {
        InputStream inputStream;
        OutputStream outputStream = System.out;
        Configuration config = new Configuration();

        if (args.length < 1) {
            throw new RuntimeException("Il faut donner en argument un fichier niveaux !");
        }

        inputStream = config.ouvre(args[0]);

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



