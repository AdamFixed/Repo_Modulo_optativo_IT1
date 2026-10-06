package ejercicio4;

// Capa de servicio
public class Servicio {

    public void realizarCalculo() {
        System.out.println("Servicio: voy a realizar el cálculo");
        Calculadora calculadora = new Calculadora();
        calculadora.calcular();
        System.out.println("Servicio: cálculo terminado");
    }
}
