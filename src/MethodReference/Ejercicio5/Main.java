package MethodReference.Ejercicio5;

import java.util.function.Function;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        // Función original
        Function<String, Integer> longitud
                = String::length;

        // Función moderna
        Function<String, Integer> longitud2
                = texto -> texto.length();
    }

}
