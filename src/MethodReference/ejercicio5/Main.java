/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MethodReference.ejercicio5;

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
