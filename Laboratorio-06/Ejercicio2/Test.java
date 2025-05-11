package Ejercicio2;
import Actividad1.ExceptionIsEmpty;
import Actividad2.Queue;

public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con Strings
            Queue<String> colaStrings = new QueueArray<>(3);
            System.out.println("=== Prueba con Strings ===");
            colaStrings.enqueue("Primero");
            colaStrings.enqueue("Segundo");
            System.out.println("Contenido: " + colaStrings); // [Primero, Segundo]
            System.out.println("Front: " + colaStrings.front()); // Primero
            System.out.println("Dequeue: " + colaStrings.dequeue()); // Primero
            colaStrings.enqueue("Tercero");
            System.out.println("Nuevo contenido: " + colaStrings); // [Segundo, Tercero]

            // Prueba con Integers
            Queue<Integer> colaInts = new QueueArray<>(2);
            System.out.println("\n=== Prueba con Integers ===");
            colaInts.enqueue(100);
            colaInts.enqueue(200);
            System.out.println("Back: " + colaInts.back()); // 200
            colaInts.enqueue(300); // Lanza IllegalStateException (cola llena)
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error de cola vacía: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error de cola llena: " + e.getMessage());
        }
    }
}