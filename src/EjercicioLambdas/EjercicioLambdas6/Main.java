package EjercicioLambdas.EjercicioLambdas6;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    ArrayList<String> nombres = new ArrayList<>();

    Predicate<String> tieneMasDeCuatroLetras = new Predicate<String>() {
        @Override
        public boolean test(String nombre) {
            return nombre.length() > 4;
        }
    };

    Consumer<String> mostrarMayusculas = new Consumer<String>() {
        @Override
        public void accept(String nombre) {
            System.out.println(nombre.toUpperCase());
        }
    };
    
    public static void main(String[] args) {
        Main main = new Main();
        main.nombres.add("Adam");
        main.nombres.add("Alain");
        main.nombres.add("Adam2");
        main.nombres.add("Begoña");
        main.nombres.add("Jaime0");

        System.out.println("Nombres con más de 4 letras:");
        for (String nombre : main.nombres) {
            if (main.tieneMasDeCuatroLetras.test(nombre)) {
                main.mostrarMayusculas.accept(nombre);
            }
        }
    }

}
