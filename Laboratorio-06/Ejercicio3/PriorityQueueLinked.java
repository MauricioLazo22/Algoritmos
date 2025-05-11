package Ejercicio3;
import Actividad1.ExceptionIsEmpty;
import Actividad3.PriorityQueue;
import Actividad2.QueueLink;

public class PriorityQueueLinked<E, P extends Comparable<P>> implements PriorityQueue<E, P> {

    private QueueLink<E>[] colas;
    private final int prioridades;

    public PriorityQueueLinked(int prioridades) {
        this.prioridades = prioridades;
        this.colas = new QueueLink[prioridades];
        for (int i = 0; i < prioridades; i++) {
            colas[i] = new QueueLink<>();
        }
    }

    public void enqueue(E elemento, P prioridad) {
        int nivelPrioridad = prioridad.compareTo(prioridad);
        colas[nivelPrioridad].enqueue(elemento);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < prioridades; i++) {
            if (!colas[i].isEmpty()) {
                return colas[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < prioridades; i++) {
            if (!colas[i].isEmpty()) {
                return colas[i].front();
            }
        }
        throw new ExceptionIsEmpty("Todas las colas están vacías");
    }

    
}