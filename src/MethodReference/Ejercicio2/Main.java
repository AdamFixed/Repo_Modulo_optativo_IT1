package MethodReference.Ejercicio2;

import java.util.function.Function;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        // Función original
        Function<String, String> mayusculas
                = texto -> texto.toUpperCase();
        
        // Función moderna
        Function<String, String> mayusculas2 =
                String::toUpperCase;
    }

}
