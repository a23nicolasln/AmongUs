package logica.tareas;

import java.util.ArrayList;
import java.util.List;

/*
 * Clase que contiene el listado de tareas existentes
 * solo puede haber una instancia de esta clase
 */
public class listadoTareas {
    private List<tarea> tareasExistentes = new ArrayList<>();
    private static listadoTareas instanciaUnica = null;


    public List<tarea> getTareasExistentes() {
        return tareasExistentes;
    }

    public static listadoTareas getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new listadoTareas();
        }
        return instanciaUnica;
    }

    /*
     * Constructor de la clase listadoTareas, que añade las tareas existentes
     */
    private listadoTareas() {
        tareasExistentes.add(new tarea("Abrir ventanas para ventilar", "Aula 12"));
        tareasExistentes.add(new tarea("Reparar proyector", "Aula 12"));
        tareasExistentes.add(new tarea("Hacer fotocopias", "Conserjeria"));
        tareasExistentes.add(new tarea("Coger tizas", "Conserjeria"));
        tareasExistentes.add(new tarea("Desbloquear hacceso a internet", "Sala de profesores"));
        tareasExistentes.add(new tarea("Arreglar gotera", "Sala de profesores"));
        tareasExistentes.add(new tarea("Encender ordenador", "Sala de profesores"));
        tareasExistentes.add(new tarea("Actualizar software de repetidor de wifi", "Biblioteca"));
        tareasExistentes.add(new tarea("Cambiar cable de ethernet", "Biblioteca"));
        tareasExistentes.add(new tarea("Encender pantalla", "Taller"));
        tareasExistentes.add(new tarea("Ordenar material", "Taller"));
    }

    /*
     * Método que añade una tarea al listado de tareas existentes
     */
    public void addTarea(tarea tarea) {
        tareasExistentes.add(tarea);
    }

    /*
     * Método que elimina una tarea del listado de tareas existentes
     * devuelve un String indicando si la tarea ha sido eliminada o no
     */
    public String removeTarea(String tareaEliminar) {
        for (tarea tarea : tareasExistentes) {
            if (tarea.getDescripcion().equals(tareaEliminar)) {
                tareasExistentes.remove(tarea);
                return "Tarea eliminada";
            }
        }
        return "Tarea no existente";
    }

    public String VerTareas() {
        return tareasExistentes.toString();
    }


}
