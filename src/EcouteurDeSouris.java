import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurDeSouris extends MouseAdapter {
	NiveauGraphique niveauGraphique;
	AireDeDessin aire;

    public EcouteurDeSouris() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public EcouteurDeSouris (NiveauGraphique g) {
		this.niveauGraphique = g;
	}

	public EcouteurDeSouris (AireDeDessin aire) {
		this.aire = aire;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (null != niveauGraphique) {
			System.out.println("Le bouton de la souris a été pressé en (" + e.getX() + ", " + e.getY() + ")");
			niveauGraphique.fixePosition(e.getX(), e.getY());
			niveauGraphique.repaint();
		} else if (null != aire) {
			System.out.println("Le bouton de la souris a été pressé en (" + e.getX() + ", " + e.getY() + ")");
			aire.position = new Point(e.getX(), e.getY());
			aire.repaint();
		}
	}
}
