import Global.Configuration;
import java.io.InputStream;
import javax.swing.*;

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




