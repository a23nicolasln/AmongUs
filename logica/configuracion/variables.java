package logica.configuracion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Random;

import logica.jugadores.alumno;
import logica.jugadores.impostor;
import logica.jugadores.jugador;

import java.util.Arrays;

/*
 * Clase que contiene las variables del juego, como la cantidad de tareas, jugadores e impostores.
 * Tambien contiene los metodos para modificar estas variables
 */
public class variables {
    private int cantidadTareas = 5;
    private int cantidadJugadores = 5;
    private int cantidadImpostores = 1;
    private Set<jugador> jugadores = new HashSet<>();
    private LinkedList<String> nombres = new LinkedList<>(Arrays.asList("@FFF", "@GGG", "@HHH", "@III", "@JJJ"));

    private static variables instance = null;
    private Random random = new Random();

    private variables() {

    } 

    public static variables getInstance() {
        if (instance == null) {
            instance = new variables();
        }
        return instance;
    }

    public Set<jugador> getJugadores() {
        return jugadores;
    }

    public int getCantidadTareas() {
        return cantidadTareas;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public int getCantidadImpostores() {
        return cantidadImpostores;
    }

    public String printNombres() {
        return nombres.toString();
    }

    public void setCantidadTareas(int cantidadTareas) {
        this.cantidadTareas = cantidadTareas;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public void setCantidadImpostores(int cantidadImpostores) {
        this.cantidadImpostores = cantidadImpostores;
    }

    public String infoJugadores() {
        return jugadores.toString();
    }

    /*
     * Método que crea los jugadores del juego, con un número de impostores y de alumnos
     * definidos por las variables cantidadImpostores y cantidadJugadores
     */
    public void creacionJugadores(){
        //reinicio de variables
        this.jugadores = new HashSet<>();
        nombres = new LinkedList<>(Arrays.asList("@FFF", "@GGG", "@HHH", "@III", "@JJJ"));

        for (int i = 0; i < cantidadImpostores; i++) {
            jugadores.add(new impostor(nombres.remove(random.nextInt(cantidadJugadores - i)), cantidadTareas));
        }

        for (int i = cantidadImpostores; i < cantidadJugadores; i++) {
            jugadores.add(new alumno(nombres.remove(random.nextInt(cantidadJugadores - i)), cantidadTareas));
        }

    }

    /*
     * Método que añade un jugador a la lista de jugadores
     * si el nombre no está en uso y tiene el formato correcto
     * El formato es el siguiente: @XXX donde X es una letra mayúscula
     * Devuelve un String con un mensaje de si se ha añadido correctamente o no
     */
    public String añadirJugador(String nombre) {
        if(nombres.contains(nombre)) {
            return "El nombre ya esta en uso";
        }
        if (!nombre.matches("^@[A-Z]{3}")) {
            return "Formato de nombre incorrecto";
        }
        nombres.add(nombre);
        cantidadJugadores++;
        return "Jugador añadido correctamente";
    }

    /*
     * Método que elimina un jugador de la lista de jugadores
     * si el jugador existe, en caso contrario devuelve un String
     * de error
     */
    public String eliminarJugador(String nombre) {
        if(!nombres.contains(nombre)) {
            return "El nombre no esta en uso";
        }
        nombres.remove(nombre);
        cantidadJugadores--;
        return "Jugador eliminado";
    }

}
