/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioLambdas.EjercicioLambdas3;

/**
 *
 * @author 20ala
 */


//Parte Alain

public class Ejercicio3 {
    public static void main(String[] args) {
        int x = 10;
        int y = 4;
        
        Operacion suma = (a, b) -> a + b;
        Operacion resta = (a, b) -> a - b;
        Operacion multiplicacion = (a, b) -> a * b;

        System.out.println("Suma: " + suma.calcular(x, y));
        System.out.println("Resta: " + resta.calcular(x, y));
        System.out.println("Multiplicación: " + multiplicacion.calcular(x, y));
    }
}
