package Streams2.Ejercicio4;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
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

        try {
            List<String> lineas = Files.readAllLines(ruta);

            System.out.println("*** Clientes con un importe de más de 300€ ***");

            List<String> clientesBilbao = lineas.stream()
                    .map(l -> l.split(";"))
                    .peek(reserva -> {
                        if (reserva.length > 5) {
                            try {
                                double noches = Double.parseDouble(reserva[4].trim());
                                double precioNoches = Double.parseDouble(reserva[5].trim());
                                if ((noches * precioNoches) > 300) {
                                    System.out.println(Arrays.toString(reserva));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Excepción: " + e);
                            }
                        }
                    })
                    .filter(datos -> datos.length > 2 && datos[2].trim().equalsIgnoreCase("Bilbao"))
                    .map(datos -> datos[1].trim())
                    .collect(Collectors.toList());

            List<String> clientesTresNoches = lineas.stream()
                    .map(l -> l.split(";"))
                    .filter(reserva -> {
                        if (reserva.length > 4) {
                            try {
                                return Double.parseDouble(reserva[4].trim()) >= 3;
                            } catch (NumberFormatException e) {
                                return false;
                            }
                        }
                        return false;
                    })
                    .map(reserva -> reserva[1].trim())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("\n*** Clientes de Bilbao ***");
            System.out.println(clientesBilbao);

            System.out.println("\n*** Clientes con 3 o más noches ***");
            System.out.println(clientesTresNoches);

            System.out.println("\n*** Facturación total del hotel ***");
            System.out.println(facturacionTotal(lineas.stream()) + "€");

            System.out.println("\n*** Facturación media del hotel ***");
            System.out.println(facturacionTotal(lineas.stream()) / lineas.stream().count() + "€");
        } catch (IOException e) {
            System.out.println("Excepción: " + e);
        }
    }

    public static Double facturacionTotal(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 5)
                .mapToDouble(reserva -> {
                    try {
                        double noches = Double.parseDouble(reserva[4].trim());
                        double precioNoches = Double.parseDouble(reserva[5].trim());
                        return noches * precioNoches;
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .sum();
    }
}
