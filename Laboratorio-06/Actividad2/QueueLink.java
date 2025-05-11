package Actividad2;
import Actividad1.ExceptionIsEmpty;

public class QueueLink<E> implements Queue<E> {

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
        } else{
            this.last.setNext(aux);
            this.last = aux;
        }
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
        if (this.isEmpty()) {
			throw new ExceptionIsEmpty("La cola está vacia.");
		} else {
			return this.last.getData();
		}
    }

    public E front() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
			throw new ExceptionIsEmpty("La cola está vacia.");
		} else {
			return this.first.getData();
		}
    }

    public boolean isEmpty() {
        return first == null;
    }

	public String toString(){
		if (this.isEmpty()) {
	        return "La cola está vacía.";
	    } else {
            String resultado = "";
	        Node<E> actual = first;
	
            while (actual != null) {
                resultado += actual.getData();
                if (actual.getNext() != null) {
                    resultado += ", ";
                }
                actual = actual.getNext();
            }
	    return resultado;
        } 
	}
}