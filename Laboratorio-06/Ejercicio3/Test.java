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

            // Prueba con Integers y prioridades String
            PriorityQueue<Integer, String> pqInt = new PriorityQueueLinked<>(2);
            System.out.println("\n=== Prueba Integers ===");
            pqInt.enqueue(100, "Alta");
            pqInt.enqueue(200, "Baja");
            System.out.println("Dequeue: " + pqInt.dequeue()); // 100 (Alta)
            System.out.println("isEmpty: " + pqInt.isEmpty()); // false
            
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}