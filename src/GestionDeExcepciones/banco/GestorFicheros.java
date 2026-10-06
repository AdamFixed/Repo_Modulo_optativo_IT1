package banco;

import java.io.FileReader;
import java.io.IOException;

public class GestorFicheros {
	public void comprobarFichero() throws IOException {
		FileReader lector = new FileReader("cuentas.txt");
		System.out.println("Fichero abierto");
		lector.close();
	}
}