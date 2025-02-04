import java.util.Scanner;


class MainSeqListe {
    public static void main(String [] args) {

        Scanner myScanner = new Scanner(System.in);
        SequenceListe liste = new SequenceListe();
        int element;

        while (true) {
            System.out.println("Entier a inserer en tete (-1 pour arreter): ");
            element = myScanner.nextInt();
            if (-1 == element)
                break;
            liste.insereTete(element);
        }
        
        while (true) {
            System.out.println("Entier a inserer en queue (-1 pour arreter): ");
            element = myScanner.nextInt();
            if (-1 == element)
                break;
            liste.insereQueue(element);
        } 

        String empty = liste.estVide() ? "est" : "n'est pas";
        System.out.println("la liste "+empty+" vide");

        System.out.println(liste);
        
        String userInput;
        myScanner.nextLine();   // consume leftovers (form nextInt) in buffer
        while (true) {
            System.out.println("Taper -1 pour extraire tete (autre touche pour arreter)");
            userInput = myScanner.nextLine().trim();
            if (!userInput.equals("-1"))
                break;
            element = liste.extraitTete();
            System.out.println("extrait: "+element);
        } 
        
        System.out.println(liste);

        myScanner.close();
    }
}	
