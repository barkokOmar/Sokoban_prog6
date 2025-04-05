import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;


// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
	boolean maximized;
	Jeu jeu;

	public InterfaceGraphique(Jeu jeu) {
		this.jeu = jeu;
	}

	public void toggleFullscreen(JFrame frame) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}

	public void run() {
		JFrame frame;

		// Creation d'une fenetre
		if (jeu.prochainNiveau())
			frame = new JFrame(jeu.niveau().nom());
		else
			frame = new JFrame("Empty Level");

		NiveauGraphique niveauGraphique = new NiveauGraphique(this.jeu);

		// Charge les images des elements
		niveauGraphique.chargeMure ("./res/Images/Mur.png");
		niveauGraphique.chargePousseur ("./res/Images/Pousseur.png");
		niveauGraphique.chargeCaisse ("./res/Images/Caisse.png");
		niveauGraphique.chargeCaisseSurBut ("./res/Images/Caisse_sur_but.png");
		niveauGraphique.chargeBut ("./res/Images/But.png");
		niveauGraphique.chargeSol ("./res/Images/Sol.png");

		// Ajout de notre composant de dessin dans la fenetre
		frame.add(niveauGraphique);
		
		// Ajout d'un mouse listener
		EcouteurDeSouris mouseListener = new EcouteurDeSouris(niveauGraphique);
		niveauGraphique.addMouseListener(mouseListener);

		// Ajout d'un key listener
		EcouteurDeClavier keyListener = new EcouteurDeClavier(niveauGraphique);
		niveauGraphique.addKeyListener(keyListener);

		// Assurez-vous que le composant est focusable pour recevoir les événements clavier
		niveauGraphique.setFocusable(true);
		niveauGraphique.requestFocusInWindow();

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On set l'icone de la fenetere
		frame.setIconImage(niveauGraphique.playerImage);

		// Set window size 
		frame.setSize(500, 300);

		/*
		// Plein Ecran
		toggleFullscreen(frame);
		*/

		// On fixe la taille et on demarre
		frame.setVisible(true);
	}
}
