package logica.tareas;

/*
 * Clase que representa una tarea, que tiene una descripción y una sala
 */
public class tarea {
    private String descripcion;
    private String sala;

    public String toString(){
        return descripcion + " en " + sala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSala() {
        return sala;
    }

    public tarea(String descripcion, String sala) {
        this.descripcion = descripcion;
        this.sala = sala;
    }
}