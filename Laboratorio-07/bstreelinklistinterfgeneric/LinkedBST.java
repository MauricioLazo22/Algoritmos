package bstreelinklistinterfgeneric;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E> implements BinarySearchTree<E>{
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
}
