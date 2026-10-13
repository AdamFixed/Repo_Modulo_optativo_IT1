package Streams.Ejercicio4;

import java.util.ArrayList;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Pepe");
        nombres.add("Lucas");
        nombres.add("María");
        nombres.add("Luis");
        nombres.add("Ernesto");
        nombres.add("Lucía");

        nombres.stream()
                .filter(n -> n.length() > 4)
                .forEach(System.out::println);
    }
}
