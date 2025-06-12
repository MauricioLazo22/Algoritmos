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
    }
}
