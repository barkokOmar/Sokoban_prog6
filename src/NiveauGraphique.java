import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import Global.Configuration;

class NiveauGraphique extends JComponent {
    Jeu jeu;
    Image wallImage;
    Image playerImage;
    Image boxImage;
    Image boxOnGoalImage;
    Image goalImage;
    Image floorImage;

    public NiveauGraphique() {
        this.jeu = new Jeu();
    }

    public NiveauGraphique(Jeu jeu) {
        this.jeu = jeu;
    }
    
    // Charges the image which path is specified by imagePath and returns an image that can be used by Swing
	public Image chargeImage(String imagePath) {
        Image img = null;
        Configuration config = new Configuration();
        InputStream in = config.ouvre(imagePath);

		try {
			img = ImageIO.read(in);
		} catch (IOException e) {
			System.err.println("ERREUR : impossible de charger l'image <"+imagePath+">");
			System.exit(3);
		}
        return img;
	}

    public void chargeMure (String wallImagePath) {
        this.wallImage = chargeImage(wallImagePath);
    }
    public void chargePousseur (String playerImagePath) {
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
    public void chargeSol (String floorImagePath) {
        this.floorImage = chargeImage(floorImagePath);
    }
    public boolean allElementImagesCharged() {
        return (null != wallImage) &&
               (null != playerImage) &&
               (null != boxImage) &&
               (null != boxOnGoalImage) &&
               (null != goalImage) &&
               (null != floorImage);
    }

    protected void paintElement(Graphics2D drawable, int l, int c, int x, int y, int width, int height) {
        Niveau niveau = this.jeu.niveau();

        if (niveau.estVide(l, c)) {
            // Waits for image pixels to finish changing
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}

        } else if (niveau.aMur(l, c)) {
            while (!drawable.drawImage(wallImage, x, y, width, height, null)) {}

        } else if (niveau.aBut(l, c)) {
            while(!drawable.drawImage(goalImage, x, y, width, height, null)) {}

        } else if (niveau.aPousseur(l, c)) { // On charge le floor puis le joueur dessus
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}
            while(!drawable.drawImage(playerImage, x, y, width, height, null)) {}

        } else if (niveau.aCaisse(l, c)) {
            while(!drawable.drawImage(boxImage, x, y, width, height, null)) {}

        } else if (niveau.aCaisseSurBut(l, c)) {
            while(!drawable.drawImage(boxOnGoalImage, x, y, width, height, null)) {}

        } else {
            throw new RuntimeException("Element '"+niveau.getElement(l, c)+"' a dessiner non recconue");
        }
    }

	@Override
	public void paintComponent(Graphics g) {
        /*
        */
        if (!allElementImagesCharged()) {
            throw new RuntimeException("Il faut charger toutes les images des elements de niveau avant de l'afficher !!");
        }
        Niveau niveau = jeu.niveau();
        int lignesNiveau = niveau.lignes();
        int colonnesNiveau = niveau.colonnes();

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
        
        // On calcule quel sera la taille du rectangle de chaque element du Niveau
        int widthImage = width / colonnesNiveau;
        int heightImage = height / lignesNiveau;

        // On affiche (dessine) element par element
        for (int i = 0; i < lignesNiveau; i++) {
            for (int j = 0; j < niveau.grille[i].length; j++)
                paintElement(drawable, i, j, j*widthImage, i*heightImage, widthImage, heightImage);
        }
	}
}
