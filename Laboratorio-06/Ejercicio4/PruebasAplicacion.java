package Ejercicio4;
import Actividad1.ExceptionIsEmpty;

public class PruebasAplicacion {
    public static void main(String[] args) throws ExceptionIsEmpty {
        String[] pruebas = {
            "(())[()]()",    // true
            "((())[])",      // true
            "([])[])",       // false
            "([[]))",        // false
            "[",             // false
            "[][][](([)))"   // true
        };

        for (String prueba : pruebas) {
            System.out.println(prueba + " => " + 
                Aplicacion.balanceoSímbolos(prueba));
        }
    }
}