package interfaces_funcionales_2;

/**
 *
 * @author Jaime.Diaz
 */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PedidoMain {

    public static void main(String[] args) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1, "Ana", 1200, true));
        pedidos.add(new Pedido(2, "Carlos", 350, false));
        pedidos.add(new Pedido(3, "Marta", 800, true));
        pedidos.add(new Pedido(4, "Luis", 1500, false));

        GestorPedidos gestor = new GestorPedidos();

        System.out.println("--- 1. BUSCAR PEDIDOS ---");

        List<Pedido> pedidosCaros = gestor.buscar(pedidos, new Predicate<Pedido>() {
            @Override
            public boolean test(Pedido p) {
                return p.getImporte() > 1000;
            }
        });
        System.out.println("Superiores a 1000€: " + pedidosCaros);

        List<Pedido> pedidosNoPagados = gestor.buscar(pedidos, new Predicate<Pedido>() {
            @Override
            public boolean test(Pedido p) {
                return !p.isPagado();
            }
        });
        System.out.println("No pagados: " + pedidosNoPagados);

        System.out.println("\n--- 2. TRANSFORMAR PEDIDOS ---");

        List<String> formatoCSV = gestor.transformar(pedidos, new Function<Pedido, String>() {
            @Override
            public String apply(Pedido p) {
                return p.getNumero() + ";" + p.getCliente() + ";" + p.getImporte() + ";" + p.isPagado();
            }
        });
        System.out.println("Formato CSV:");
        for (String csv : formatoCSV) {
            System.out.println(csv);
        }

        List<String> formatoTexto = gestor.transformar(pedidos, new Function<Pedido, String>() {
            @Override
            public String apply(Pedido p) {
                return "Pedido " + p.getNumero() + " - Cliente: " + p.getCliente() + " - Importe: " + p.getImporte() + " €";
            }
        });
        System.out.println("\nFormato Texto:");
        for (String txt : formatoTexto) {
            System.out.println(txt);
        }

        System.out.println("\n--- 3. PROCESAR PEDIDOS ---");

        System.out.println("Mostrando Número y Cliente:");
        gestor.procesar(pedidos, new Consumer<Pedido>() {
            @Override
            public void accept(Pedido p) {
                System.out.println("Nº: " + p.getNumero() + " | Cliente: " + p.getCliente());
            }
        });

        System.out.println("\nAvisos de impago:");
        gestor.procesar(pedidos, new Consumer<Pedido>() {
            @Override
            public void accept(Pedido p) {
                if (!p.isPagado()) {
                    System.out.println("¡AVISO! El pedido " + p.getNumero() + " de " + p.getCliente() + " está PENDIENTE DE PAGO.");
                }
            }
        });
    }
}
