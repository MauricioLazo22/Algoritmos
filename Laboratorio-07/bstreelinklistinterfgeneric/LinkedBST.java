package bstreelinklistinterfgeneric;
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

}
