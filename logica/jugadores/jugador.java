package logica.jugadores;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import logica.tareas.listadoTareas;
import logica.tareas.tarea;

/*
 * Clase que representa a un jugador, que puede ser un alumno o un impostor
 * contiene sus tareas y si está vivo o muerto 
 */
public abstract class jugador {

    private boolean vivo = true;
    private String nombre;
    private Queue<tarea> tareas = new LinkedList<>();

    /*
     * Constructor de la clase jugador, que recibe un nombre y una cantidad de tareas
     * a realizar, y asigna tareas aleatorias al jugador de la lista de tareas
     */
    public jugador(String nombre, int cantidadTareas){
        this.nombre = nombre;
        Random random = new Random();
        listadoTareas listado = listadoTareas.getInstance();
        for (int i = 0; i < cantidadTareas; i++) {
            tareas.add(listado.getTareasExistentes().get(random.nextInt(listado.getTareasExistentes().size())));
        }
    }

    public String toString() {
        return nombre + ":\nEsta vivo: " + vivo + "\n" + "Tipo: " + getClass().getSimpleName() + "\n" + tareasToString()
                + "\n";

    }

    public String tareasToString() {
        String frase = "";
        for (tarea tarea : this.tareas) {
            frase += tarea.toString() + "\n";
        }

        return frase;
    }

    /*
     * Método que realiza una tarea, si el jugador está vivo, y la elimina de la cola
     * devuelve la tarea realizada o null si el jugador está muerto
     */
    public tarea realizarTarea() {
        if (vivo == true) {
            return tareas.poll();
        } else {
            return null;
        }
    }

    public tarea verTarea() {
        return tareas.peek();
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean getVivo() {
        return vivo;
    }

    public String getName() {
        return nombre;
    }

    public Queue<tarea> getTareas() {
        return tareas;
    }


}