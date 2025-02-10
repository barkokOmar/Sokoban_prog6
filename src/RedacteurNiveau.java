import java.io.OutputStream;
import java.io.PrintStream;

class RedacteurNiveau {

    PrintStream my_printer;

    public RedacteurNiveau(OutputStream outputStream) {
        my_printer = new PrintStream(outputStream);
    }

    public void ecrisNiveau(Niveau level) {
        for (char [] line : level.grille) {
            my_printer.println(line);
        }
        my_printer.println(';'+level.nom);
        my_printer.println();
    }

    public void EndPrinting() {
        my_printer.close();
    }
}
