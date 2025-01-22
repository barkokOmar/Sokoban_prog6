
class SequenceTableau {

    int capacity;
    int[] elements;
    int size = 0;
	int headIndex = 0;
	int tailIndex = 0;
    boolean isFistInsert = true;

    public SequenceTableau() {
        capacity = 100;
        elements = new int[capacity];
    }
    public SequenceTableau(int capacity) {
        this.capacity = capacity;
        elements = new int[capacity];
    }


    void insereTete(int element) {
        if (this.estRemplie()) {
			// on va grossir dynamiquement le buffer en doublant la taille
        }

        System.out.println("Avant calcule : head="+headIndex+" tail="+tailIndex); // Debug

        headIndex = this.getNextIndexForHeadInsert();
        if (isFistInsert) {
            tailIndex = headIndex;
            isFistInsert = false;
        }

        System.out.println("Après calcule : head="+headIndex+" tail="+tailIndex); // Debug

        elements[headIndex] = element;
        size++;
         
    }

    void insereQueue(int element) {
        if (this.estRemplie()) {
			// on va grossir dynamiquement le buffer en doublant la taille
        }

        System.out.println("Avant calcule : head="+headIndex+" tail="+tailIndex); // Debug

        tailIndex = this.incrementRelativeIndex(tailIndex);
        if (isFistInsert) {
            headIndex = tailIndex;
            isFistInsert = false;
        }

        System.out.println("Après calcule : head="+headIndex+" tail="+tailIndex); // Debug

        elements[tailIndex] = element;
        size++;
    }

    int extraitTete() {
        if (this.estVide())
            throw new RuntimeException("Séquence vide");
        int retVal = elements[headIndex];
        headIndex = this.decrementRelativeIndex(headIndex);
        size--;
        return retVal;
    }

    boolean estVide() {
        return (0 == size);
    }

    boolean estRemplie() {
        return capacity == size;
    }

    int getNextIndexForHeadInsert() {
        return ((headIndex-1 + capacity) % capacity); 
    }

    int incrementRelativeIndex(int index) {
        return ((index+1) % capacity);
    }

    int decrementRelativeIndex(int index) {
        return ((index+1) % capacity);
    }

    public String toString () {
        StringBuilder retVal = new StringBuilder();
        int tempHead = headIndex;
        for (int i = 0; i < size; i++) {
            retVal.append(elements[tempHead]).append("->");
            tempHead = this.decrementRelativeIndex(tempHead);
        }
        retVal.append("null");
        return retVal.toString();
    }

}
