package Laboratorio-10.btree;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode (int n){
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        for(int i=0; i < n; i++){ 
            this.keys.add(null);
            this.childs.add(null);
        }
    }
    //Check if the current node is full
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }
    //Check if the current node is empty
    public boolean nodeEmpty() {
        return count == 0;
    }
    
    //Search for a key in the current node, if found it returns true and
    //the position where it is located, otherwise, returns false and the
    //position of the child where it should descend.
    public boolean searchNode ( ) {
    }
    //Return the keys found in the node.
    public String toString(){
    }
}