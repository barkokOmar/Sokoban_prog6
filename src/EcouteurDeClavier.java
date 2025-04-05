import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Point;

public class EcouteurDeClavier extends KeyAdapter {
    private NiveauGraphique niveauGraphique;

    public EcouteurDeClavier(NiveauGraphique niveauGraphique) {
        this.niveauGraphique = niveauGraphique;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (null == niveauGraphique) {
            return;
        }
        System.out.println("La touche " + KeyEvent.getKeyText(e.getKeyCode()) + " a été pressée");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                niveauGraphique.directionDeDeplacement = new Point(-1, 0);
                break;
            case KeyEvent.VK_DOWN:
                niveauGraphique.directionDeDeplacement = new Point(1, 0);
                break;
            case KeyEvent.VK_LEFT:
                niveauGraphique.directionDeDeplacement = new Point(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                niveauGraphique.directionDeDeplacement = new Point(0, 1);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_Q:
                System.out.println("Je quitte le jeu");
                System.exit(0);
                break;
            default:
                break;
        }
        niveauGraphique.repaint();
    }
}