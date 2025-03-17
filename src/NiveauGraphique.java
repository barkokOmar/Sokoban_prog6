import Global.Configuration;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class NiveauGraphique extends JComponent {
    Jeu jeu;
    Image wallImage;
    Image playerImage;
    Image boxImage;
    Image boxOnGoalImage;
    Image goalImage;
    Image floorImage;
    Point positionDeDeplacement;

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

	public void fixePositionDeDeplacement(int x, int y) {
		this.positionDeDeplacement = new Point(x, y);
	}

    protected void paintElement(Graphics2D drawable, int l, int c, int x, int y, int width, int height) {
        Niveau niveau = this.jeu.niveau();

        if (niveau.estVide(l, c)) {
            // Waits for image pixels to finish changing
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}

        } else if (niveau.aMur(l, c)) {
            while (!drawable.drawImage(wallImage, x, y, width, height, null)) {}

        } else if (niveau.aBut(l, c)) {
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}
            while(!drawable.drawImage(goalImage, x, y, width, height, null)) {}

        } else if (niveau.aPousseur(l, c)) { // On charge le floor puis le joueur dessus
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}
            while(!drawable.drawImage(playerImage, x, y, width, height, null)) {}

        } else if (niveau.aCaisse(l, c)) {
            while(!drawable.drawImage(boxImage, x, y, width, height, null)) {}

        } else if (niveau.aCaisseSurBut(l, c)) {
            while(!drawable.drawImage(boxOnGoalImage, x, y, width, height, null)) {}

		} else if (niveau.aPousseurSurBut(l, c)) {
            while(!drawable.drawImage(floorImage, x, y, width, height, null)) {}
			while(!drawable.drawImage(goalImage, x, y, width, height, null)) {}
			while(!drawable.drawImage(playerImage, x, y, width, height, null)) {}

		} else {
            throw new RuntimeException("Element '"+niveau.getElement(l, c)+"' a dessiner non recconue");
        }
    }

    private void realiseDeplacementDansNiveau(Niveau niveau, int ImageWidth, int ImageHeight) {
        int x = this.positionDeDeplacement.x / ImageHeight;
        int y = this.positionDeDeplacement.y / ImageWidth;
        int xJoueur = niveau.positionJoueur().x;
        int yJoueur = niveau.positionJoueur().y;
        int dx = x - xJoueur;
        int dy = y - yJoueur;
        int xCaisse = x + dx;
        int yCaisse = y + dy;

        if (!estCaseAdjacente(x, y, xJoueur, yJoueur)) {
            System.out.println("Le joueur ne peut se deplacer que d'une case a la fois !");
        }
        if (niveau.aCaisse(x, y) && (niveau.estVide(xCaisse, yCaisse) || niveau.aBut(xCaisse, yCaisse)) ) {
            niveau.deplaceCaisse(x, y, xCaisse, yCaisse);
        }
		if (niveau.estVide(x, y) || niveau.aBut(x, y)) {	// Si jamais y avait une caisse sur la nouvelle position du joueur, elle a déjà été déplacée (effet de bord de deplaceCaisse) et l'enciennne position de la caisse sera donc vide
            niveau.deplaceJoueur(x, y);
        } else {
            System.out.println("Mouvement invalide !");
        }
		this.positionDeDeplacement = null;
    }

    private boolean estCaseAdjacente(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) < 1 && y1 == y2) || (Math.abs(y1 - y2) == 1 && x1 == x2);
    }
    
	@Override
	public void paintComponent(Graphics g) {
        if (!allElementImagesCharged()) {
            throw new RuntimeException("Il faut charger toutes les images des elements de niveau avant d'afficher le niveau !");
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
		//Point center = new Point(width/2, height/2);

		// On efface tout
		drawable.clearRect(0, 0, width, height);
        
        // On calcule quel sera la taille du rectangle de chaque element du Niveau
        int widthImage = width / colonnesNiveau;
        int heightImage = height / lignesNiveau;

		// Un click est detectee
		if (null != positionDeDeplacement)
			realiseDeplacementDansNiveau(niveau, widthImage, heightImage);
        positionDeDeplacement = null;
    

        // On dessine la grille
        for (int i = 0; i < lignesNiveau; i++) {
            for (int j = 0; j < niveau.grille[i].length; j++) {
                paintElement(drawable, i, j, j*widthImage, i*heightImage, widthImage, heightImage);
            }
        }
        
	}
}
