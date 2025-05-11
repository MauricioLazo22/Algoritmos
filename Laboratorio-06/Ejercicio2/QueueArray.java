package Ejercicio2;
import Actividad1.ExceptionIsEmpty;
import Actividad2.Queue;

public class QueueArray<E> implements Queue<E> {

    private E[] array;
    private int first;
    private int last;
    private int size;
    private final int capacity;

    public QueueArray(int capacity) {
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = -1;
        this.size = 0;
        this.capacity = capacity;
    }

}