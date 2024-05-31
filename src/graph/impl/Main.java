package graph.impl;

public class Main {
    public static void main(String[] args){
        Graph graph = new Graph();
        // Adding Nodes.
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        // Adding Edges
        graph.addEdge("C", "A");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("D", "E");
        System.out.println();
        System.out.println("Adjacency List Representation of Graph");
        System.out.println("======================================");
        graph.print();
        System.out.println("======================================\n");

        // Depth First Search Algorithm
        System.out.println("Depth First Search (Recursive)");
        System.out.println("======================================");
        graph.dfsRecursive("C");
        System.out.println("======================================\n");

        System.out.println("Depth First Search (Iterative)");
        System.out.println("======================================");
        graph.dfsIterative("C");
        System.out.println("======================================\n");

        // Breath First Search Algorithm
        System.out.println("Breath First Search (Iterative)");
        System.out.println("======================================");
        graph.bfsIterative("C");
        System.out.println("======================================\n");

        // Topological Sort
        GraphAdvanced topologicalGraph = new GraphAdvanced();
        topologicalGraph.addNode("A");
        topologicalGraph.addNode("B");
        topologicalGraph.addNode("P");
        topologicalGraph.addNode("X");

        topologicalGraph.addEdge("X", "A");
        topologicalGraph.addEdge("X", "B");
        topologicalGraph.addEdge("A", "P");
        topologicalGraph.addEdge("B", "P");

        System.out.println("Topological Sort");
        System.out.println("======================================");
        var topologicalSort = topologicalGraph.topologicalSort();
        System.out.println(topologicalSort);
        System.out.println("======================================\n");

        // Cycle Detection
        GraphAdvanced noCycleGraph = new GraphAdvanced();
        noCycleGraph.addNode("A");
        noCycleGraph.addNode("B");
        noCycleGraph.addNode("C");

        noCycleGraph.addEdge("A", "B");
        noCycleGraph.addEdge("B", "C");
        noCycleGraph.addEdge("A", "C");

        System.out.println("Cycle Detection");
        System.out.println("======================================");
        System.out.println("Graph 1 - " + noCycleGraph.hasCycle());

        GraphAdvanced cycleGraph = new GraphAdvanced();
        cycleGraph.addNode("A");
        cycleGraph.addNode("B");
        cycleGraph.addNode("C");

        cycleGraph.addEdge("A", "B");
        cycleGraph.addEdge("B", "C");
        cycleGraph.addEdge("C", "A");

        System.out.println("Graph 2 - " + cycleGraph.hasCycle());
        System.out.println("======================================\n");
    }
}
