package EjercicioLambdas.EjercicioLambdas5;

import java.util.function.Predicate;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        // Crear un Predicate que determine si un número es mayor que 100.
        Predicate<Integer> mayor100 = num -> num > 100;

        boolean resultado = mayor100.test(56);
        boolean resultado2 = mayor100.test(128);

        System.out.println("¿Es 56 mayor que 100?: " + resultado);
        System.out.println("¿Es 128 mayor que 100?: " + resultado2);
    }
}
