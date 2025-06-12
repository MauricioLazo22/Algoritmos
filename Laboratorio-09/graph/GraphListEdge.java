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

    public String representacionFormal() {
        StringBuilder sb = new StringBuilder();
        // Vértices
        sb.append("V = {");
        for (int i = 0; i < secVertex.size(); i++) {
            sb.append(secVertex.get(i).info);
            if (i < secVertex.size() - 1) sb.append(", ");
        }
        sb.append("}\n");
        // Aristas
        sb.append("E = {");
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V,E> e = secEdge.get(i);
            sb.append("{")
            .append(e.endVertex1.info)
            .append(",")
            .append(e.endVertex2.info)
            .append("}");
            if (i < secEdge.size() - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public Map<V, List<V>> listaAdyacencias() {
        Map<V, List<V>> adj = new LinkedHashMap<>();
        // Inicializar lista vacía para cada vértice
        for (VertexObj<V,E> v : secVertex) {
            adj.put(v.info, new ArrayList<>());
        }
        // Rellenar con vecinos
        for (EdgeObj<V,E> e : secEdge) {
            V u = e.endVertex1.info;
            V w = e.endVertex2.info;
            adj.get(u).add(w);
            adj.get(w).add(u);
        }
        return adj;
    }

    public int[][] matrizAdyacencia() {
        int n = secVertex.size();
        int[][] matriz = new int[n][n];
        // Mapa de índice por vértice
        Map<V, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idx.put(secVertex.get(i).info, i);
        }
        // Marcar 1 donde exista arista
        for (EdgeObj<V,E> e : secEdge) {
            int i = idx.get(e.endVertex1.info);
            int j = idx.get(e.endVertex2.info);
            matriz[i][j] = 1;
            matriz[j][i] = 1; // grafo no dirigido
        }
        return matriz;
    }

    public int outDegree(E v) {
        for (Vertex<E> vert : listVertex) {
            if (vert.getData().equals(v)) {
                return vert.listAdj.size();
            }
        }
        return 0;
    }

    public int inDegree(E v) {
        int count = 0;
        for (Vertex<E> vert : listVertex) {
            for (Edge<E> edge : vert.listAdj) {
                if (edge.getRefDest().getData().equals(v)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int grado(E v) {
        return inDegree(v) + outDegree(v);
    }

    private boolean isUnderlyingConnected() {
        if (listVertex.isEmpty()) return true;
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        Vertex<E> start = listVertex.get(0);
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex<E> curr = queue.poll();
            // explorar salientes
            for (Edge<E> e : curr.listAdj) {
                Vertex<E> nei = e.getRefDest();
                if (visited.add(nei)) queue.add(nei);
            }
            // explorar entrantes
            for (Vertex<E> vert : listVertex) {
                for (Edge<E> e : vert.listAdj) {
                    if (e.getRefDest() == curr && visited.add(vert)) {
                        queue.add(vert);
                    }
                }
            }
        }
        return visited.size() == listVertex.size();
    }

    public boolean esCaminoDirigido() {
        if (!isUnderlyingConnected() || listVertex.size() < 2) return false;
        int origen = 0, sumidero = 0;
        for (Vertex<E> vert : listVertex) {
            int out = vert.listAdj.size();
            int in = inDegree(vert.getData());
            if (out - in == 1) origen++;
            else if (in - out == 1) sumidero++;
            else if (in != out) return false;
        }
        return origen == 1 && sumidero == 1;
    }

    public boolean esCicloDirigido() {
        if (!isUnderlyingConnected() || listVertex.size() < 2) return false;
        for (Vertex<E> vert : listVertex) {
            if (vert.listAdj.size() != 1 || inDegree(vert.getData()) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean esRuedaDirigido() {
        int n = listVertex.size();
        if (!isUnderlyingConnected() || n < 4) return false;
        Vertex<E> centro = null;
        int centros = 0;
        for (Vertex<E> vert : listVertex) {
            int out = vert.listAdj.size(), in = inDegree(vert.getData());
            if (out == n - 1 && in == 0) {
                centro = vert;
                centros++;
            }
        }
        if (centros != 1) return false;
        // Verificar ciclo dirigido en periféricos
        for (Vertex<E> vert : listVertex) {
            if (vert == centro) continue;
            if (vert.listAdj.size() != 1 || inDegree(vert.getData()) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean esCompletoDirigido() {
        int n = listVertex.size();
        for (Vertex<E> vert : listVertex) {
            if (vert.listAdj.size() != n - 1 || inDegree(vert.getData()) != n - 1) {
                return false;
            }
        }
        return true;
    }
    
}