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

    public boolean searchEdge(V v, V z) {
        for (EdgeObj<V,E> edge : secEdge) {
            V a = edge.endVertex1.info;
            V b = edge.endVertex2.info;
            if ((a.equals(v) && b.equals(z)) || (a.equals(z) && b.equals(v))) {
                return true;
            }
        }
        return false;
    }

    public void bfs(V v) {
        // Buscar el vértice de inicio
        VertexObj<V,E> start = null;
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(v)) {
                start = vertex;
                break;
            }
        }
        if (start == null) return;

        // Conjuntos y cola para BFS
        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();

        // Inicialización
        visited.add(start);
        queue.add(start);

        // Proceso BFS
        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.println(current.info);

            // Explorar vecinos a través de secEdge
            for (EdgeObj<V,E> edge : secEdge) {
                VertexObj<V,E> neighbor = null;
                if (edge.endVertex1 == current) {
                    neighbor = edge.endVertex2;
                } else if (edge.endVertex2 == current) {
                    neighbor = edge.endVertex1;
                }
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public int grado(V info) {
        VertexObj<V,E> vert = null;
        for (VertexObj<V,E> v : secVertex) {
            if (v.info.equals(info)) {
                vert = v;
                break;
            }
        }
        if (vert == null) return 0;
        int grado = 0;
        for (EdgeObj<V,E> e : secEdge) {
            if (e.endVertex1 == vert || e.endVertex2 == vert) {
                grado++;
            }
        }
        return grado;
    }

    private boolean isConnected() {
        if (secVertex.isEmpty()) return true;
        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();
        visited.add(secVertex.get(0));
        queue.add(secVertex.get(0));
        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            for (EdgeObj<V,E> e : secEdge) {
                VertexObj<V,E> neigh = null;
                if (e.endVertex1 == current) neigh = e.endVertex2;
                else if (e.endVertex2 == current) neigh = e.endVertex1;
                if (neigh != null && visited.add(neigh)) {
                    queue.add(neigh);
                }
            }
        }
        return visited.size() == secVertex.size();
    }

    public boolean esCamino() {
        if (!isConnected() || secVertex.size() < 2) return false;
        int extremos = 0;
        for (VertexObj<V,E> v : secVertex) {
            int d = grado(v.info);
            if (d == 1) extremos++;
            else if (d != 2) return false;
        }
        return extremos == 2;
    }

    public boolean esCiclo() {
        if (!isConnected() || secVertex.size() < 3) return false;
        for (VertexObj<V,E> v : secVertex) {
            if (grado(v.info) != 2) return false;
        }
        return true;
    }

    public boolean esRueda() {
        int n = secVertex.size();
        if (!isConnected() || n < 4) return false;
        VertexObj<V,E> centro = null;
        int centros = 0;
        for (VertexObj<V,E> v : secVertex) {
            int d = grado(v.info);
            if (d == n - 1) {
                centro = v;
                centros++;
            } else if (d != 3) {
                return false;
            }
        }
        if (centros != 1) return false;
        // Verificar que al excluir el centro, los demás formen un ciclo
        Set<VertexObj<V,E>> others = new HashSet<>(secVertex);
        others.remove(centro);
        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();
        VertexObj<V,E> start = others.iterator().next();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            VertexObj<V,E> curr = queue.poll();
            for (EdgeObj<V,E> e : secEdge) {
                VertexObj<V,E> neigh = null;
                if (e.endVertex1 == curr && !others.contains(e.endVertex2)) continue;
                if (e.endVertex1 == curr) neigh = e.endVertex2;
                else if (e.endVertex2 == curr) neigh = e.endVertex1;
                if (neigh != null && others.contains(neigh) && visited.add(neigh)) {
                    queue.add(neigh);
                }
            }
        }
        return visited.size() == others.size();
    }

    public boolean esCompleto() {
        int n = secVertex.size();
        for (VertexObj<V,E> v : secVertex) {
            if (grado(v.info) != n - 1) return false;
        }
        return true;
    }
}