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

    public String toString() {
        return this.listVertex.toString();
    }

}
