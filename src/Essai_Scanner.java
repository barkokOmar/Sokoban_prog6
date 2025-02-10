import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        int myInteger;
        boolean isValidInteger = true;

        my_scanner = new Scanner(System.in);
        System.out.println("Saisir un entier");

        while (isValidInteger) {
            try {
                myInteger = my_scanner.nextInt();
                isValidInteger = false;
                System.out.println("Vous avez saisi l'entier : " + myInteger);
            } catch (InputMismatchException e) {
                System.err.println("Il faut saisir un entier !");
                my_scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("Aucune ligne saisie !");
                break;
            } 
        }

        my_scanner.close();
    }
}

