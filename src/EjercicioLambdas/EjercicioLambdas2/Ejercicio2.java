/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioLambdas.EjercicioLambdas2;

/**
 *
 * @author 20ala
 */


//Parte Alain
@FunctionalInterface
interface OperacionSuma3 {
    int calcular(int a, int b, int c);
}

public class Ejercicio2 {
    public static void main(String[] args) {
        OperacionSuma3 suma = (a, b, c) -> a + b + c;

        System.out.println("Suma de 5 + 7 + 3 = " + suma.calcular(5, 7, 3));
    }
}