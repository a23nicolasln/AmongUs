package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import App.app;
import Scanner.lector;
import logica.configuracion.variables;
import logica.tareas.listadoTareas;
import logica.tareas.tarea;

/*
 * Clase abstracta que representa un componente de menú. Cada componente de menú
 * tiene un nombre y un padre. Los componentes de menú pueden ser menús o items
 */
abstract class ComponenteMenu {
    protected ComponenteMenu padre;
    protected String nombre;
    Scanner sc;

    protected ComponenteMenu(String nombre, Scanner sc) {
        padre = null;
        this.nombre = nombre;
        this.sc = lector.getInstance().getScanner();
    }

    abstract void ejecutar();
}

/*
 * Clase que representa un menú. Cada menú tiene un nombre y una lista de
 * opciones de menú.
 */
class Menu extends ComponenteMenu {
    List<ComponenteMenu> hijos = new ArrayList<>();// hay composición

    public Menu(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    void addMenu(ComponenteMenu cm) {
        this.hijos.add(cm);
        cm.padre = this;
    }

    /*
     * Método que ejecuta el menú. Muestra
     * las opciones de menú y permite al usuario seleccionar una opción.
     */
    @Override
    void ejecutar() {
        int numMenu = -1;
        while (numMenu == -1) {
            System.out.println();
            System.out.println("Menú " + this.nombre);
            System.out.println("--------------------");
            for (int i = 0; i < hijos.size(); i++) {
                System.out.println(i + ". " + hijos.get(i).nombre);
            }
            System.out.println(hijos.size() + ". Salir");
            System.out.println("Teclea un número de opción");
            String opcion = sc.nextLine();
            if (opcion.matches("\\d+")) {
                int numOpcion = Integer.parseInt(opcion);
                if (numOpcion >= 0 && numOpcion <= hijos.size()) {
                    numMenu = numOpcion;
                }
            }
        }
        if (numMenu == this.hijos.size()) {
            if (this.padre == null) {
                System.out.println("Cerrando aplicación");
                System.exit(0);
            } else {
                this.padre.ejecutar();
            }
        } else {
            this.hijos.get(numMenu).ejecutar();
        }
    }
}

/*
 * Clase que representa un item de menú. Cada item de menú tiene un nombre y una
 * acción asociada. La acción asociada es modificar diferentes opcciones del
 * juego o iniciarlo.
 */
class MenuItem extends ComponenteMenu {

    /*
     * Constructor de la clase MenuItem
     */
    public MenuItem(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    /*
     * Método que ejecuta la acción asociada al item de menú
     */
    @Override
    void ejecutar() {

    }
}

class AgregarTarea extends MenuItem {

    public AgregarTarea(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        listadoTareas lt = listadoTareas.getInstance();
        System.out.println("Ingrese la descripcion de la tarea");
        String nombreTarea = sc.nextLine();
        System.out.println("Ingrese la sala de la tarea");
        String sala = sc.nextLine();
        lt.addTarea(new tarea(nombreTarea, sala));
        this.padre.ejecutar();
    }

}

class EliminarTarea extends MenuItem {

    public EliminarTarea(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        listadoTareas lt = listadoTareas.getInstance();
        System.out.println("Tareas existentes:");
        System.out.println(lt.VerTareas());
        System.out.println();
        System.out.println("Ingrese la descripcion de la tarea");
        System.out.println(lt.removeTarea(sc.nextLine()));
        this.padre.ejecutar();
    }

}

class VerTareas extends MenuItem {

    public VerTareas(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        listadoTareas lt = listadoTareas.getInstance();
        System.out.println(lt.VerTareas());
        this.padre.ejecutar();
    }

}

class AgregarJugador extends MenuItem {

    public AgregarJugador(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        variables v = variables.getInstance();
        System.out.println("Escriba el nombre del jugador a agregar");
        System.out.println(v.añadirJugador(sc.nextLine()));
        this.padre.ejecutar();
    }

}

class EliminarJugador extends MenuItem {

    public EliminarJugador(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        variables v = variables.getInstance();
        System.out.println("Escriba el nombre del jugador a eliminar");
        System.out.println(v.eliminarJugador(sc.nextLine()));
        this.padre.ejecutar();
    }

}

class VerJugadores extends MenuItem {
    variables v = variables.getInstance();

    public VerJugadores(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        System.out.println(v.printNombres());
        this.padre.ejecutar();
    }
}

class NumeroImpostores extends MenuItem {
    variables v = variables.getInstance();

    public NumeroImpostores(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        System.out.println("Numero de impostores actual: " + v.getCantidadImpostores());
        System.out.println("Inserte el nuevo numero de impostores");
        v.setCantidadImpostores(Integer.parseInt(sc.nextLine()));
        this.padre.ejecutar();
    }
}

class NumeroTareas extends MenuItem {
    variables v = variables.getInstance();

    public NumeroTareas(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    @Override
    void ejecutar() {
        System.out.println("Numero de tareas actual: " + v.getCantidadTareas());
        System.out.println("Inserte el nuevo numero de tareas");
        v.setCantidadTareas(Integer.parseInt(sc.nextLine()));
        this.padre.ejecutar();
    }
}

class jugar extends MenuItem {

    public jugar(String nombre, Scanner sc) {
        super(nombre, sc);
    }

    void ejecutar() {
        app app = new app();
        app.estructuraJuego();
        this.padre.ejecutar();
    }
}

/*
 * Clase que representa el menú de configuración del juego. Este menú tiene
 * opciones para modificar las tareas y los jugadores del juego.
 */
public class MenuConfiguracion {
    Scanner sc = new Scanner(System.in);
    Menu principal = new Menu("Among us", sc);

    /*
     * Constructor de la clase MenuConfiguracion. Se crean los menús y las opciones
     */
    public MenuConfiguracion() {

        Menu Tareas = new Menu("Tareas", sc);

        MenuItem AgregarTarea = new AgregarTarea("Agregar Tarea", sc);
        MenuItem EliminarTarea = new EliminarTarea("Eliminar Tarea", sc);
        MenuItem VerTareas = new VerTareas("Ver Tareas", sc);
        Tareas.addMenu(AgregarTarea);
        Tareas.addMenu(EliminarTarea);
        Tareas.addMenu(VerTareas);

        Menu Jugadores = new Menu("Jugadores", sc);

        MenuItem AgregarJugador = new AgregarJugador("Agregar Jugador", sc);
        MenuItem EliminarJugador = new EliminarJugador("Eliminar Jugador", sc);
        MenuItem VerJugadores = new VerJugadores("Ver Jugadores", sc);
        MenuItem modificarImpostores = new NumeroImpostores("Modificar numero de impostores", sc);
        MenuItem modificarTareas = new NumeroTareas("Modificar numero de tareas", sc);
        Jugadores.addMenu(AgregarJugador);
        Jugadores.addMenu(EliminarJugador);
        Jugadores.addMenu(VerJugadores);
        Jugadores.addMenu(modificarImpostores);
        Jugadores.addMenu(modificarTareas);

        Menu configuracion = new Menu("Configuracion", sc);
        configuracion.addMenu(Tareas);
        configuracion.addMenu(Jugadores);

        MenuItem Jugar = new jugar("Jugar", sc);

        principal.addMenu(configuracion);
        principal.addMenu(Jugar);
    }

    /*
     * Método que ejecuta el menú principal
     */
    public void ejecutar() {
        principal.ejecutar();
    }
}
