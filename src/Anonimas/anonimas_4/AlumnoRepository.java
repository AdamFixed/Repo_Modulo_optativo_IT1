package anonimas_4;

import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {
    private List<Alumno> alumnos;

    public AlumnoRepository() {
        this.alumnos = new ArrayList<>();
    }

    public void guardar(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void buscarPorDni(String dni, AccionAlumno accion) {
        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dni)) {
                accion.ejecutar(alumno);
                return;
            }
        }

        System.out.println("No se ha encontrado ningún alumno con el DNI: " + dni);
    }
}