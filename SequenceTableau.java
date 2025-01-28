
class SequenceTableau {

    int capacity = 1;
    int [] elements;
    int size;
	int headIndex;
	int tailIndex;

    public SequenceTableau() {
        elements = new int[capacity];
    }
    
    public SequenceTableau(int capacity) {
        this.capacity = capacity;
        elements = new int[capacity];
    }

    public String toString () {
        StringBuilder retVal = new StringBuilder();
        int tempHead = headIndex;
        for (int i = 0; i < size; i++) {
            retVal.append(elements[tempHead]).append("->");
            tempHead = this.incrementRelativeIndex(tempHead);
        }
        retVal.append("null");
        return retVal.toString();
    }

    private void realloc(int new_capacity) {
        SequenceTableau new_seq = new SequenceTableau(new_capacity);

        while (0 < this.getSize()) {
            new_seq.insereQueue(this.extraitTete());
        }
        
        this.elements = new_seq.elements;
        this.capacity = new_capacity;
        this.size = new_seq.size;
        this.headIndex = new_seq.headIndex;
        this.tailIndex = new_seq.tailIndex;
    }



    public void insereTete(int element) {
        if (this.estRemplie()) {
			// on va grossir dynamiquement le buffer en doublant la taille
            int new_capacity = 2*(this.getCapacity());
            realloc(new_capacity);
        }

        //headIndex = this.getNextIndexForHeadInsert();
        headIndex = this.decrementRelativeIndex(headIndex);
        if (estVide()) {
            tailIndex = headIndex;
        }

        elements[headIndex] = element;
        size++;
         
    }

    public void insereQueue(int element) {
        if (this.estRemplie()) {
			// on va grossir dynamiquement le buffer en doublant la taille
            int new_capacity = 2*(this.getCapacity());
            realloc(new_capacity);
        }

        tailIndex = this.incrementRelativeIndex(tailIndex);
        if (estVide()) {
            headIndex = tailIndex;
        }

        elements[tailIndex] = element;
        size++;
    }

    public int extraitTete() {
        if (this.estVide())
            throw new RuntimeException("Séquence vide");
        int retVal = elements[headIndex];
        headIndex = this.incrementRelativeIndex(headIndex);
        size--;
        return retVal;
    }

    public boolean estVide() {
        return (0 == this.getSize());
    }

    public boolean estRemplie() {
        return this.getCapacity() == this.getSize();
    }

    /*
    private int getNextIndexForHeadInsert() {
        return ((headIndex-1 + this.getCapacity()) % this.getCapacity()); 
    }
    */

    private int incrementRelativeIndex(int index) {
        return ((index+1) % this.getCapacity());
    }

    private int decrementRelativeIndex(int index) {
        return ((headIndex-1 + this.getCapacity()) % this.getCapacity()); 
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }


}
