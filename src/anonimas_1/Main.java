package anonimas_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Disco> aDiscos = new ArrayList<>();
        aDiscos.add(new Disco("nombreAAAAAAAA", "grupoPrimero"));
        aDiscos.add(new Disco("nombreBB", "grupoSegundo"));
        aDiscos.add(new Disco("nombreC", "grupoTerceroLargo"));

        // Clase anónima: ordenar por la longitud del nombre
        Comparator<Disco> porNombre = new Comparator<Disco>() {
            @Override
            public int compare(Disco a, Disco b) {
                return a.getNombre().length() - b.getNombre().length();
            }
        };

        Collections.sort(aDiscos, porNombre);
        System.out.println("--- Ordenados por longitud del nombre ---");
        mostrar(aDiscos);

        // Clase anónima: ordenar por la longitud del grupo
        Comparator<Disco> porGrupo = new Comparator<Disco>() {
            @Override
            public int compare(Disco a, Disco b) {
                return a.getGrupo().length() - b.getGrupo().length();
            }
        };

        Collections.sort(aDiscos, porGrupo);
        System.out.println("\n--- Ordenados por longitud del grupo ---");
        mostrar(aDiscos);
    }

    private static void mostrar(ArrayList<Disco> aDiscos) {
        for (Disco disco : aDiscos) {
            System.out.println(disco);
        }
    }
}

