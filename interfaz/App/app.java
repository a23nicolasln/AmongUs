package App;

import java.util.Scanner;

import Scanner.lector;
import logica.juego.juego;
import logica.jugadores.impostor;
import menu.MenuConfiguracion;

public class app {

    /*
     * Método principal que inicia el menú de configuración
     */
    public static void main(String[] args) {
        
        MenuConfiguracion menu = new MenuConfiguracion();
        menu.ejecutar();
        
    }

    /*
     * Método que contiene la estructura del juego
     * Se encarga de iniciar los metodos que inician el juego, realizar las rondas, comprobar si hay un ganador y mostrar los resultados
     */
    public void estructuraJuego(){
        juego juego = new juego();
        Scanner sc = lector.getInstance().getScanner();

        juego.iniciarJuego();
        while (juego.comprobarGanador() == null) {
            System.out.println(juego.ronda());
            System.out.println(juego.asesinatos());
            System.out.println(juego.aciertoImpostor(sc.nextLine()));
        }
        System.out.println(juego.comprobarGanador());
        System.out.println();
        for (impostor i : juego.getimpostores()) {
            System.out.println(i.asesinado());
        }
        System.out.println("Pulse enter para volver al menú principal");
        sc.nextLine();
        
    }
   
}
