package ejercicio3_2;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido(1001, 250.50);

        procesarPedido(pedido, new AccionPedido() {
            @Override
            public void ejecutar(Pedido pedido) {
                System.out.println("--- INFORMACIÓN DEL PEDIDO ---");
                System.out.println("Número de pedido: " + pedido.getNumero());
                System.out.println("Importe: " + pedido.getImporte() + " €\n");
            }
        });

        procesarPedido(pedido, new AccionPedido() {
            @Override
            public void ejecutar(Pedido pedido) {
                System.out.println("--- ENVIANDO EMAIL ---");
                System.out.println("Enviando correo al cliente... Su pedido " + pedido.getNumero() + " por un importe de "  + pedido.getImporte() + " € ha sido procesado con éxito.\n");
            }
        });

        procesarPedido(pedido, new AccionPedido() {
            @Override
            public void ejecutar(Pedido pedido) {
                System.out.println("--- GENERANDO FACTURA ---");
                System.out.println("Factura generada correctamente para el pedido nº: " + pedido.getNumero() + "\n");
            }
        });

        procesarPedido(pedido, new AccionPedido() {
            @Override
            public void ejecutar(Pedido pedido) {
                System.out.println("--- REGISTRO EN FICHERO ---");
                System.out.println("Guardando datos del pedido " + pedido.getNumero()  + " (" + pedido.getImporte() + " €) en un archivo .txt \n");
            }
        });
    }

    public static void procesarPedido(Pedido pedido, AccionPedido accion) {
        accion.ejecutar(pedido);
    }
}