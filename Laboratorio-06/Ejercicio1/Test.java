package Ejercicio1;
import Actividad1.ExceptionIsEmpty;
import Actividad1.Stack;

public class Test {
    public static void main(String[] args) {
        try {
            // Uso con Integer a través de la interfaz
            Stack<Integer> intStack = new StackLink<>();
            System.out.println("=== Prueba Integer ===");
            intStack.push(10);
            intStack.push(20);
            System.out.println("Contenido: " + intStack); // [20, 10]
            System.out.println("Top: " + intStack.top());  // 20
            System.out.println("Pop: " + intStack.pop());  // 20
            System.out.println("Nuevo estado: " + intStack); // [10]

        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}