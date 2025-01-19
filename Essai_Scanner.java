import java.util.Scanner;
import java.util.NoSuchElementException;

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        String ligne = null;

        my_scanner = new Scanner(System.in);
        System.out.println("Saisissez une ligne");

        try {
            ligne = my_scanner.nextLine();
            System.out.println("Vous avez saisi la ligne : " + ligne);
        } catch (NoSuchElementException e) {
            System.err.println("Aucune ligne saisie");
        } 

        my_scanner.close();
    }
}

