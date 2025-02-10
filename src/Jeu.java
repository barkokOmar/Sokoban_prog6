
public class Jeu {
	LecteurNiveaux levelReader;
	Niveau niveau;

	public Jeu() {

	}

	public Jeu(LecteurNiveaux levelReader) {
		this.levelReader = levelReader;
	}

	// Returns current level
	Niveau niveau() {
		return niveau;
	}

	// Advances to the next level, return true upon successful reading
	boolean prochainNiveau() {
		this.niveau = levelReader.lisProchainNiveau();
		return null != this.niveau;
	}

}

