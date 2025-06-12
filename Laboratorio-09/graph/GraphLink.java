package Laboratorio-09.graph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public boolean searchVertex(E v) {
        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchEdge(E v, E z) {
        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                for (Edge<E> edge : vertex.listAdj) {
                    if (edge.getData().equals(z)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeVertex(E v) {
        for (Vertex<E> vertex : listVertex) {
            for (Edge<E> edge : vertex.listAdj) {
                if (edge.getData().equals(v)) {
                    vertex.listAdj.remove(edge);
                    break;
                }
            }
        }

        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                listVertex.remove(vertex);
                break;
            }
        }
    }

    public void removeEdge(E v, E z) {
        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                for (Edge<E> edge : vertex.listAdj) {
                    if (edge.getData().equals(z)) {
                        vertex.listAdj.remove(edge);
                        break;
                    }
                }
            }
        }
    }

    public void dfs(E v) {
        Set<Vertex<E>> visited = new HashSet<>();
        dfsRecursive(v, visited);
    }

    private void dfsRecursive(E v, Set<Vertex<E>> visited) {
        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                if (!visited.contains(vertex)) {
                    System.out.println(vertex.getData());
                    visited.add(vertex);
                    
                    for (Edge<E> edge : vertex.listAdj) {
                        dfsRecursive(edge.getData(), visited);
                    }
                }
                break;
            }
        }
    }

    public void bfs(E v) {
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                queue.add(vertex);
                visited.add(vertex);
                System.out.println(vertex.getData());

                while (!queue.isEmpty()) {
                    Vertex<E> current = queue.poll();

                    for (Edge<E> edge : current.listAdj) {
                        Vertex<E> neighbor = edge.getRefDest();

                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                            System.out.println(neighbor.getData());
                        }
                    }
                }
                break;
            }
        }
    }

    public ArrayList<Vertex<E>> bfsPath(E v, E z) {
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        Map<Vertex<E>, Vertex<E>> previous = new HashMap<>();
        ArrayList<Vertex<E>> path = new ArrayList<>();

        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                queue.add(vertex);
                visited.add(vertex);

                while (!queue.isEmpty()) {
                    Vertex<E> current = queue.poll();

                    if (current.getData().equals(z)) {
                        Vertex<E> temp = current;
                        while (temp != null) {
                            path.add(0, temp);
                            temp = previous.get(temp);
                        }
                        return path;
                    }

                    for (Edge<E> edge : current.listAdj) {
                        Vertex<E> neighbor = edge.getRefDest();

                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                            previous.put(neighbor, current);
                        }
                    }
                }
            }
        }
        return path;
    }

    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> vertexV = null;
        Vertex<E> vertexZ = null;

        for (Vertex<E> vertex : listVertex) {
            if (vertex.getData().equals(v)) {
                vertexV = vertex;
            }
            if (vertex.getData().equals(z)) {
                vertexZ = vertex;
            }
        }

        if (vertexV != null && vertexZ != null) {
            Edge<E> edge = new Edge<>(vertexZ, w);
            vertexV.listAdj.insert(edge);
        }
    }

    public ArrayList<Vertex<E>> shortPath(E sourceData, E targetData) {
        Vertex<E> source = null, target = null;
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(sourceData)) {
                source = v;
            }
            if (v.getData().equals(targetData)) {
                target = v;
            }
        }
        if (source == null || target == null) {
            return new ArrayList<>();
        }

        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        for (Vertex<E> v : listVertex) {
            dist.put(v, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        // Recopilar todas las aristas del grafo
        class EdgeInfo {
            Vertex<E> u;
            Vertex<E> v;
            int w;
            EdgeInfo(Vertex<E> u, Vertex<E> v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }
        ArrayList<EdgeInfo> edges = new ArrayList<>();
        for (Vertex<E> u : listVertex) {
            for (Edge<E> e : u.listAdj) {
                edges.add(new EdgeInfo(u, e.getRefDest(), e.getWeight()));
            }
        }

        int V = dist.size();
        // Relajar aristas V-1 veces
        for (int i = 1; i < V; i++) {
            for (EdgeInfo ei : edges) {
                if (dist.get(ei.u) != Integer.MAX_VALUE && dist.get(ei.u) + ei.w < dist.get(ei.v)) {
                    dist.put(ei.v, dist.get(ei.u) + ei.w);
                    prev.put(ei.v, ei.u);
                }
            }
        }

        // Comprobar ciclos negativos
        for (EdgeInfo ei : edges) {
            if (dist.get(ei.u) != Integer.MAX_VALUE && dist.get(ei.u) + ei.w < dist.get(ei.v)) {
                return new ArrayList<>();
            }
        }

        // Reconstruir la ruta desde target hasta source
        ArrayList<Vertex<E>> path = new ArrayList<>();
        for (Vertex<E> at = target; at != null; at = prev.get(at)) {
            path.add(0, at);
        }
        // Verificar que la ruta empieza en el origen
        if (path.isEmpty() || !path.get(0).equals(source)) {
            return new ArrayList<>();
        }
        return path;
    }

    public boolean isConexo() {
        // Si no hay vértices o solo uno, se considera conexo
        int totalVertices = 0;
        Vertex<E> inicio = null;
        for (Vertex<E> v : listVertex) {
            totalVertices++;
            if (inicio == null) {
                inicio = v;
            }
        }
        if (inicio == null || totalVertices <= 1) {
            return true;
        }

        Set<Vertex<E>> visitados = new HashSet<>();
        Queue<Vertex<E>> cola = new LinkedList<>();
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();
            for (Edge<E> arista : actual.listAdj) {
                Vertex<E> vecino = arista.getRefDest();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return visitados.size() == totalVertices;
    }

    public String toString() {  
        return this.listVertex.toString();
    }

}
