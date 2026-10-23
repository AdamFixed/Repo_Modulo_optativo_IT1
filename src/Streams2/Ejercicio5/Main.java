package Streams2.Ejercicio5;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Jaime.Diaz
 */
public class Main {

    public static void main(String[] args) {
        Path ruta = Paths.get("src/Streams2/Ejercicio5/reservas.txt");

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

            System.out.println("\nFacturación total del hotel: " + facturacionTotal(lineas.stream()) + "€");
            System.out.println("Facturación media del hotel: " + facturacionTotal(lineas.stream()) / lineas.stream().count() + "€");

            System.out.println("\n*** Reservas por tipo de habitación ***");
            Map<String, Long> reservasPorTipo = reservasHabitacion(lineas.stream());

            reservasPorTipo.forEach((tipoHabitacion, cantidad)
                    -> System.out.printf("- %s: %d reserva(s)%n", tipoHabitacion, cantidad)
            );

            System.out.println("\n*** Facturación por ciudad ***");
            Map<String, Double> facturacionCiudad = facturacionCiudad(lineas.stream());

            facturacionCiudad.forEach((ciudad, total)
                    -> System.out.printf("- %s: %.2f€%n", ciudad, total)
            );

            System.out.println("\n*** Reserva con el importe más alto ***");
            Optional<String[]> reservaMaxima = mayorReserva(lineas.stream());

            String[] datos = reservaMaxima.get();
            double total = Double.parseDouble(datos[4].trim()) * Double.parseDouble(datos[5].trim());

            System.out.printf("- Cliente: %s | Ciudad: %s | Habitación: %s | Número de noches: %s | Total: %.2f€%n | Servicios contratados: %s\n",
                    datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), total, datos[6].trim());

            System.out.printf("\nPorcentaje de reservas superiores a 300€: %.2f%%%n", reservasMas300(lineas.stream()));

            System.out.println("\n*** Servicios contratados ***");
            List<String> servicios = serviciosUnicos(lineas.stream());
            servicios.forEach(s -> System.out.println("- " + s));

            System.out.println("\n*** Número de reservas por servicio ***");
            Map<String, Long> reservasPorServicio = reservasServicio(lineas.stream());
            reservasPorServicio.forEach((servicio, cantidad)
                    -> System.out.printf("- %s: %d reserva(s)%n", servicio, cantidad)
            );

            System.out.println("\n*** Reservas por importe (300€) ***");
            Map<Boolean, List<String>> gruposImporte = separarImporte300(lineas.stream());

            System.out.println("- Superan los 300€:");
            gruposImporte.get(true).forEach(linea -> {
                String nombre = linea.split(";")[1].trim();
                String ciudad = linea.split(";")[2].trim();
                String habitacion = linea.split(";")[3].trim();
                System.out.println("    - " + nombre + " - " + ciudad + " - " + habitacion);
            });

            System.out.println("- No superan los 300€:");
            gruposImporte.get(false).forEach(linea -> {
                String nombre = linea.split(";")[1].trim();
                String ciudad = linea.split(";")[2].trim();
                String habitacion = linea.split(";")[3].trim();
                System.out.println("    - " + nombre + " - " + ciudad + " - " + habitacion);
            });

            System.out.println("\n*** TOP 3 reservas más caras ***");
            top3Reservas(lineas.stream());

            System.out.println("\n*** Demostración de peek() (Filtro: Bilbao) ***");
            reservasPeek(lineas.stream());

            System.out.println("\n*** Archivo de reservas caras ***");
            reservasCaras(lineas.stream());
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

