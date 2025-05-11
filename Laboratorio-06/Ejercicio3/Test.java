package Ejercicio3;
import Actividad1.ExceptionIsEmpty;
import Actividad3.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con Strings y prioridades Integer
            PriorityQueue<String, Integer> pqStr = new PriorityQueueLinked<>(3);
            System.out.println("=== Prueba Strings ===");
            pqStr.enqueue("Urgente", 0);
            pqStr.enqueue("Media", 1);
            System.out.println("Front: " + pqStr.front()); // Urgente
            System.out.println("Dequeue: " + pqStr.dequeue()); // Urgente
            pqStr.enqueue("Baja", 2);
            System.out.println("Back: " + pqStr.back()); // Baja

        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}