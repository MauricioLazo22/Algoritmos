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

    public void insertEdge(V v, V z) {
        // Localizar vértices
        VertexObj<V,E> vert1 = null, vert2 = null;
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(v)) vert1 = vertex;
            if (vertex.info.equals(z)) vert2 = vertex;
        }
        // Si faltan vértices, no hacer nada
        if (vert1 == null || vert2 == null) return;

        // Comprobar que la arista no exista ya
        for (EdgeObj<V,E> edge : secEdge) {
            boolean direct = edge.endVertex1 == vert1 && edge.endVertex2 == vert2;
            boolean inverse = edge.endVertex1 == vert2 && edge.endVertex2 == vert1;
            if (direct || inverse) {
                return;
            }
        }

        // Insertar nueva arista
        EdgeObj<V,E> newEdge = new EdgeObj<>(vert1, vert2, null, secEdge.size());
        secEdge.add(newEdge);
    }

    public boolean searchVertex(V info) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.info.equals(info)) {
                return true;
            }
        }
        return false;
    }
    
}