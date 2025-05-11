package Actividad3;
import Actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con prioridades Integer
            PriorityQueue<String, Integer> pq1 = new PriorityQueueLinkSort<>();
            System.out.println("=== Prueba con prioridades enteras ===");
            pq1.enqueue("Tarea media", 3);
            pq1.enqueue("Tarea urgente", 1);
            pq1.enqueue("Tarea normal", 5);
            System.out.println("Cola completa: " + pq1); // Ordenada por prioridad
            System.out.println("Front (máxima prioridad): " + pq1.front()); // Tarea urgente
            System.out.println("Back (mínima prioridad): " + pq1.back()); // Tarea normal
            System.out.println("Dequeue: " + pq1.dequeue()); // Elimina "Tarea urgente"

            // Prueba con prioridades String (orden alfabético)
            PriorityQueue<Integer, String> pq2 = new PriorityQueueLinkSort<>();
            System.out.println("\n=== Prueba con prioridades String ===");
            pq2.enqueue(100, "Alta");
            pq2.enqueue(200, "Baja");
            pq2.enqueue(50, "Media");
            System.out.println("Cola completa: " + pq2); // Ordenada alfabéticamente
            System.out.println("Dequeue: " + pq2.dequeue()); // Elimina 100 ("Alta")

            // Prueba de errores
            System.out.println("\n=== Prueba de excepciones ===");
            PriorityQueue<Boolean, Integer> pq3 = new PriorityQueueLinkSort<>();
            pq3.front(); // Lanza ExceptionIsEmpty

        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}