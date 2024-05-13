package logica.jugadores;

import java.util.LinkedList;

/*
 * Clase que representa al impostor, que es un tipo de jugador
 */
public class impostor extends jugador{

    LinkedList<alumno> asesinados = new LinkedList<>();


    /*
     * Constructor de la clase impostor, que recibe un nombre y una cantidad de tareas
     */
    public impostor(String nombre, int cantidadTareas){
        super(nombre, cantidadTareas);
    }


    /*
     * Método que asesina a un alumno, lo marca como muerto y lo añade a la lista de asesinados
     */
    public void asesina(alumno alumno){
        alumno.setVivo(false);
        asesinados.add(alumno);
    }

    /*
     * Método que devuelve un String con los alumnos asesinados por el impostor
     */
    public String asesinado(){
        String asesinados = "El impostor "+this.getName()+" ha asesinado a los siguientes alumnos: \n";
        for (alumno alumno : this.asesinados) {
            asesinados += alumno.getName() + "\n";
        }
        return asesinados;
    }
}
