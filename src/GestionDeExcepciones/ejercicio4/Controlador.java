package ejercicio4;

// Capa del controlador
public class Controlador {

    public void recibirPeticion() {
        System.out.println("Controlador: recibo la petición");
        Servicio servicio = new Servicio();
        servicio.realizarCalculo();
        System.out.println("Controlador: petición procesada");
    }
}
