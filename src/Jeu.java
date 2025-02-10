
public class Jeu {
	LecteurNiveaux levelReader;
	Niveau level;

	public Jeu() {

	}

	public Jeu(LecteurNiveaux levelReader) {
		this.levelReader = levelReader;
	}

	// Returns current level
	Niveau niveau() {
		return level;
	}

	// Advances to the next level, 
	boolean prochainNiveau() {
		this.level = levelReader.lisProchainNiveau();
		return null != this.level;
	}

}

