package Laboratorio-09.graph;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.Set;

public class JGraphTExample {

    public static void main(String[] args) {
        // 1. Creamos y poblamos nuestro grafo personalizado
        GraphListEdge<String, String> custom = new GraphListEdge<>();
        custom.insertVertex("A");
        custom.insertVertex("B");
        custom.insertVertex("C");
        custom.insertVertex("D");
        custom.insertVertex("E");

        custom.insertEdge("A", "B");
        custom.insertEdge("A", "C");
        custom.insertEdge("B", "D");
        custom.insertEdge("C", "D");
        custom.insertEdge("D", "E");
        custom.insertEdge("B", "C");

        // 2. Convertimos a un grafo no dirigido de JGraphT
        Graph<String, DefaultEdge> graph =
                new DefaultUndirectedGraph<>(DefaultEdge.class);

        // Añadimos vértices
        for (VertexObj<String, String> vObj : custom.secVertex) {
            graph.addVertex(vObj.info);
        }
        // Añadimos aristas
        for (EdgeObj<String, String> eObj : custom.secEdge) {
            String u = eObj.endVertex1.info;
            String w = eObj.endVertex2.info;
            graph.addEdge(u, w);
        }

        // 3. Inspección básica
        System.out.println("Vértices JGraphT: " + graph.vertexSet());
        System.out.println("Aristas JGraphT: " + graph.edgeSet());

        // 4. Recorrido BFS usando BreadthFirstIterator
        System.out.print("BFS desde A: ");
        BreadthFirstIterator<String, DefaultEdge> bfsIter =
                new BreadthFirstIterator<>(graph, "A");
        while (bfsIter.hasNext()) {
            System.out.print(bfsIter.next() + " ");
        }
        System.out.println();

        // 5. Conectividad y componentes
        ConnectivityInspector<String, DefaultEdge> ci =
                new ConnectivityInspector<>(graph);
        System.out.println("¿Grafo conectado? " + ci.isConnected());
        System.out.println("Componentes conectados: " + ci.connectedSets());

        // 6. Detección de ciclos
        CycleDetector<String, DefaultEdge> cd =
                new CycleDetector<>(graph);
        System.out.println("¿Contiene ciclos? " + cd.detectCycles());
        if (cd.detectCycles()) {
            System.out.println("Vértices en algún ciclo: " + cd.findCycles());
        }

        // 7. Camino más corto (Dijkstra)
        DijkstraShortestPath<String, DefaultEdge> dijkstra =
                new DijkstraShortestPath<>(graph);
        var path = dijkstra.getPath("A", "E");
        System.out.println("Ruta más corta A→E: " + path.getVertexList());
        System.out.println("Distancia (aristas): " + path.getLength());

        // 8. Árbol de expansión mínima (Kruskal) (Para grafos no dirigidos por defecto)
        KruskalMinimumSpanningTree<String, DefaultEdge> kruskal =
                new KruskalMinimumSpanningTree<>(graph);
        System.out.println("MST (aristas): " +
                kruskal.getSpanningTree().getEdges());

        // 9. Coloreado voraz de vértices
        GreedyColoring<String, DefaultEdge> coloring =
                new GreedyColoring<>(graph);
        System.out.println("Número de colores usados: " +
                coloring.getColoring().getNumberColors());
        System.out.println("Asignación de colores: " +
                coloring.getColoring().getColors());
    }
}
