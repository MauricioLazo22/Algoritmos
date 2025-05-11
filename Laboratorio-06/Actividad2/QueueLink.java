package Actividad2;
import Actividad1.ExceptionIsEmpty;

class QueueLink<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;

    public QueueLink(){
        this.first = null;
        this.last = null;
    }
    
    public void enqueue(E x){
        Node<E> aux = new Node<E>(x);
        if (this.isEmpty()) {
        this.first = aux;
        else
        this.last.setNext(aux);
        this.last = aux;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
			throw new ExceptionIsEmpty("La cola está vacia.");
		} else {
			Node<E> aux = first;
			first = first.getNext();
			return aux.getData();
		}
    }

    public E back() throws ExceptionIsEmpty {
        // include here your code
    }

    public E front() throws ExceptionIsEmpty {
        // include here your code 
    }

    public boolean isEmpty() {
        // include here your code
    }

    //The elements must be included in the chain from the one at the front
    //to the one at the back of the queue.

    public String toString(){
        // include here your code
    }
} 