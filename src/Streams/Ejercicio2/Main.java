package Streams.Ejercicio2;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> numeros
                = Arrays.asList(1, 2, 3, 4, 5, 6);

        numeros.stream()
                .filter(n -> n % 2 == 1)
                .forEach(System.out::println);

        // Filtrar impares y luego mayores que 4
        numeros.stream()
                .filter(n -> n % 2 == 1)
                .filter(n -> n > 4)
                .forEach(System.out::println);
    }
}
