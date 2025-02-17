package Global;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Configuration {

	public InputStream ouvre(String file) {
		InputStream inputStream;

		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException  e) {
			throw new RuntimeException("ERREUR: Impossible de trouver le fichier "+file);
		}

		return inputStream;
	}

}
