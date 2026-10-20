package Streams2.Ejercicio4;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        Path ruta = Paths.get("src/Streams2/Ejercicio4/reservas.txt");

        try (Stream<String> lineas = Files.lines(ruta)) {
            lineas.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Excepción: " + e);
        }
    }
}
