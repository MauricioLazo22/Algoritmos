package Laboratorio-09.graph;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        listVertex.insert(newVertex);
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> origen = null;
        Vertex<E> destino = null;
        
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(verOri)) {
                origen = v;
            }
            if (v.getData().equals(verDes)) {
                destino = v;
            }
        }

        if (origen != null && destino != null) {
            Edge<E> edge = new Edge<>(destino);
            origen.listAdj.insert(edge);
        } else {
            System.out.println("Vértice no encontrado.");
        }
    }

    public String toString() {
        return this.listVertex.toString();
    }

}
