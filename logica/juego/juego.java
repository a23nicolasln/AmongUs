package logica.juego;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import logica.configuracion.variables;
import logica.jugadores.impostor;
import logica.jugadores.jugador;
import logica.jugadores.alumno;
import logica.tareas.tarea;

/*
 * Clase que representa el juego en sí, con sus jugadores, tareas y métodos para
 * realizar las rondas, asesinatos, acusaciones y comprobar el ganador
 */
public class juego {

    variables v = variables.getInstance();;
    Set<jugador> jugadores = new HashSet<>();
    Map<jugador, tarea> tareasRealizadas = new HashMap<>();
    public LinkedList<impostor> impostores = new LinkedList<>();

    /*
     * Método que inicia el juego, creando los jugadores y asignando los impostores
     */
    public void iniciarJuego(){
        v.creacionJugadores();
        this.jugadores = v.getJugadores();

        for (jugador j : jugadores) {
            if (j.getClass().getSimpleName().equals("impostor")) {
                impostores.add((impostor) j);
            }

        }
    }

    /*
     * Método que realiza una ronda del juego, en la que cada jugador realiza una tarea
     * y se guarda en un mapa las tareas realizadas por cada jugador
     * se devuelve un String con las tareas realizadas
     */
    public String ronda() {
        String texto = "";

        for (jugador j : jugadores) {
            if (j.getVivo() == true) {
                this.tareasRealizadas.put(j, j.verTarea());
                texto += j.getName() + " Realiza la tarea: " + j.realizarTarea().toString() + "\n";
            }

        }
        return texto;
    }

    /*
     * Método en el que se comprueba si un impostor puede
     * asesinar a un alumno, las condicions son que el impostor
     * esté vivo, el alumno esté vivo y que ambos estén en la misma sala
     * en caso de cumplirse hay una probabilidad del 50% de asesinar al alumno 
     * se devuelve un String con el asesinato o la ausencia de este
     */
    public String asesinatos() {
        String texto = "";
        for (impostor i : impostores) {
            if (i.getVivo() == true) {
                int asesinato = 1;
                for (jugador j : jugadores) {
                    if (j.getClass().getSimpleName().equals("alumno") && tareasRealizadas.get(j).getSala() == tareasRealizadas.get(i).getSala() && j.getVivo() == true){
                        Random r = new Random();
                        if (r.nextInt(1, 3) != 2 && asesinato == 1) {
                            i.asesina((alumno) j);
                            texto += j.getName() + " ha sido asesinado\n";
                            asesinato--;
                        }
    
                    }
                }
            }
        }
        if (texto == "") {
            return "No ha habido asesinatos";
        }
        return texto;
    }

    /*
     * Método en el que se acusa a un jugador y se le elimina
     * devuelviendo un String informandonos de si era un impostor o no
     */
    public String aciertoImpostor(String acierto) {
        String texto = "";
        if (acierto == null) {
            return "No se ha acusado a nadie";
        }

        for (jugador j : jugadores) {
            if (j.getName().equals(acierto)) {
                j.setVivo(false);
                if (j.getClass().getSimpleName().equals("impostor")) {
                    texto += j.getName() + " era un impostor\n";
                } else {
                    texto += j.getName() + " no era un impostor\n";
                }
            }
        }
        return texto;
    }

    /*
     * Método que comprueba si hay un ganador y devuelve un String
     * con el resultado
     */
    public String comprobarGanador() {
        int impostoresVivos = 0;
        int alumnosVivos = 0;
        int tareasRestantes = 0;
        for (jugador j : jugadores) {
            if (j.getVivo() == true) {
                if (j.getClass().getSimpleName().equals("impostor")) {
                    impostoresVivos++;
                } else {
                    alumnosVivos++;
                    tareasRestantes += j.getTareas().size();
                }
            }
        }
        if (tareasRestantes == 0) {
            return "Los alumnos han terminado todas las tareas\nLos alumnos han ganado";
        } else if (impostoresVivos == 0) {
            return "Los impostores han sido eliminados\nLos alumnos han ganado";
        } else if (impostoresVivos >= alumnosVivos) {
            return "Los impostores superan en número a los alumnos\nLos impostores han ganado";
        } else {
            return null;
        }

    }


    public LinkedList<impostor> getimpostores() {
        return impostores;
    }
}
