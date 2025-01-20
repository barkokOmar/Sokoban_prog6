import java.util.Scanner;

class MainSeqTab {

    
    public static void main(String [] args) {

        Scanner myScanner = new Scanner(System.in);
        SequenceTableau seq = new SequenceTableau();
        int element;

        while (true) {
            System.out.println("Entier a inserer en tete (-1 pour arreter): ");
            element = myScanner.nextInt();
            if (-1 == element)
                break;
            seq.insereTete(element);
        }
        
        while (true) {
            System.out.println("Entier a inserer en queue (-1 pour arreter): ");
            element = myScanner.nextInt();
            if (-1 == element)
                break;
            seq.insereQueue(element);
        } 

        String empty = seq.estVide() ? "est" : "n'est pas";
        System.out.println("la sequence "+empty+" vide");

        System.out.println(seq);
        
        String userInput;
        myScanner.nextLine();   // consume leftovers (form nextInt) in buffer
        while (true) {
            System.out.println("Taper -1 pour extraire tete (autre touche pour arreter)");
            userInput = myScanner.nextLine().trim();
            if (!userInput.equals("-1"))
                break;
            element = seq.extraitTete();
            System.out.println("extrait: "+element);
        } 
        
        System.out.println(seq);

        myScanner.close();
        
    }
}