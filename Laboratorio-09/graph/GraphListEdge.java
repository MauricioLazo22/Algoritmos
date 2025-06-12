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

    public static <V> GraphListEdge<V, Void> fromFormal(Collection<V> vertices, Collection<V[]> edges) {
        GraphListEdge<V, Void> g = new GraphListEdge<>();
        for (V v : vertices) {
            g.insertVertex(v);
        }
        for (V[] e : edges) {
            if (e != null && e.length == 2) {
                g.insertEdge(e[0], e[1]);
            }
        }
        return g;
    }

    public static <V> GraphListEdge<V, Void> fromAdjacencyList(Map<V, ? extends Collection<V>> adj) {
        GraphListEdge<V, Void> g = new GraphListEdge<>();
        // añadir vértices
        for (V v : adj.keySet()) {
            g.insertVertex(v);
        }
        // añadir aristas evitando duplicados (insertEdge ya verifica)
        for (Map.Entry<V, ? extends Collection<V>> entry : adj.entrySet()) {
            V u = entry.getKey();
            for (V w : entry.getValue()) {
                g.insertEdge(u, w);
            }
        }
        return g;
    }

    public static <V> GraphListEdge<V, Void> fromAdjacencyMatrix(V[] vertices,int[][] matrix) {
        GraphListEdge<V, Void> g = new GraphListEdge<>();
        // insertar vértices
        for (V v : vertices) {
            g.insertVertex(v);
        }
        // insertar aristas
        int n = vertices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {      // i < j evita duplicar
                if (matrix[i][j] == 1) {
                    g.insertEdge(vertices[i], vertices[j]);
                }
            }
        }
        return g;
    }

    public boolean esIsomorfo(GraphLink<E> g2) {
        int n = listVertexSize();
        if (n != g2.listVertexSize() || countEdges() != g2.countEdges()) return false;
        if (n > 8) throw new IllegalArgumentException("Isomorfismo exacto limitado a n ≤ 8");

        int[][] m1 = toMatrix(this);
        int[][] m2 = toMatrix(g2);

        int[] perm = new int[n];
        for (int i = 0; i < n; i++) perm[i] = i;

        return backtrackIso(0, perm, m1, m2);
    }

    private boolean backtrackIso(int idx, int[] perm, int[][] m1, int[][] m2) {
        int n = perm.length;
        if (idx == n) return matricesEqualUnderPerm(m1, m2, perm);

        for (int i = idx; i < n; i++) {
            swap(perm, idx, i);
            if (backtrackIso(idx + 1, perm, m1, m2)) return true;
            swap(perm, idx, i);
        }
        return false;
    }

    private boolean matricesEqualUnderPerm(int[][] a, int[][] b, int[] p) {
        int n = p.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] != b[p[i]][p[j]]) return false;
        return true;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private int[][] toMatrix(GraphLink<E> g) {
        int n = g.listVertexSize();
        Map<Vertex<E>, Integer> idx = new HashMap<>();
        int k = 0;
        for (Vertex<E> v : g.listVertex) idx.put(v, k++);
        int[][] m = new int[n][n];
        for (Vertex<E> v : g.listVertex) {
            int i = idx.get(v);
            for (Edge<E> e : v.listAdj) {
                int j = idx.get(e.getRefDest());
                m[i][j] = 1;
            }
        }
        return m;
    }

    private int listVertexSize() {
        int c = 0;
        for (Vertex<E> v : listVertex) c++;
        return c;
    }

    private int countEdges() {
        int c = 0;
        for (Vertex<E> v : listVertex) c += v.listAdj.size();
        return c;
    }

    public boolean esPlano() {
       int n = listVertexSize();          // número de vértices
        if (n <= 4) return true;           // todo grafo con ≤4 vértices es plano
        int e = undirectedEdgeCount();     // número de aristas sin duplicar
        return e <= 3 * n - 6;             // cota de planaridad de Euler
    } 

    private int undirectedEdgeCount() {
        Set<String> pares = new HashSet<>();
        for (Vertex<E> v : listVertex) {
            for (Edge<E> e : v.listAdj) {
                Vertex<E> w = e.getRefDest();
                // clave conmutativa basada en identityHashCode para evitar colisiones
                String key = System.identityHashCode(v) < System.identityHashCode(w)
                        ? v.getData() + "|" + w.getData()
                        : w.getData() + "|" + v.getData();
                pares.add(key);
            }
        }
        return pares.size();
    }

    public boolean esConexoDirigido() {
        if (listVertexSize() == 0) return true;          // grafo vacío → conexo

        Set<Vertex<E>> visitados = new HashSet<>();
        Queue<Vertex<E>> cola = new LinkedList<>();

        Vertex<E> inicio = listVertex.iterator().next();
        visitados.add(inicio);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();

            // Aristas salientes
            for (Edge<E> e : actual.listAdj) {
                Vertex<E> vecino = e.getRefDest();
                if (visitados.add(vecino)) cola.add(vecino);
            }
            // Aristas entrantes (tratamos el grafo como no dirigido)
            for (Vertex<E> v : listVertex) {
                for (Edge<E> e : v.listAdj) {
                    if (e.getRefDest() == actual && visitados.add(v)) {
                        cola.add(v);
                    }
                }
            }
        }
        return visitados.size() == listVertexSize();
    }
}