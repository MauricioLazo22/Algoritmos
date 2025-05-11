package Actividad2;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con Integers
            Queue<Integer> intQueue = new QueueLink<>();
            System.out.println("=== Prueba con enteros ===");
            intQueue.enqueue(10);
            intQueue.enqueue(20);
            intQueue.enqueue(30);
            System.out.println("Contenido: " + intQueue); // 10, 20, 30
            System.out.println("Front: " + intQueue.front()); // 10
            System.out.println("Back: " + intQueue.back()); // 30
            System.out.println("Dequeue: " + intQueue.dequeue()); // 10
            System.out.println("Contenido actualizado: " + intQueue); // 20, 30

            // Prueba con Strings
            Queue<String> strQueue = new QueueLink<>();
            System.out.println("\n=== Prueba con cadenas ===");
            strQueue.enqueue("Hola");
            strQueue.enqueue("Mundo");
            System.out.println(strQueue.dequeue() + " " + strQueue.dequeue()); // Hola Mundo

            // Prueba de errores
            System.out.println("\n=== Prueba de excepciones ===");
            Queue<Double> emptyQueue = new QueueLink<>();
            emptyQueue.front(); // Lanza ExceptionIsEmpty

        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}