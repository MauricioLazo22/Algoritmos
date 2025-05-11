package Actividad1;

class StackArray<E> implements Stack<E> {

    private E[] array;
    private int tope;

    public StackArray(int n){
        this.array = (E[]) new Object[n];
        tope = -1;
    }

    public void push(E x){
        if (isFull()){
            System.out.println("La pila esta llena.");
        } else {
            tope++;
		    array[tope] = x;
        }
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
			throw new ExceptionIsEmpty("La pila está vacia");
		} else {
			E itemEliminado = array[tope];
			array[tope] = null;
			tope--;
			return itemEliminado;
		}
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
			throw new ExceptionIsEmpty("La pila está vacia");
		} else {
			return array[tope];
		}
    }

    public boolean isEmpty() {
        return this.tope == -1;
    }

    public boolean isFull(){
        if (tope == array.length) return true;
		else  return false;
    }

    //The elements must be included in the chain from the one at the top
    //to the one at the bottom of the stack.

    public String toString(){
        // include here your code
    }
} 
