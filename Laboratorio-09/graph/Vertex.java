package Laboratorio-09.graph;

public class Vertex {
    private E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data){
        this.data = data;
        listAdj = new ListLinked<Edge<E>>();
    }

}
