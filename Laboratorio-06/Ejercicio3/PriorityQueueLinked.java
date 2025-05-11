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
}