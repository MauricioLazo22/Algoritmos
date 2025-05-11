package Actividad3;
import Actividad1.ExceptionIsEmpty;

class PriorityQueueLinkSort<E,N> implements PriorityQueue<E,N> {

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
        // The list must be ordered by the priority of the elements.
        // The higher the priority, the element is further to the front.
    }
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()){
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        }
        E aux = this.first.getData().data;
        this.first = this.first.getNext();
        if (this.first == null){
            this.last = null;
            return aux;
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
    
    // Elements must be included in the string as they are located in the list
    
    public String toString(){
    // include here your code
    }
} 