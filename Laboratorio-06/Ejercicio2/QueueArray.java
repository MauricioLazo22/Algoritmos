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

    public void enqueue(E x) {
        if (isFull()) {
            throw new IllegalStateException("La cola está llena");
        }
        last = (last + 1) % capacity;
        array[last] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        E data = array[first];
        array[first] = null;
        first = (first + 1) % capacity;
        size--;
        return data;
    }

     public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return array[first];
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return array[last];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0, current = first; i < size; i++, current = (current + 1) % capacity) {
            sb.append(array[current]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}