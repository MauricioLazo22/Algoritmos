package Actividad1;

class StackArray<E> implements Stack<E> {

    private E[] array;
    private int tope;

    public StackArray(int n){
        this.array = (E[])new Object[n];
        tope = -1;
    }

    public void push(E x){
        // include here your code
    }

    public E pop() throws ExceptionIsEmpty {
        // include here your code
    }

    public E top() throws ExceptionIsEmpty {
        // include here your code
    }

    public boolean isEmpty() {
        return this.tope == -1;
    }

    public boolean isFull(){
        // include here your code
    }

    //The elements must be included in the chain from the one at the top
    //to the one at the bottom of the stack.

    public String toString(){
        // include here your code
    }
} 
