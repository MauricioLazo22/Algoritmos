package Actividad3;
import Actividad1.ExceptionIsEmpty;

class PriorityQueueLinkSort<E,N extends Comparable<N>> implements PriorityQueue<E,N> {

    class EntryNode{
        E data;
        N priority;

        EntryNode(E data, N priority){
            this.data = data;
            this.priority = priority;
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort (){
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr){
        Node<EntryNode> nuevo = new Node<>(new EntryNode(x, pr));

        if (isEmpty()) {
            this.first = nuevo;
            this.last = nuevo;
            return;
        }
    
        if (pr.compareTo(first.getData().priority) < 0) {
            nuevo.setNext(first);
            first = nuevo;
            return;
        }
    
        Node<EntryNode> anterior = first;
        Node<EntryNode> actual = first.getNext();
    
        while (actual != null && pr.compareTo(actual.getData().priority) >= 0) {
            anterior = actual;
            actual = actual.getNext();
        }
    
        nuevo.setNext(actual);
        anterior.setNext(nuevo);
    
        if (nuevo.getNext() == null) {
            last = nuevo;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()){
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        }
        E aux = this.first.getData().data;
        this.first = this.first.getNext();
        if (this.first == null){
            this.last = null;
        }
        return aux;
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola está vacía");
        return last.getData().data;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("La cola está vacía");
        return first.getData().data;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
    
    public String toString() {
        String resultado = "";
        Node<EntryNode> actual = first;
        while (actual != null) {
            resultado += "(" + actual.getData().data + ", " + actual.getData().priority + ") -> ";
            actual = actual.getNext();
        }
        resultado += "null";
        return resultado;
    }
} 