    public static Map<String, Long> reservasHabitacion(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 4)
                .collect(Collectors.groupingBy(
                        reserva -> reserva[3].trim(),
                        Collectors.counting()
                ));
    }

    public static Map<String, Double> facturacionCiudad(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 5)
                .collect(Collectors.groupingBy(
                        reserva -> reserva[2].trim(),
                        Collectors.summingDouble(reserva -> {
                            double noches = Double.parseDouble(reserva[4].trim());
                            double precioNoches = Double.parseDouble(reserva[5].trim());
                            return noches * precioNoches;
                        })
                ));
    }

    public static Optional<String[]> mayorReserva(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 5)
                .max(Comparator.comparingDouble(reserva -> {
                    double noches = Double.parseDouble(reserva[4].trim());
                    double precioNoches = Double.parseDouble(reserva[5].trim());
                    return noches * precioNoches;
                }));
    }

    public static double reservasMas300(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 5)
                .mapToDouble(reserva -> {
                    double noches = Double.parseDouble(reserva[4].trim());
                    double precioNoches = Double.parseDouble(reserva[5].trim());
                    return (noches * precioNoches) > 300 ? 100.0 : 0.0;
                })
                .average()
                .orElse(0.0);
    }

    public static List<String> serviciosUnicos(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 6)
                .map(reserva -> reserva[6])
                .flatMap(servicios -> java.util.Arrays.stream(servicios.split(",")))
                .map(String::trim)
                .filter(servicio -> !servicio.equalsIgnoreCase("ninguno") && !servicio.equalsIgnoreCase("servicio"))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Map<String, Long> reservasServicio(Stream<String> lineas) {
        return lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 6)
                .map(reserva -> reserva[6])
                .flatMap(servicios -> java.util.Arrays.stream(servicios.split(",")))
                .map(String::trim)
                .filter(servicio -> !servicio.equalsIgnoreCase("ninguno") && !servicio.equalsIgnoreCase("servicio"))
                .collect(Collectors.groupingBy(
                        servicio -> servicio,
                        Collectors.counting()
                ));
    }

    public static Map<Boolean, List<String>> separarImporte300(Stream<String> lineas) {
        return lineas
                .filter(l -> l.split(";").length > 5)
                .collect(Collectors.partitioningBy(linea -> {
                    String[] reserva = linea.split(";");
                    double noches = Double.parseDouble(reserva[4].trim());
                    double precio = Double.parseDouble(reserva[5].trim());
                    return (noches * precio) > 300;
                }));
    }

    public static void top3Reservas(Stream<String> lineas) {
        lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 5)
                .sorted(Comparator.comparingDouble((String[] reserva) -> {
                    double noches = Double.parseDouble(reserva[4].trim());
                    double precio = Double.parseDouble(reserva[5].trim());
                    return noches * precio;
                }).reversed())
                .limit(3)
                .forEach(reserva -> {
                    double total = Double.parseDouble(reserva[4].trim()) * Double.parseDouble(reserva[5].trim());
                    System.out.printf("- Cliente: %s | Importe: %.2f€%n", reserva[1].trim(), total);
                });
    }

    public static void reservasPeek(Stream<String> lineas) {
        lineas
                .map(l -> l.split(";"))
                .filter(reserva -> reserva.length > 2)
                .peek(reserva -> System.out.println("Entra en el filtro: " + reserva[1].trim()))
                .filter(reserva -> reserva[2].trim().equalsIgnoreCase("Bilbao"))
                .peek(reserva -> System.out.println("Después del filtro: " + reserva[1].trim()))
                .collect(Collectors.toList());
    }

    public static void reservasCaras(Stream<String> lineas) {
        Path rutaSalida = Paths.get("src/Streams2/Ejercicio5/reservas_caras.txt");

        try (java.io.BufferedWriter bw = Files.newBufferedWriter(rutaSalida)) {
            lineas
                    .filter(l -> l.split(";").length > 5)
                    .filter(l -> {
                        String[] reserva = l.split(";");
                        try {
                            double noches = Double.parseDouble(reserva[4].trim());
                            double precio = Double.parseDouble(reserva[5].trim());
                            return (noches * precio) > 300;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    })
                    .forEach(lineaFiltrada -> {
                        try {
                            bw.write(lineaFiltrada);
                            bw.newLine();
                        } catch (IOException e) {
                            System.out.println("Error al escribir línea: " + e.getMessage());
                        }
                    });

            System.out.println("Fichero reservas_caras.txt generado con éxito.");
        } catch (IOException e) {
            System.out.println("Excepción al crear el fichero: " + e.getMessage());
        }
    }
}
