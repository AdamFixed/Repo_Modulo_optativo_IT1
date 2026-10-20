package main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import clases.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Producto p1 = new Producto("Leche", 1.20);
        Producto p2 = new Producto("Pan", 0.90);
        Producto p3 = new Producto("Huevos", 2.50);
        Producto p4 = new Producto("Arroz", 1.80);
        Producto p5 = new Producto("Leche", 1.20);
        Producto p6 = new Producto("Café", 3.50);

        Cliente c1 = new Cliente("Ana", Arrays.asList(p1, p2));
        Cliente c2 = new Cliente("Luis", Arrays.asList(p3, p4, p5));
        Cliente c3 = new Cliente("Marta", Arrays.asList(p6));
        
        
        List<Cliente> clientes = Arrays.asList(c1, c2, c3);
        
        List<Producto> todosLosProductos = clientes.stream()
                .flatMap(cliente -> cliente.getProductos().stream())
                .collect(Collectors.toList());
        
        System.out.println("--- 2. Nombres de todos los productos ---");
        clientes.stream()
                .flatMap(cliente -> cliente.getProductos().stream())
                .map(Producto::getNombre)
                .forEach(System.out::println);
        
        System.out.println("\n--- 3. Productos con precio superior a 2 € ---");
        clientes.stream()
                .flatMap(cliente -> cliente.getProductos().stream())
                .filter(producto -> producto.getPrecio() > 2.0)
                .forEach(System.out::println);
        
        double importeTotal = clientes.stream()
                .flatMap(cliente -> cliente.getProductos().stream())
                .mapToDouble(Producto::getPrecio)
                .sum();
        
        System.out.println("\n--- 4. Importe total ---");
        System.out.println("Importe total de todas las compras: " + importeTotal + " €");
	}

}
