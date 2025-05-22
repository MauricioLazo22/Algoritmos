package bstreelinklistinterfgeneric;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E>{

    class Node{
        public E data;
        public Node left;
        public Node right;

        public Node(E data){
            this(data,null,null);
        }

        public Node(E data, Node left, Node Right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST(){
        this.root = null;
    }

    public void insert(E data) throws ItemDuplicated {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node actual = root;
        Node padre = null;

        while (actual != null) {
            padre = actual;
            int comparacion = data.compareTo(actual.data);
            if (comparacion == 0) {
                throw new ItemDuplicated("El elemento ya existe en el árbol");
            } else if (comparacion < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        if (data.compareTo(padre.data) < 0) {
            padre.left = new Node(data);
        } else {
            padre.right = new Node(data);
        }
    }

    public E search(E data) throws ItemNoFound {
        Node actual = root;

        while (actual != null) {
            int comparacion = data.compareTo(actual.data);

            if (comparacion == 0) {
                return actual.data;
            } else if (comparacion < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        throw new ItemNoFound("El elemento no se encuentra en el árbol");
    }

    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío, no se puede eliminar");
        }

        Node actual = root;
        Node padre = null;

        while (actual != null && data.compareTo(actual.data) != 0) {
            padre = actual;
            if (data.compareTo(actual.data) < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        if (actual == null) {
            return; // No se encontró el dato, no se hace nada
        }

        if (actual.left == null && actual.right == null) {
            if (actual == root) {
                root = null;
            } else if (padre.left == actual) {
                padre.left = null;
            } else {
                padre.right = null;
            }
        } else if (actual.left != null && actual.right == null) {
            if (actual == root) {
                root = actual.left;
            } else if (padre.left == actual) {
                padre.left = actual.left;
            } else {
                padre.right = actual.left;
            }
        } else if (actual.left == null && actual.right != null) {
            if (actual == root) {
                root = actual.right;
            } else if (padre.left == actual) {
                padre.left = actual.right;
            } else {
                padre.right = actual.right;
            }
        } else {
            Node sucesor = obtenerMinimo(actual.right);
            E datoSucesor = sucesor.data;
            delete(datoSucesor);
            actual.data = datoSucesor;
        }
    }

    private Node obtenerMinimo(Node nodo) {
        while (nodo.left != null) {
            nodo = nodo.left;
        }
        return nodo;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return recorrerInorden(root);
    }

    private String recorrerInorden(Node nodo) {
        if (nodo == null) {
            return "";
        }
        String izquierda = recorrerInorden(nodo.left);
        String centro = nodo.data.toString() + " ";
        String derecha = recorrerInorden(nodo.right);
        return izquierda + centro + derecha;
    }

    private void inOrder(Node nodo) {
        if (nodo != null) {
            inOrder(nodo.left);
            System.out.println(nodo.data);
            inOrder(nodo.right);
        }
    }

    private void preOrder(Node nodo) {
        if (nodo != null) {
            System.out.println(nodo.data);
            preOrder(nodo.left);
            preOrder(nodo.right);
        }
    }

    private void postOrder(Node nodo) {
        if (nodo != null) {
            postOrder(nodo.left);
            postOrder(nodo.right);
            System.out.println(nodo.data);
        }
    }
}
