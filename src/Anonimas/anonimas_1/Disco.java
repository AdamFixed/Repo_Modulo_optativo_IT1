
package Anonimas.anonimas_1;

// Ya no implementa Comparable: el criterio de ordenación
// se proporciona desde fuera mediante clases anónimas.
public class Disco {

    private String nombre;
    private String grupo;

    public Disco(String nombre, String grupo) {
        super();
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    @Override
    public String toString() {
        return "Disco [nombre=" + nombre + ", grupo=" + grupo + "]";
    }
}
