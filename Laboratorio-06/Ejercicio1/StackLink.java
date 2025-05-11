package Ejercicio1;

import Actividad1.ExceptionIsEmpty;
import Actividad1.Stack;
import Actividad2.Node;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }
}
