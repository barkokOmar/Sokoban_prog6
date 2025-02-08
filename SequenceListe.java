
class IterateurSequenceListe implements Iterateur {
	public boolean aProchain() {
        return false;
    }
	public int prochain() {
        return 0;
    }
	public void supprime() {

    }
}

class SequenceListe {
    Cellule head;
    Cellule tail;
    
    public SequenceListe() {
        head = null;
        tail = null;
    }

    public SequenceListe(int element) {
        this.insereTete(element);
    }

    void insereTete(int element) {
        Cellule cell = new Cellule(element);
        cell.suivant = head;
        if (this.estVide())
            tail = cell;
        head = cell;
    }

    void insereQueue(int element) {
        Cellule cell = new Cellule(element);
        if (this.estVide())
            head = cell;
        else
            tail.suivant = cell;
        tail = cell;
    }

    int extraitTete() {
        if (this.estVide())
            throw new RuntimeException("Séquence vide");
        
        Cellule tmp = head;
        head = head.suivant;
        return tmp.valeur;
    }

    boolean estVide() {
        return (head == null) && (tail == null);
    }

    public String toString () {
        StringBuilder retVal = new StringBuilder();
        Cellule tmp = this.head;

        while (null != tmp) {
            retVal.append(tmp.valeur).append("->");
            tmp = tmp.suivant;
        }

        retVal.append("null");
        
        return retVal.toString();
    }
}
