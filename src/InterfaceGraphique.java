import javax.swing.*;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
	Jeu jeu;

	public InterfaceGraphique(Jeu jeu) {
		this.jeu = jeu;
	}

	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame(jeu.niveau.nom());

		// Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique(this.jeu));

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
}
