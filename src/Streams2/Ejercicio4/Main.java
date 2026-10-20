package Streams2.Ejercicio4;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        Path ruta = Paths.get("src/Streams2/Ejercicio4/reservas.txt");

        try (Stream<String> lineas = Files.lines(ruta)) {
            
            List<String> clientesFiltrados = lineas
                    .map(l -> l.split(";"))
                    .filter(datos -> {
                        if (datos.length > 5) {
                            try {
                                double valor4 = Double.parseDouble(datos[4].trim());
                                double valor5 = Double.parseDouble(datos[5].trim());
                                return (valor4 * valor5) > 300;
                            } catch (NumberFormatException e) {
                                return false;
                            }
                        }
                        return false;
                    })
                    
                    .filter(datos -> datos[2].trim().equalsIgnoreCase("Bilbao"))
                    .map(datos -> datos[1].trim())
                    .collect(Collectors.toList());

            System.out.println("Clientes filtrados: " + clientesFiltrados);
        } catch (IOException e) {
            System.out.println("Excepción: " + e);
        }
    }
}
