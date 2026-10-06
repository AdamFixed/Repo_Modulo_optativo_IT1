package principal;

public class Ejercicio2 {

	public static void main(String[] args) {
		String nullString = null;
		
		System.out.println("Ejemplo para contemplar un string null, sacando longitud de string null");
		try
		{
			System.out.println(nullString.length());
		}
		catch(NullPointerException e)
		{
			System.err.println("El String es vacio no se puede sacar su longitud");
		}
		
	}

}
