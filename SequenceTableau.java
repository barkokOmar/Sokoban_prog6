
class SequenceTableau {

    int TAILLE_MAX;
    int [] data;
    int length = 0;

    public SequenceTableau() {
        TAILLE_MAX = 100;
        data = new int[TAILLE_MAX];
    }

    public SequenceTableau(int TAILLE_MAX) {
        this.TAILLE_MAX = TAILLE_MAX;
        data = new int[TAILLE_MAX];
    }

    void insereTete(int element) {
        if (this.estRemplie()) {
            throw new RuntimeException("Séquence remplie !");
        }
        // on decale a droite tout les elements
        this.decaleADroite();
        data[0] = element;
        length++;
    }

    void insereQueue(int element) {
        if (this.estRemplie()) {
            throw new RuntimeException("Séquence remplie !");
        }
        data[length] = element;
        length++;
    }

    int extraitTete() {
        if (this.estVide())
            throw new RuntimeException("Séquence vide");
        int retVal = data[0];
        this.decaleAGauche();
        length--;
        return retVal;
    }

    boolean estVide() {
        return (0 == length);
    }

    boolean estRemplie() {
        return TAILLE_MAX == length;
    }

    void decaleADroite() {
        for (int i = length - 1; i >= 0; i--) {
            data[i+1] = data[i];
        }
    }

    void decaleAGauche() {
        for (int i = 0; i < length; i++) {
            data[i] = data[i+1];
        }
    }

    public String toString () {
        StringBuilder retVal = new StringBuilder();
        for (int i = 0; i < length; i++) {
            retVal.append(data[i]).append("->");
        }
        retVal.append("null");
        return retVal.toString();
    }

}