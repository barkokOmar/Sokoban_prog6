import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class NiveauGraphique extends JComponent {
	int counter;

    Niveau level;

    Image wallImage;
    Image playerImage;
    Image boxImage;
    Image boxOnGoalImage;
    Image goalImage;
    Image groundImage;

    public NiveauGraphique() {
        this.level = new Niveau();
    }

    public NiveauGraphique(Niveau level) {
        this.level = level;
    }
    
    // Charges the image which path is specified by imagePath and returns an image that can be used by Swing
	public Image chargeImage(String imagePath) {
        Image img = null;
		try {
			InputStream in = new FileInputStream(imagePath);
			img = ImageIO.read(in);
		} catch (FileNotFoundException e) {
			System.err.println("ERREUR : impossible de trouver le fichier image <"+imagePath+">");
			System.exit(2);
		} catch (IOException e) {
			System.err.println("ERREUR : impossible de charger l'image <"+imagePath+">");
			System.exit(3);
		}
		counter = 1;
        return img;
	}

    public void chargeMure (String wallImagePath) {
        this.wallImage = chargeImage(wallImagePath);
    }
    public void chargeJoueur (String playerImagePath) {
        this.playerImage = chargeImage(playerImagePath);
    }
    public void chargeCaisse (String boxImagePath) {
        this.boxImage = chargeImage(boxImagePath);
    }
    public void chargeCaisseSurBut (String boxOnGoalImagePath) {
        this.boxOnGoalImage = chargeImage(boxOnGoalImagePath);
    }
    public void chargeBut (String goalImagePath) {
        this.goalImage = chargeImage(goalImagePath);
    }
    public void chargeSol (String groundImagePath) {
        this.groundImage = chargeImage(groundImagePath);
    }

	@Override
	public void paintComponent(Graphics g) {
        /*
		System.out.println("Entree dans paintComponent : " + counter++);

		// Graphics 2D est le vrai type de l'objet passé en paramètre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		Graphics2D drawable = (Graphics2D) g;

		// On reccupere quelques infos provenant de la partie JComponent
		int width = getSize().width;
		int height = getSize().height;

		// On calcule le centre de la zone et un rayon
		Point center = new Point(width/2, height/2);

		// On efface tout
		drawable.clearRect(0, 0, width, height);

		// On affiche une petite image au milieu
		drawable.drawImage(img, center.x-20, center.y-20, 40, 40, null);
        */
	}

}