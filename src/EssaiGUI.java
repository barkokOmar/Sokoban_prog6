import javax.swing.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.List; 
import java.util.ArrayList; 
import java.io.InputStream;
import Global.Configuration;

public class EssaiGUI {
	public static void main(String[] args) {
        InputStream inputStream;
		boolean isNextLevelAvailable = true;
        Configuration config = new Configuration();

        if (args.length < 1) {
            throw new RuntimeException("Il me faut un fichier niveau en argument!");
        }

        inputStream = config.ouvre(args[0]);

		Jeu jeu = new Jeu(new LecteurNiveaux(inputStream));
        isNextLevelAvailable = jeu.prochainNiveau();

        if (isNextLevelAvailable)
            SwingUtilities.invokeLater(new InterfaceGraphique(jeu));

	}
}




