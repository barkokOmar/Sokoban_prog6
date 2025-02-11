
public class Jeu {

	LecteurNiveaux lecteur;
	Niveau niveauCourant;

	public Jeu() {

	}

	public Jeu(LecteurNiveaux levelReader) {
		this.lecteur = levelReader;
	}

	// Returns current level
	Niveau niveau() {
		return niveauCourant;
	}

	// Advances to the next level, return true upon successful reading
	boolean prochainNiveau() {
		this.niveauCourant = lecteur.lisProchainNiveau();
		return null != this.niveauCourant;
	}

}

