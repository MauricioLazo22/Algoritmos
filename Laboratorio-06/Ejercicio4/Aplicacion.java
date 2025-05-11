package Ejercicio4;
import Actividad1.ExceptionIsEmpty;
import Ejercicio1.StackLink;

public class Aplicacion {
    public static boolean balanceoSímbolos(String cadena) throws ExceptionIsEmpty {
        StackLink<Character> pila = new StackLink<>();
        
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            
            if (caracter == '(' || caracter == '[' || caracter == '{') {
                pila.push(caracter);
            } else if (caracter == ')' || caracter == ']' || caracter == '}') {
                if (pila.isEmpty()) {
                    return false;
                }
                char tope = pila.pop();
                if (!((tope == '(' && caracter == ')') || 
                      (tope == '[' && caracter == ']') || 
                      (tope == '{' && caracter == '}'))) {
                    return false;
                }
            }
        }
        return pila.isEmpty();
    }
}