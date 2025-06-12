package Laboratorio-09.graph;

public class GraphListEdge<V,E> {
    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge(){
        this.secVertex = new ArrayList<VertexObj<V,E>>();
        this.secEdge = new ArrayList<EdgeObj<V,E>>();
    }
    
    public void insertVertex(V info) {
        // Verificar si ya existe el vértice
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(info)) {
                return;
            }
        }
        // Si no existe, crear y agregar
        int pos = secVertex.size();
        VertexObj<V,E> newVertex = new VertexObj<>(info, pos);
        secVertex.add(newVertex);
    }
}