package Anonimas.anonimas_4;

public class Main {
    public static void main(String[] args) {
        AlumnoRepository repositorio = new AlumnoRepository();

        repositorio.guardar(new Alumno("11111111A", "Ana García", 8.5));
        repositorio.guardar(new Alumno("22222222B", "Luis Pérez", 4.0));
        repositorio.guardar(new Alumno("33333333C", "Marta López", 9.5));

        System.out.println("--- a) Mostrar datos del alumno ---");
        repositorio.buscarPorDni("11111111A", new AccionAlumno() {
            @Override
            public void ejecutar(Alumno alumno) {
                System.out.println(alumno.toString());
            }
        });

        System.out.println("\n--- b) Modificar nota a 5 ---");
        repositorio.buscarPorDni("22222222B", new AccionAlumno() {
            @Override
            public void ejecutar(Alumno alumno) {
                alumno.setNota(5.0);
                System.out.println("Nota modificada con éxito. Nuevos datos: " + alumno.toString());
            }
        });

        System.out.println("\n--- c) Comprobar calificación excelente ---");
        repositorio.buscarPorDni("33333333C", new AccionAlumno() {
            @Override
            public void ejecutar(Alumno alumno) {
                if (alumno.getNota() >= 9) {
                    System.out.println("¡" + alumno.getNombre() + " tiene una calificación excelente (" + alumno.getNota() + ")!");
                }
            }
        });
        
        System.out.println("\n--- Prueba de alumno no encontrado ---");
        repositorio.buscarPorDni("99999999Z", new AccionAlumno() {
            @Override
            public void ejecutar(Alumno alumno) {
                System.out.println("Esto no se mostrará.");
            }
        });
    }
}
