import javax.swing.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List; 
import java.util.ArrayList; 

public class EssaiGUI {
	public static void main(String[] args) {
        InputStream inputStream;
		boolean isNextLevelAvailable = true;

        if (args.length < 1) {
            throw new RuntimeException("Il me faut un fichier niveau en argument!");
        }

        try {
            inputStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException  e) {
            throw new RuntimeException("Erreur durant ouverture du fichier "+ args[0]);
        }

		Jeu jeu = new Jeu(new LecteurNiveaux(inputStream));
        isNextLevelAvailable = jeu.prochainNiveau();

        if (isNextLevelAvailable)
            SwingUtilities.invokeLater(new InterfaceGraphique(jeu));

	}
}




