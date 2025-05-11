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

    public String toString(){
        String resultado = "[";
	    if (tope >= 0) {
	        for (int i = 0; i <= tope; i++) {
	            resultado += array[i];
	            if (i < tope) {
	                resultado += ", ";
	            }
	        }
	    }
	    resultado += "]";
	    return resultado;
    }
} 
