package InterfacesFuncionales.interfaces_funcionales_1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        String texto = "Hola, Bego!";
        //Predict cooje un valor y devuelva un booleano
        Predicate<Integer> esMayorDeEdad = new Predicate<Integer>() {
            @Override
            public boolean test(Integer edad) {
                return edad >= 18;
            }
        };

        //Function coje un objeto y devuelva otro objeto
        Function<String, Integer> longitudTexto = new Function<String, Integer>() {
            @Override
            public Integer apply(String texto) {
                return texto.length();
            }
        };
        //Consumer coje un valor y no devuelve nada pero puede hacer funciones con el
        Consumer<String> mostrarTexto = new Consumer<String>() {
            @Override
            public void accept(String texto) {
                System.out.println(texto);
            }
        };
        //Suplier no esta pero genera valores y no hace falta que reciba
        
        System.out.println("Es 20 mayor de edad? = " + esMayorDeEdad.test(20) + "\n");
        
        System.out.println("La longitud de '" + texto + "' es: " + longitudTexto.apply(texto) + "\n");

        mostrarTexto.accept(texto);

    }
}
