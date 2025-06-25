package Laboratorio-10.btree;

public class BNode<E> {
    protected static int nodeCounter = 0; // Contador global de nodos
    protected int idNode; // Identificador único de cada nodo
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode (int n){
        this.idNode = ++nodeCounter; // Asignar y aumentar el contador
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
    public boolean searchNode(E key, int[] pos) {
        int i = 0;
        while (i < count && keys.get(i) != null && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        if (i < count && keys.get(i) != null && key.compareTo(keys.get(i)) == 0) {
            pos[0] = i;
            return true;
        } else {
            pos[0] = i;
            return false;
        }
    }

    //Return the keys found in the node, now also includes idNode.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node ").append(idNode).append(": [");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}