package EjercicioLambdas.EjercicioLambdas4;

import EjercicioLambdas.EjercicioLambdas3.Operacion;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    static int operar(int a, int b, Operacion operacion) {
        return operacion.calcular(a, b);
    }

    public static void main(String[] args) {
        int numero1 = 10;
        int numero2 = 5;

        int suma = operar(numero1, numero2, (a, b) -> a + b);
        System.out.println("Suma: " + suma);

        int multiplicacion = operar(numero1, numero2, (a, b) -> a * b);
        System.out.println("Multiplicación: " + multiplicacion);
    }
}
