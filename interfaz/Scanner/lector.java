package Scanner;

import java.util.Scanner;
/*
 * Clase que contiene el scanner para leer la entrada del usuario
 
 */
public class lector {
    Scanner sc ;
    private static lector instance = null;

    private lector(){
        sc = new Scanner(System.in);
    }

    public static lector getInstance(){
        if (instance == null) {
            instance = new lector();
        }
        return instance;
    }

    public Scanner getScanner(){
        return sc;
    }
}
