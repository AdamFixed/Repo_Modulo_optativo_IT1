package ejercicio4;

public class Main {

    public static void main(String[] args) {
        try {
            Controlador controlador = new Controlador();
            controlador.recibirPeticion();
        } catch (RuntimeException e) {
            ManejadorExcepciones.manejar(e);
        }
        System.out.println("Aplicación terminada");
    }
}
